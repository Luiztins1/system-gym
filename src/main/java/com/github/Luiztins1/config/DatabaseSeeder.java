package com.github.Luiztins1.config;

import com.github.Luiztins1.model.entity.UserAuth;
import com.github.Luiztins1.repository.UserAuthRepository;
import com.github.Luiztins1.service.UserAuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner initDatabase(UserAuthRepository repository, PasswordEncoder encoder){
        return args -> {
            if(repository.count() == 0){
                UserAuth defaultAdmin = new UserAuth();
                defaultAdmin.setLogin("admin");
                defaultAdmin.setPassword(encoder.encode("admin123"));
                defaultAdmin.setRoles(List.of("ADMIN"));

                repository.save(defaultAdmin);
            }
        };
    }
}
