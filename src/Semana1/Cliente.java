package Semana1;

import java.util.Objects;

public class Cliente {
    private String cpf;
    private String nome;
    private String endereco;
    private String senha;
    private String email;
    private int idade;

    public Cliente()
    {}

    public Cliente(String cpf, String nome, String endereco, String senha, String email, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.senha = senha;
        this.email = email;
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}

