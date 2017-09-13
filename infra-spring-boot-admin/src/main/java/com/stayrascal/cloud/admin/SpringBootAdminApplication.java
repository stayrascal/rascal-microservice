package com.stayrascal.cloud.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import de.codecentric.boot.admin.notify.LoggingNotifier;
import de.codecentric.boot.admin.notify.Notifier;
import de.codecentric.boot.admin.notify.RemindingNotifier;
import de.codecentric.boot.admin.notify.filter.FilteringNotifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class SpringBootAdminApplication {
    public static ApplicationContext APPLICATION_CONTEXT;

    public static void main(String[] args) {
        APPLICATION_CONTEXT = SpringApplication.run(SpringBootAdminApplication.class, args);
    }

    @Configuration
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll()
                    .and().logout().logoutUrl("/logout")
                    .and().csrf().disable()
                    .authorizeRequests().antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**").permitAll()
                    .and().authorizeRequests().antMatchers("/**").authenticated()
                    .and().httpBasic();
        }
    }

    @Configuration
    public static class NotifierConfig {
        @Bean
        @Primary
        public RemindingNotifier remindingNotifier() {
            RemindingNotifier notifier = new RemindingNotifier(filteringNotifier(loggerNotifier()));
            notifier.setReminderPeriod(TimeUnit.SECONDS.toMillis(10));
            return notifier;
        }

        @Scheduled(fixedRate = 1_000L)
        public void remind() {
            remindingNotifier().sendReminders();
        }

        @Bean
        public FilteringNotifier filteringNotifier(Notifier delegate) {
            return new FilteringNotifier(delegate);
        }

        @Bean
        public LoggingNotifier loggerNotifier() {
            return new LoggingNotifier();
        }
    }
}
