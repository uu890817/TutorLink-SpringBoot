package tw.tutorlink.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import tw.tutorlink.websocket.TutorlinkWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	
	public WebSocketConfig() {
		System.out.println(this.getClass().getName());
	}
	
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new TutorlinkWebSocketHandler(), "/tSocket/{userId}")
				.setAllowedOrigins("*") //允許跨域
				
				;

	}

}
