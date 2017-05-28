package com.jojoliu.hexagon;

import com.jojoliu.hexagon.util.AuthFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class HexagonApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HexagonApplication.class, args);
        new SpringApplicationBuilder(HexagonApplication.class).web(true).run(args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        AuthFilter authFilter  = new AuthFilter ();
        registrationBean.setFilter(authFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/rest/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
