package br.com.jean;

import br.com.jean.domain.entity.Cliente;
import br.com.jean.domain.entity.Pedido;
import br.com.jean.domain.repository.ClienteRepository;
import br.com.jean.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController


public class VendasApplication {



    @Autowired ClienteRepository cli;
    @Autowired PedidoRepository ped;
    @Bean
    public CommandLineRunner init(
            ) {
        return args -> {

            System.out.println("Salvando Cliente");
            Cliente teste = new Cliente();
            teste.setNome("Jean");
            cli.save(teste);

            System.out.println("Salvando pedido");
            Pedido p = new Pedido();
            p.setCliente(teste);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            ped.save(p);

            Pedido p1 = new Pedido();
            p1.setCliente(teste);
            p1.setDataPedido(LocalDate.now());
            p1.setTotal(BigDecimal.valueOf(10));
            ped.save(p1);

//            teste.setPedidos(Arrays.asList(p,p1));

//            cli.findClienteFetchPedidos(teste.getId());
//            System.out.println(teste.getPedidos());
//            System.out.println(p.getCliente().getNome());

            ped.findByCliente(teste).forEach(System.out::println);

        };
    }


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
