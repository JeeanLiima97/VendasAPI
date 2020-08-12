package br.com.jean;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean(name = "appName")
    public String appName(){
        return "Vendas";
    }

}
