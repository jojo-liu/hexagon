package com.jojoliu.hexagon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class HexagonApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HexagonApplication.class, args);
        new SpringApplicationBuilder(HexagonApplication.class).web(true).run(args);
    }

}
