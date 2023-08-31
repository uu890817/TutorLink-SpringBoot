package tw.tutorlink.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.service.UsersService;

@RestController
public class GoogleLoginController {

	@Autowired
	private UsersService uService;

	@PostMapping("/googlelogin")
	@ResponseBody
	public String loginByGoogle(@RequestBody String googleToken, HttpSession session)
			throws JsonMappingException, JsonProcessingException {

		// 處理google登入後續邏輯
		JsonObject jsonObject = JsonParser.parseString(googleToken).getAsJsonObject();
		String accessToken = jsonObject.get("access_token").getAsString();
		String urlStr = "https://www.googleapis.com/oauth2/v3/tokeninfo?access_token=" + accessToken;
		// 設定一個空字串準備裝回應值
		String response = "";
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
					response += line;
				}
				System.out.println(response);
				reader.close();
			}
			// 關閉連結
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(response);

		String mail = jsonNode.get("email").asText();
		String sub = jsonNode.get("sub").asText();

		// 利用google回傳token中的唯一識別碼及Mail是否存在資料庫中
		Users user = uService.login(sub, mail);

		// ----------- 建立session -----------
		// 當回傳不為空值時，代表資料存在，寫入整個bean進session
		if (user != null) {
			session.setAttribute("logState", user);
			System.out.println(session.getId());
			return "google";
//			Users loggedInUser = (Users) session.getAttribute("logState");
//			System.out.println("測試撈session中資料: "+loggedInUser.getGoogleSubId());
//			System.out.println("測試撈session中資料: "+loggedInUser.getUserEmail());
		} else {
			return "verification failed";
		}
	}

	// ----------- 登出，清除session -----------
	@GetMapping("/googlelogout")
	@ResponseBody
	public void logout(HttpSession session) {
		session.removeAttribute("logState");
	}
}