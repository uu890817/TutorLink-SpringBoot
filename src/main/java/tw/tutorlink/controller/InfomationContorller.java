package tw.tutorlink.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.user.InfomationDTO;
import tw.tutorlink.repository.UsersDAO;
import tw.tutorlink.service.UsersService;

@RestController
public class InfomationContorller {

	@Autowired
	private UsersService uService;

	@Autowired
	private UsersDAO uDAO;

	@PostMapping("/infomation")
	@ResponseBody
	public InfomationDTO infomation(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		System.out.println("Session取得的ID : " + userid);
		InfomationDTO iDTO = uService.findByIdDetail(userid);
		if (iDTO.getGoogletoken() != null) {
			iDTO.setGoogletoken("google");
		}
		return iDTO;
	}

	// 使用者設定個人資料用
	@PostMapping("/send")
	@ResponseBody
	public String data(@RequestBody String str, HttpSession session, @CookieValue("UsersId") String cookie) {
		// 字串轉JSON
		JsonObject json = JsonParser.parseString(str).getAsJsonObject();
		String name = json.get("UserName").getAsString();
		String phone = json.get("Phone").getAsString();
		String city = json.get("City").getAsString();
		Long birth = json.get("Birthday").getAsLong();
		// 用session抓ID
		Users loggedInUser = (Users) session.getAttribute("logState");
		int sessionid = loggedInUser.getUsersId();
		uService.setData(sessionid, name, phone, city, birth);
		return "ok";
	}

	// --- 修改/驗證密碼用 ---
	@PostMapping("/pwdverifty")
	@ResponseBody
	public String pwd(@RequestBody String str, HttpSession session, HttpServletResponse response) {
		JsonObject json = JsonParser.parseString(str).getAsJsonObject();
		String oldpwd = json.get("oldPwd").getAsString();
		String newPwd = json.get("newPwd").getAsString();
		String newPwd2 = json.get("newPwd2").getAsString();
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		String result = uService.findbyIdAndPwd(userid, oldpwd, newPwd, newPwd2);
		if (result.equals("fail")) {
			return "fail";
		}
		return "update";
	}

	// --- 回傳UserType，如果是老師就不會顯示申請老師頁面，且可以切換學生/老師頁面 ---
	@PostMapping("/type")
	@ResponseBody
	public Integer type(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		Users result = uService.findUsersByID(userid);
		if (result != null) {
			return result.getUserType();
		}
		return null;
	}

	// 用在使用者頁面彈出視窗顯示名字用
	@PostMapping("/username")
	@ResponseBody
	public String username(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		InfomationDTO result = uService.findByIdDetail(userid);
		if (result != null) {
			System.out.println(result.getUserName());
			return result.getUserName();
		}
		return null;
	}

	@PostMapping("/logintime")
	@ResponseBody
	public String logintime(HttpSession session) throws JSONException, ParseException {
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		Users result = uService.findUsersByID(userid);
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String lastLoginTime = "";
		if (result.getUserDetail().getLastLoginTime().toString() == null) {
			lastLoginTime = "2000-01-01 00:00:00.000000";
		} else {
			lastLoginTime = result.getUserDetail().getLastLoginTime().toString();
		}
		String newLoginTime = result.getUserDetail().getNewLoginTime().toString();

		// 創建SimpleDateFormat對象以格式化日期時間
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

		Date lastLoginOriginalDate = originalFormat.parse(lastLoginTime);
		Date newLoginOriginalDate = originalFormat.parse(newLoginTime);
		String formattedlastDateTime = outputFormat.format(lastLoginOriginalDate);
		String formattednewDateTime = outputFormat.format(newLoginOriginalDate);

		JSONObject item = new JSONObject().put("LastLoginTime", formattedlastDateTime).put("NewLoginTime",
				formattednewDateTime);
		return item.toString();
	}

	// 上傳大頭貼
	@Value("${upload.path}")
	private String uploadPath;

	@PostMapping("/uploadimg")
	@ResponseBody
	public String imgUpload(@RequestParam("image") MultipartFile imageFile, HttpSession session)
			throws IllegalStateException, IOException {
		Users loggedInUser = (Users) session.getAttribute("logState");
		String userId = loggedInUser.getUsersId().toString();
		String userName = loggedInUser.getUserDetail().getUserName();
		if (imageFile != null && !imageFile.isEmpty()) {
			String contentType = imageFile.getContentType();
			System.out.println(contentType);
			if (contentType != null && contentType.startsWith("image")) {

				// 新增檔案
				String fileName = userId + "-" + userName + ".jpg";
				Path filePath = Paths.get(uploadPath, fileName);
				File targetFile = filePath.toFile();
				File uploadDirectory = targetFile.getParentFile();
				if (!uploadDirectory.exists()) {
					uploadDirectory.mkdirs();
				}
				imageFile.transferTo(targetFile);
				byte[] fileBytes = Files.readAllBytes(filePath);
				// 針對id 將圖片路徑寫進資料庫
				Users result = uService.findUsersByID(loggedInUser.getUsersId());
				result.getUserDetail().setImage(fileName);
				result.getUserDetail().setImageByte(fileBytes);
				uDAO.save(result);

				return "success";
			}
		}
		return "fail";
	}

	@PostMapping("/getImage")
	public String getImage(HttpSession session) throws IOException {
		Users loggedInUser = (Users) session.getAttribute("logState");
		Users result = uService.findUsersByID(loggedInUser.getUsersId());
		String img = result.getUserDetail().getImage();
		Path filePath = Paths.get(uploadPath, img);
		byte[] fileBytes = Files.readAllBytes(filePath);
		byte[] fileBytestest = result.getUserDetail().getImageByte();
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);
		String base64Imagetest = Base64.getEncoder().encodeToString(fileBytestest);
//		return base64Image;
		return base64Imagetest;
	}
}
