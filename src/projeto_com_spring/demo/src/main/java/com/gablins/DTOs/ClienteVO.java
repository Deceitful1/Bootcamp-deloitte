package com.gablins.DTOs;

import Semana1.exceptions.InvalidCPFFormatException;
import Semana1.exceptions.InvalidEmailFormatException;
import com.gablins.entities.Cliente;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.gablins.entities.validator.ClienteDataValidator.validateCPF;
import static com.gablins.entities.validator.ClienteDataValidator.validateEmail;

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

    public void setCpf(String cpf) {
        validateCPF(cpf);
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






}
