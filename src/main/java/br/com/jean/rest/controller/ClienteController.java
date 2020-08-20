package br.com.jean.rest.controller;


import br.com.jean.domain.entity.Cliente;
import br.com.jean.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes/")
    @ResponseBody
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
    Cliente clienteSalvo = clienteRepository.save(cliente);
    return ResponseEntity.ok(clienteSalvo);

    }


}
