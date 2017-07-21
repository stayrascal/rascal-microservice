package com.stayrascal.cloud.user;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.stayrascal.cloud.common")
@ComponentScan("com.stayrascal.cloud.user")
@ComponentScan("com.stayrascal.cloud.loaddata")
@EntityScan({
        "com.stayrascal.cloud.user.auth.infrastructure.persistence.po",
        "com.stayrascal.cloud.user.member.infrastructure.persistence.po",
        "com.stayrascal.cloud.user.admin.infrastructure.persistence.po"
})
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
