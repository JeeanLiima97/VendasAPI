package br.com.jean;

import br.com.jean.domain.entity.Cliente;
import br.com.jean.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner {
    @Autowired
    ClienteRepository clienteRepository;

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    Cliente cliente = new Cliente();
    cliente.setNome("Jean");
    clienteRepository.save(cliente);

    }
}
