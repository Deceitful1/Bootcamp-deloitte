package com.gablins.entities;




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