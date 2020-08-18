package br.com.jean;

import br.com.jean.domain.entity.Cliente;
import br.com.jean.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController


public class VendasApplication {




    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository cli) {
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Jean");
            cli.save(cliente);

            boolean exist = cli.existsClienteByNome("Jean");
            System.out.println("existe um cliente com nome Jean" + exist);

            List<Cliente> todos = cli.encontrarPornome("Jean");
            todos.forEach(System.out::println);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
