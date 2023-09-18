package tw.tutorlink.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {

    @Value("${upload.path}")
    private String uploadPath;

    @Bean
    public void createUploadDirectory() {
        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
    }
}
