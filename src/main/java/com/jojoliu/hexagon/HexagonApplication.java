package com.jojoliu.hexagon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class HexagonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexagonApplication.class, args);
	}

}
