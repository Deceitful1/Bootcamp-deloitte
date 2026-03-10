package com.gablins.entities;



import Semana1.exceptions.InvalidCPFFormatException;
import Semana1.exceptions.InvalidEmailFormatException;
import com.gablins.DTOs.ClienteVO;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "clientes")
public class Cliente
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 11, name = "cpf")
    public String cpf;
    @Column(nullable = false, name = "nome")
    public String nome;
    @Column(name = "endereco")
    public String endereco;
    @Column(nullable = false, name = "senha")
    public String senha;
    @Column(nullable = false, name = "email")
    public String email;



    public Cliente()
    {
    }

    public Cliente(String cpf, String nome, String endereco, String senha, String email)
    {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.senha = senha;
        this.email = email;
    }

    public String getCpf()
    {
        return cpf;
    }


    public String getNome()
    {
        return nome;
    }

    public void setNome(String name)
    {
        this.nome = name;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Long getId()
    {
        return id;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

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


    @Override
    public String toString()
    {
        return "Cliente{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(cpf);
    }


}