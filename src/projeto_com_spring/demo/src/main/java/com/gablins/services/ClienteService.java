package com.gablins.services;

import com.gablins.DTOs.ClienteVO;
import com.gablins.entities.Cliente;
import com.gablins.exceptions.BadRequestException;
import com.gablins.exceptions.ClienteNotFoundException;
import com.gablins.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService
{
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteVO> findAll()
    {
        return ClienteVO.toVOList(clienteRepository.findAll());
    }

    public ClienteVO create(ClienteVO cliente)
    {
        var objeto = Cliente.VOToObject(cliente);
       if(clienteRepository.existsByCpf(objeto.getCpf()))
       {
           throw new BadRequestException("cpf já cadastrado no sistema.");
       }
       if(clienteRepository.existsByEmail(objeto.getEmail()))
       {
           throw new BadRequestException("email já cadastrado no sistema.");
       }
        Cliente client = clienteRepository.save(objeto);

        return ClienteVO.toVO(client);
    }

    public ClienteVO findById(Long id)
    {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente com id: " + id + " não encontrado")
        );
        return ClienteVO.toVO(cliente);
    }

    public ClienteVO update(ClienteVO clienteAtualizado)
    {

        Cliente entity = clienteRepository.findById(clienteAtualizado.getId()).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado."));
        entity.setNome(clienteAtualizado.getNome());
        entity.setEmail(clienteAtualizado.getEmail());
        entity.setEndereco(clienteAtualizado.getEndereco());
        entity.setSenha(clienteAtualizado.getSenha());

        var result = clienteRepository.save(entity);
        return ClienteVO.toVO(result);
    }
    public void delete(Long id)
    {
        ClienteVO cliente = findById(id);
        clienteRepository.deleteById(id);

    }


}
