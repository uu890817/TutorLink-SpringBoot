package tw.tutorlink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Configuration
//public class CrosConfig implements WebMvcConfigurer{
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//			registry.addMapping("/**")//所有API支持跨域
//					.allowedOrigins("/*")//允許域名
//					.allowCredentials(true)//是否發送Cookie
//					.allowedMethods("*")//允許那些請求方法
//					.allowedHeaders("/*")
//					.maxAge(3600)//跨域允許時間
//					;
//	}	
//
//	
//}

@Configuration
public class CrosConfig {
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //允許域名
        config.addAllowedOriginPattern("*");
        //允許Cookie信息
        config.setAllowCredentials(true);

        config.addAllowedMethod("*");

        //允許任意Header
        config.addAllowedHeader("*");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}