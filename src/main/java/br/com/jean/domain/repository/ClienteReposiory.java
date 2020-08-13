package br.com.jean.domain.repository;


import br.com.jean.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteReposiory {

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "SELECT * FROM cliente";
    private static String UPDATE = "update cliente set nome = ? where id =?";
    private static String DELETE = "delete from cliente where id = ?";



    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente save(Cliente cliente) {
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> getAll() {
        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }



    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(),
                cliente.getId()});

        return cliente;
    }


    public List<Cliente> getByName(String nome){
    return jdbcTemplate
            .query(SELECT_ALL.concat(" where nome like ?"),
                    new Object[]{"%" + nome + "%"},
                    getRowMapper());
    }

    public void delete(Cliente cliente) {
        delete(cliente.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }


    private RowMapper<Cliente> getRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }

}
