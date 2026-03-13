package com.gablins.DTOs.mapper;

import com.gablins.DTOs.ClienteVO;
import com.gablins.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClientVOToEntityMapper {
    //Converter VO para object
    public static Cliente VOToObject(ClienteVO clienteVO)
    {
        return new Cliente(clienteVO.getCpf(), clienteVO.getNome(), clienteVO.getEndereco(), clienteVO.getSenha(), clienteVO.getEmail());
    }

    public static List<Cliente> VOToOjectList(List<ClienteVO> clientesVO)
    {
        List<Cliente> clientes = new ArrayList<>();
        for(ClienteVO clienteVO : clientesVO)
        {
            clientes.add(VOToObject(clienteVO));
        }
        return clientes;
    }
}
