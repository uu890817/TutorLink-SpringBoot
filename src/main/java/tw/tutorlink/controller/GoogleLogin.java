package tw.tutorlink.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@RestController
//@CrossOrigin(origins = "*") // 允许来自任何源的请求
public class GoogleLogin {
	

	
	@PostMapping("/googletoken")
	@ResponseBody
	public String verifyToken(@RequestBody String googleToken) {
		System.out.println(googleToken);
		JsonObject jsonObject = JsonParser.parseString(googleToken).getAsJsonObject();

		String accessToken = jsonObject.get("access_token").getAsString();
		System.out.println(accessToken);
		String urlStr = "https://www.googleapis.com/oauth2/v3/tokeninfo?access_token="+accessToken;
		System.out.println(urlStr);
		StringBuilder response = new StringBuilder();
		try {
            // 创建URL对象
            URL apiUrl = new URL(urlStr);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // 设置请求方法
            connection.setRequestMethod("GET");
            
            // 获取响应代码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应数据
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
               

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 输出响应数据
                System.out.println("Response from URL: " + response.toString());
            } else {
                System.out.println("Failed to fetch data. Response code: " + responseCode);
            }

            // 关闭连接
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		JsonObject aaa = JsonParser.parseString(response.toString()).getAsJsonObject();
		System.out.println(aaa.get("sub"));

		return "success";
	}

}
