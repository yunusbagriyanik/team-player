package com.api.teamplayer;

import com.api.teamplayer.config.SpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = SpringConfig.class)
public class TeamPlayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamPlayerApplication.class, args);
    }

}
