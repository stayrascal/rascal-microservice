package com.stayrascal.cloud.order;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.stayrascal.cloud.common")
@ComponentScan("com.stayrascal.cloud.order")
@ComponentScan("com.stayrascal.cloud.loaddata")
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
