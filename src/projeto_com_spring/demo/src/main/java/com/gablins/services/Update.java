package com.gablins.services;

import com.gablins.DTOs.ClienteVO;
import com.gablins.entities.Cliente;

public class Update
{

    public static void updateClient(Cliente entity, ClienteVO clienteAtualizado)
    {
        entity.setNome(clienteAtualizado.getNome());
        entity.setEmail(clienteAtualizado.getEmail());
        entity.setEndereco(clienteAtualizado.getEndereco());
        entity.setSenha(clienteAtualizado.getSenha());
    }
}
