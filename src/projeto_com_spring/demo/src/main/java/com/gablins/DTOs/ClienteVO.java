package com.gablins.DTOs;

import Semana1.exceptions.InvalidCPFFormatException;
import Semana1.exceptions.InvalidEmailFormatException;
import com.gablins.entities.Cliente;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteVO extends RepresentationModel<ClienteVO>
{

    private Long id;
    private String cpf;
    private String nome;
    private String endereco;
    private String senha;
    private String email;


    public ClienteVO()
    {
    }

    public ClienteVO(Long id,String cpf, String nome, String endereco, String senha, String email)
    {
        this.id = id;
        validateCPF(cpf);
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.senha = senha;
        validateEmail(email);
        this.email = email;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCpf()
    {
        return cpf;
    }


    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        validateEmail(email);
        this.email = email;
    }





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


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ClienteVO clienteVO)) return false;
        return Objects.equals(getId(), clienteVO.getId()) && Objects.equals(getCpf(), clienteVO.getCpf());
    }



    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getCpf());
    }


//validação de dados confidenciais
    public boolean validateCPF(String cpf)
    {

        if (!cpf.matches("\\d{11}")) {
            throw new InvalidCPFFormatException("CPF invalido!");

        }

        return true;
    }

    public boolean validateEmail(String email)
    {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(regex)) {
            throw new InvalidEmailFormatException("Formato de email inválido!");
        }
        return true;
    }



}
