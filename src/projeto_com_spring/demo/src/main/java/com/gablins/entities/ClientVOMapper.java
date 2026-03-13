package com.gablins.entities;

import com.gablins.DTOs.ClienteVO;

import java.util.ArrayList;
import java.util.List;

public class ClientVOMapper {


    //métodos para transformar as entidades em Value Objects
    public static ClienteVO toVO(Cliente cliente)
    {

        return new ClienteVO(cliente.getId() ,cliente.getCpf(), cliente.getNome(), cliente.getEndereco(), cliente.getSenha(), cliente.getEmail());
    }

    public static List<ClienteVO> toVOList(List<Cliente> clientes)
    {
        List<ClienteVO> clientesList = new ArrayList<>();
        for (Cliente cliente : clientes) {
            clientesList.add(toVO(cliente));
        }
        return clientesList;

    }
}
