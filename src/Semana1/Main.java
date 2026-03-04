package Semana1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {

        //Verificar se o loop deve ser executado novamente
        boolean isDone = true;

        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();


        System.out.println("Bem vindo a loja x!");
        while(isDone)
        {

            System.out.println("O que gostaria de saber?");


            System.out.println("-----------------------------------------------");
            System.out.println("Opção 1: Cadastro");
            System.out.println("Opção 2: Atualizar informações");
            System.out.println("Opção 3: Consultar informação");
            System.out.println("Opção 4: Apagar informação");
            System.out.println("Opção 5: sair");
            System.out.println("-----------------------------------------------");

            int option = sc.nextInt();

            switch (option)
            {
                case 1:
                    System.out.println("Insira suas informações:");
                    sc.nextLine();

                    System.out.println("Cpf:");
                    String cpf = sc.nextLine();

                    System.out.println("Nome:");
                    String nome = sc.nextLine();

                    System.out.println("Email:");
                    String email = sc.nextLine();

                    System.out.println("Senha:");
                    String senha = sc.nextLine();

                    System.out.println("Endereço:");
                    String endereco = sc.nextLine();

                    System.out.println("Idade:");
                    int idade = sc.nextInt();

                    cliente = new Cliente(cpf,nome,endereco,senha,email,idade);
                    clientes.add(cliente);



                    System.out.println("Cliente cadastrado!");
                    sc.nextLine();

                    System.out.println("----------------------------------------------------------------");

                    break;

                    case 2:
                        if (cliente == null)
                        {
                            throw new RuntimeException("Erro: precisa estar logado para alterar suas informações.");
                        }

                        System.out.println(cliente);
                        System.out.println("qual informação você deseja alterar? insira o nome do campo");
                        sc.nextLine();

                        String resposta = sc.nextLine();

                        switch (resposta)
                        {
                            case "nome":

                                sc.nextLine();
                                System.out.println("Digite o novo nome:");
                                cliente.setNome(sc.nextLine());

                                break;
                            case "endereco":
                                sc.nextLine();
                                System.out.println("Digite seu novo endereço:");
                                cliente.setEndereco(sc.nextLine());


                                break;
                            case "email":
                                sc.nextLine();
                                System.out.println("Digite seu novo email:");
                                cliente.setEmail(sc.nextLine());


                            case "senha":
                                sc.nextLine();
                                System.out.println("Digite sua nova senha:");
                                cliente.setSenha(sc.nextLine());


                                break;
                             default:
                                 break;


                        }

                    break;
                case 3:
                    if (cliente == null)
                    {
                        throw new RuntimeException("Erro: precisa estar logado para alterar suas informações.");
                    }
                    System.out.println(cliente);
                    sc.nextLine();

                    break;
                case 4:
if (clientes.isEmpty())
{
    throw new RuntimeException("nenhum cliente cadastrado.");
}

sc.nextLine();
                    System.out.println("Insira o cpf");
                    cpf = sc.nextLine();

                    //remover cliente por cpf
                    clientes.removeIf(x -> x.getCpf() == cpf);
                    if (cpf == cliente.getCpf())
                    {
                        cliente = null;
                    }

                    System.out.println("Cliente apagado do banco de dados.");
                    break;
                case 5:
                    System.out.println("Obrigado. volte sempre!");
                    isDone = false;
                    sc.nextLine();
                    break;
            }


        }

    }

}
