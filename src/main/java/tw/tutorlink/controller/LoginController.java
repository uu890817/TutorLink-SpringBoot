package tw.tutorlink.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.ReCAPTCHADAO;
import tw.tutorlink.service.UsersService;

@RestController
public class LoginController {

	@Autowired
	private UsersService uService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ReCAPTCHADAO rDAO;

	@PostMapping("/googlelogin")
	@ResponseBody
	public String loginByGoogle(@RequestBody String googleToken, HttpSession session, HttpServletResponse response)
			throws JsonMappingException, JsonProcessingException {

		// 處理google登入後續邏輯
		JsonObject jsonObject = JsonParser.parseString(googleToken).getAsJsonObject();
		String accessToken = jsonObject.get("access_token").getAsString();
		String urlStr = "https://www.googleapis.com/oauth2/v3/tokeninfo?access_token=" + accessToken;
		// 設定一個空字串準備裝回應值
		String res = "";
		// ----------- 傳給google網站分析token -----------
		try {
			// 建URL
			URL apiUrl = new URL(urlStr);
			// 開連結
			HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
			// 設置請求方法
			connection.setRequestMethod("GET");
			// 獲取回應代碼，200為正常回傳
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 讀取回應數據
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				// 讀取每段回應值，直到空值前加到字串中
				while ((line = reader.readLine()) != null) {
					res += line;
				}
				System.out.println(response);
				reader.close();
			}
			// 關閉連結
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 分析json字串
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(res);

		String mail = jsonNode.get("email").asText();
		String sub = jsonNode.get("sub").asText();

		// 利用google回傳token中的唯一識別碼及Mail是否存在資料庫中
		Users user = uService.googleLogin(sub, mail);

		if (user != null) {
			String usersid = user.getUsersId().toString();
			Cookie cookie = new Cookie("UsersId", usersid);
			cookie.setMaxAge(3600);
			cookie.setPath("/");
			response.addCookie(cookie);
			session.setMaxInactiveInterval(600);
			session.setAttribute("logState", user);
			return "google";
		} else {
			return "loginfail";
		}
	}

	@PostMapping("/normallogin")
	@ResponseBody
	public String login(@RequestBody String str, HttpSession session, HttpServletResponse response) {
		JsonObject json = JsonParser.parseString(str).getAsJsonObject();
		String mail = json.get("mail").getAsString();
		String pwd = json.get("pwd").getAsString();
		System.out.println(mail + "  " + pwd);
		String result = uService.normalLogin(mail, pwd);
		if (result.equals("102")) {
			Users user = uService.checkMail(mail);
			String usersid = user.getUsersId().toString();

			// 重新建立cookie
			Cookie cookie = new Cookie("UsersId", usersid);
			cookie.setMaxAge(3600);
			cookie.setPath("/");
			// 回傳cookie
			response.addCookie(cookie);

			// 建立session
			session.setMaxInactiveInterval(600);
			session.setAttribute("logState", user);

			return result;
		}
		return result;
	}

	// ----------- 登出，清除session、cookie-----------
	@GetMapping("/logout")
	@ResponseBody
	public String logout(HttpSession session, HttpServletResponse response) {
		// 將Cookie 值設置為null
		Cookie cookie = new Cookie("UsersId", "");

		// 設置過期時間為0
		cookie.setMaxAge(0);
		cookie.setPath("/");
		// 將Cookie 物件加入Response 中
		response.addCookie(cookie);

		// 移除session
		session.removeAttribute("logState");
		session.invalidate();
		System.out.println("已清除session");
		return "logout";
	}

	// ----------- 機器人驗證 -----------
	@PostMapping("/recaptchaV2")
	@ResponseBody
	public String recaptchaV2(@RequestBody Object obj) {
		String url = "https://www.google.com/recaptcha/api/siteverify";
		String secretKey = rDAO.findById(1).get().getReCAPTCHA().toString();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("secret", secretKey);
		params.add("response", obj.toString());

		String result = restTemplate.postForObject(url, params, String.class);

		return result;
	}
}
