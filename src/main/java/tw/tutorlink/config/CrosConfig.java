package tw.tutorlink.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CrosConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")//所有API支持跨域
					.allowedOrigins("/*")//允許域名
					.allowCredentials(true)//是否發送Cookie
					.allowedMethods("*")//允許那些請求方法
					.allowedHeaders("/*")
					.maxAge(3600)//跨域允許時間
					;
	}	

	
}
