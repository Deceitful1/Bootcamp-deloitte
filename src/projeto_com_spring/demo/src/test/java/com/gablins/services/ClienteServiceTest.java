package com.gablins.services;

import com.gablins.DTOs.ClienteVO;
import com.gablins.entities.Cliente;
import com.gablins.repositories.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest
{
    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteService clienteService;


    @Test
    void findAll()
    {
        List<Cliente> clientes = Cliente.VOToOjectList(mockClientList(5));


        when(clienteRepository.findAll()).thenReturn(clientes);
        List<ClienteVO> clienteVOs = clienteService.findAll();

        //adicionar link Hateoas pra cada cliente
        clienteVOs.forEach(c -> {
            ClienteService.addHateoasLinks(c);
        });
        clienteVOs.forEach(c -> assertNotNull(c.getLinks()));
        clienteVOs.forEach(c -> {
            assertNotNull(c);
        });

    }

    @Test
    void create()
    {
        Cliente cliente = mockClient();
        when(clienteRepository.save(cliente)).thenReturn(mockClient());
        var cliente1 = clienteRepository.save(cliente);
        var data = ClienteVO.toVO(cliente1);


        assertNotNull(data);


        ClienteService.addHateoasLinks(data);
        assertTrue(!data.getLinks().isEmpty());
        assertTrue(data.getLink("update").isPresent());
        assertTrue(data.getLink("delete").isPresent());
        assertTrue(data.getLink("findById").isPresent());
        assertTrue(data.getLink("findAll").isPresent());
        assertTrue(data.getLink("findByCpf").isPresent());
        assertTrue(data.getLink("findByEmail").isPresent());
    }

    @Test
    void findById()
    {
        ClienteVO data = mockClientVO();
        Cliente client = Cliente.VOToObject(data);
        when(clienteRepository.findById(data.getId())).thenReturn(Optional.of(client));
        Assertions.assertEquals(ClienteVO.toVO(client), clienteService.findById(data.getId()));

        ClienteService.addHateoasLinks(data);
        assertTrue(!data.getLinks().isEmpty());
        assertTrue(data.getLink("update").isPresent());
        assertTrue(data.getLink("delete").isPresent());
        assertTrue(data.getLink("findById").isPresent());


    }

    @Test
    void update()
    {
        Cliente data = mockClient();
        data.setNome("gabriel2");
        data.setId(1L
        );
        ClienteVO updateObject = mockClientVO();
        updateObject.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(data));

        when(clienteRepository.save(data)).thenReturn(Cliente.VOToObject(updateObject));

        var resultVO = clienteService.update(updateObject);

        assertNotNull(resultVO);

        ClienteService.addHateoasLinks(resultVO);
        assertTrue(!resultVO.getLinks().isEmpty());
        assertTrue(resultVO.getLink("update").isPresent());
        assertTrue(resultVO.getLink("delete").isPresent());
        assertTrue(resultVO.getLink("findById").isPresent());


    }

    @Test
    void delete()
    {
        ClienteVO data = mockClientVO();
        data.setId(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(Cliente.VOToObject(data)));

        clienteService.delete(1L);
        verify(clienteRepository, times(1)).deleteById(1L);

    }

    @Test
    void findByEmail()
    {
        ClienteVO data = mockClientVO();

        Cliente client = Cliente.VOToObject(data);
        when(clienteRepository.findByEmail(data.getEmail())).thenReturn(client);
        var result = clienteRepository.findByEmail(data.getEmail());
        Assertions.assertEquals(data.getEmail(), result.getEmail());

        result = Cliente.VOToObject(ClienteVO.toVO(result));
        var resultVo = ClienteVO.toVO(result);

        Assertions.assertEquals(client, result);

        ClienteService.addHateoasLinks(resultVo);
        assertTrue(!resultVo.getLinks().isEmpty());
        assertTrue(resultVo.getLink("update").isPresent());
        assertTrue(resultVo.getLink("delete").isPresent());
        assertTrue(resultVo.getLink("findById").isPresent());
    }

    @Test
    void findByCpf()
    {
        ClienteVO data = mockClientVO();

        Cliente client = Cliente.VOToObject(data);
        when(clienteRepository.findByCpf(data.getCpf())).thenReturn(client);
        var result = clienteRepository.findByCpf(data.getCpf());
        Assertions.assertEquals(data.getCpf(), result.getCpf());

        result = Cliente.VOToObject(ClienteVO.toVO(result));
        var resultVo = ClienteVO.toVO(result);

        Assertions.assertEquals(client, result);

        ClienteService.addHateoasLinks(resultVo);
        assertTrue(!resultVo.getLinks().isEmpty());
        assertTrue(resultVo.getLink("update").isPresent());
        assertTrue(resultVo.getLink("delete").isPresent());
        assertTrue(resultVo.getLink("findById").isPresent());

    }

    public static ClienteVO mockClientVO()
    {
        return new ClienteVO(1L, "12345678912", "gab", "rua m", "1234", "gab@email.com");
    }

    public static Cliente mockClient()
    {
        return new Cliente("12345678912", "gab", "rua m", "1234", "gab@email.com");
    }

    public static List<ClienteVO> mockClientList(int times)
    {
        List<ClienteVO> clientes = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            clientes.add(new ClienteVO(Long.parseLong(String.valueOf(i)), "1234567890" + i, "gab", "rua m", "1234", "gab@email.com"));
        }
        return clientes;
    }


}