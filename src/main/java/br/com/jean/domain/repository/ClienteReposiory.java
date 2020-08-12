package br.com.jean.domain.repository;


import br.com.jean.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteReposiory  {

    private static  String INSERT ="insert into cliente (nome) values (?)";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente save(Cliente cliente){
    jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()}  );
    return cliente;
    }

}
