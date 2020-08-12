package br.com.jean;

import br.com.jean.domain.entity.Cliente;
import br.com.jean.domain.repository.ClienteReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteReposiory clienteReposiory){
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Jean");
            clienteReposiory.save(cliente);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
