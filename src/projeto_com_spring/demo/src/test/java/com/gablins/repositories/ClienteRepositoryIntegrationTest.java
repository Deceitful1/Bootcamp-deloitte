package com.gablins.repositories;

import com.gablins.entities.Cliente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
class ClienteRepositoryIntegrationTest
{
    @Autowired
    EntityManager em;

    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    @DisplayName("Verificar no banco de dados se um cliente existe pelo cpf")
    void existsByCpf()
    {
        Cliente cliente = new Cliente("12345678912", "gabriel", "rua a", "123", "gabriel@email.com");
        em.persist(cliente);
        boolean exists = clienteRepository.existsByCpf(cliente.getCpf());
        Assertions.assertTrue(exists);

    }

    @Test
    @DisplayName("Verificar no banco de dados se um cliente não existe pelo cpf")
    void notExitsByCpf()
    {
        Cliente cliente = new Cliente("12345678912", "gabriel", "rua a", "123", "gabriel@email.com");
        clienteRepository.save(cliente);
        boolean exists = clienteRepository.existsByCpf("1211212999");
        Assertions.assertFalse(exists);
    }

    @Test
    @DisplayName("Verificar no banco de dados se um cliente existe pelo email")
    void existsByEmail()
    {
        Cliente cliente = new Cliente("12345678912", "gabriel", "rua a", "123", "gabriel@email.com");
        em.persist(cliente);
        boolean exists = clienteRepository.existsByEmail(cliente.getEmail());
        Assertions.assertTrue(exists);


    }

    @Test
    @DisplayName("Verificar no banco de dados se um cliente não existe pelo email")
    void notExistsByEmail()
    {
        Cliente cliente = new Cliente("12345678912", "gabriel", "rua a", "123", "gabriel@email.com");
        em.persist(cliente);
        boolean exists = clienteRepository.existsByEmail("g@email.com");
        Assertions.assertFalse(exists);


    }

    @Test
    @DisplayName("Encontrar cliente por cpf")
    void findByCpf()
    {
        Cliente cliente = new Cliente("1212345678", "gabriel", "rua a", "123", "gabriel@email.com");
        em.persist(cliente);
        var response = clienteRepository.findByCpf(cliente.getCpf());

        Assertions.assertEquals(cliente.getCpf(), response.getCpf());
    }

    @Test
    @DisplayName("Não Encontrar cliente por cpf")
    void notFindByCpf()
    {
        Cliente cliente = new Cliente("1212345678", "gabriel", "rua a", "123", "gabriel@email.com");
        em.persist(cliente);
        var response = clienteRepository.findByCpf("1");
        Assertions.assertNull(response);
    }


    @Test
    @DisplayName("Encontrar cliente por email")
    void findByEmail()
    {
        Cliente cliente = new Cliente("1212345678", "gabriel", "rua a", "123", "gabriel@email.com");
        em.persist(cliente);
        var response = clienteRepository.findByEmail("gabriel@email.com");
        Assertions.assertEquals(cliente.getEmail(), response.getEmail());

    }

    @DisplayName("Não Encontrar um cliente por email")
    void notFindByEmail()
    {
        Cliente cliente = new Cliente("1212345678", "gabriel", "rua a", "123", "gabriel@email.com");
        em.persist(cliente);
        var response = clienteRepository.findByEmail("ga@email.com");
        Assertions.assertNull(response);

    }
}

