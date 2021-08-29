package com.eden.springgallery.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config for cloudinary image upload.
 */
@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.name}")
    private String cloudName;
    @Value("${cloudinary.key}")
    private String apiKey;
    @Value("${cloudinary.secret}")
    private String apiSecret;

    /**
     * Cloudinary client config.
     *
     * @return cloudinary client
     */
    @Bean
    public Cloudinary cloudinary() {

        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", this.cloudName,
                "api_key", this.apiKey,
                "api_secret", this.apiSecret
        ));
    }
}
