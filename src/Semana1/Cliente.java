package Semana1;



import Semana1.exceptions.InvalidCPFFormatException;
import Semana1.exceptions.InvalidEmailFormatException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Cliente
{
    public String cpf;
    public String nome;
    public String endereco;
    public String senha;
    public String email;
    public int idade;
    Set<Cliente> clientes = new HashSet<>();

    //atributos utilitarios
    Scanner sc = new Scanner(System.in);

    public Cliente()
    {
    }

    public Cliente(String cpf, String nome, String endereco, String senha, String email, int idade)
    {

        this.cpf = cpf;

        this.nome = nome;
        this.endereco = endereco;
        this.senha = senha;
        this.email = email;
        this.idade = idade;
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

    public int getIdade()
    {
        return idade;
    }

    public void setIdade(int idade)
    {
        this.idade = idade;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public void cadastro()
    {
        try {
            System.out.println("Insira suas informações:");
            System.out.println("");

            System.out.println("Cpf: (Apenas números)");
            String cpfInput = sc.nextLine();
            if (validateCPF(cpfInput)) {
                this.cpf = cpfInput;
            }


            System.out.println("Nome:");
            this.nome = sc.nextLine();

            System.out.println("Email:");
            String emailInput = sc.nextLine();
            if (validateEmail(emailInput)) {
                this.email = emailInput;
            }

            System.out.println("Senha:");
            this.senha = sc.nextLine();

            System.out.println("Endereço:");
            this.endereco = sc.nextLine();

            System.out.println("Idade:");
            this.idade = sc.nextInt();

            System.out.println("Cliente cadastrado!");
            clientes.add(this);
            sc.nextLine();

            System.out.println("----------------------------------------------------------------");
        } catch (InvalidCPFFormatException e) {
            System.out.println("CPF invalido!");
        }
        catch (InvalidEmailFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarCadastro()
    {
        if (this.cpf == null) {
            throw new RuntimeException("Operação inválida: Você não está cadastrado.");
        }

        try {
            System.out.println("qual informação você deseja alterar? insira o nome do campo");


            String resposta = sc.nextLine();

            switch (resposta) {
                case "nome":

                    sc.nextLine();
                    System.out.println("Digite o novo nome:");
                    this.setNome(sc.nextLine());

                    break;
                case "endereco":
                    sc.nextLine();
                    System.out.println("Digite seu novo endereço:");
                    this.setEndereco(sc.nextLine());

                    break;
                case "email":
                    sc.nextLine();
                    System.out.println("Digite seu novo email:");
                    String emailInput = sc.nextLine();
                    if (validateEmail(emailInput)) {
                        this.setEmail(emailInput);
                    }

                    break;


                case "senha":
                    sc.nextLine();
                    System.out.println("Digite sua nova senha:");
                    this.setSenha(sc.nextLine());

                    break;
                default:
                    break;


            }
        }
        catch (InvalidCPFFormatException e) {
            System.out.println("CPF invalido!");
        }
        catch (InvalidEmailFormatException e) {
            System.out.println(e.getMessage());
        }


    }

    public Cliente consultar()
    {
        return this;
    }

    public void deletar()
    {
        if (clientes.isEmpty()) {
            throw new RuntimeException("nenhum cliente cadastrado.");
        }

        sc.nextLine();
        System.out.println("Insira o cpf");
        cpf = sc.nextLine();

        //remover cliente por cpf
        clientes.removeIf(x -> x.getCpf().equals(cpf));

        System.out.println("Cliente apagado do banco de dados.");
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
                ", idade=" + idade +
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