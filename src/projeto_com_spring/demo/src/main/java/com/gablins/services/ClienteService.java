package com.gablins.services;

import com.gablins.DTOs.ClienteVO;
import com.gablins.controllers.ClienteController;
import com.gablins.entities.Cliente;
import com.gablins.exceptions.BadRequestException;
import com.gablins.exceptions.ClienteNotFoundException;
import com.gablins.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ClienteService
{
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteVO> findAll()
    {

        var resultList = ClienteVO.toVOList(clienteRepository.findAll());
        for (ClienteVO result : resultList) {
            addHateoasLinks(result.getId(), result.getCpf(), result.getEmail(), result);
        }
        return resultList;
    }

    public ClienteVO create(ClienteVO cliente)
    {
        var objeto = Cliente.VOToObject(cliente);
        if (clienteRepository.existsByCpf(objeto.getCpf())) {
            throw new BadRequestException("cpf já cadastrado no sistema.");
        }
        if (clienteRepository.existsByEmail(objeto.getEmail())) {
            throw new BadRequestException("email já cadastrado no sistema.");
        }
        Cliente client = clienteRepository.save(objeto);

        var result = ClienteVO.toVO(client);
        addHateoasLinks(result.getId(), result.getCpf(), result.getEmail(), result);
        return result;
    }

    public ClienteVO findById(Long id)
    {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente com id: " + id + " não encontrado")
        );
        var result = ClienteVO.toVO(cliente);


        addHateoasLinks(id, result.getCpf(), result.getEmail(), result);
        return result;
    }

    public ClienteVO update(ClienteVO clienteAtualizado)
    {

        Cliente entity = clienteRepository.findById(clienteAtualizado.getId()).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado."));
        entity.setNome(clienteAtualizado.getNome());
        entity.setEmail(clienteAtualizado.getEmail());
        entity.setEndereco(clienteAtualizado.getEndereco());
        entity.setSenha(clienteAtualizado.getSenha());

        var result = clienteRepository.save(entity);
        var result2 = ClienteVO.toVO(result);
        addHateoasLinks(result2.getId(), result2.getCpf(), result2.getEmail(), result2);
        return result2;
    }

    public void delete(Long id)
    {
        ClienteVO cliente = findById(id);

        clienteRepository.deleteById(id);

    }

    public ClienteVO findByEmail(String email)
    {
        if (!clienteRepository.existsByEmail(email)) {
            throw new ClienteNotFoundException("Cliente não encontrado.");
        }
        Cliente cliente = clienteRepository.findByEmail(email);

        var result = ClienteVO.toVO(cliente);
        addHateoasLinks(result.getId(), result.getCpf(), result.getEmail(), result);
        return result;
    }

    public ClienteVO findByCpf(String cpf)
    {
        if (!clienteRepository.existsByCpf(cpf)) {
            throw new ClienteNotFoundException("Cpf não encontrado.");
        }
        Cliente cliente = clienteRepository.findByCpf(cpf);

        return ClienteVO.toVO(cliente);
    }


    private static void addHateoasLinks(Long id, String cpf, String email, ClienteVO result)
    {
        result.add(linkTo(methodOn(ClienteController.class).findById(id)).withRel("findById").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).findByCpf(cpf)).withRel("findByCpf").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).findByEmail(email)).withRel("findByEmail").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).delete(id)).withRel("delete").withType("DELETE"));
        result.add(linkTo(methodOn(ClienteController.class).findAll()).withRel("findAll").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).create(result)).withRel("create").withType("POST"));
        result.add(linkTo(methodOn(ClienteController.class).update(result)).withRel("update").withType("PUT"));
    }


}
