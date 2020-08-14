package br.com.jean;

import br.com.jean.domain.entity.Cliente;
import br.com.jean.domain.repository.ClienteReposiory;
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
    public CommandLineRunner init(@Autowired ClienteReposiory clienteReposiory) {
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Jean");
            clienteReposiory.save(cliente);


            Cliente c2 = new Cliente();
            c2.setNome("Teste");
            clienteReposiory.save(c2);

            List<Cliente> todos = clienteReposiory.getAll();
            todos.forEach(System.out::println);

            clienteReposiory.delete(c2.getId());
        clienteReposiory.delete(1);
           clienteReposiory.getByName("Jean");

            System.out.println("deletados");
            List<Cliente> todos1 = clienteReposiory.getAll();
            todos.forEach(System.out::println);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
