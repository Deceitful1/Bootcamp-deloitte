package Semana1;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {

        //Verificar se o loop deve ser executado novamente
        boolean isDone = true;

        Scanner sc = new Scanner(System.in);
        Cliente cliente = new Cliente();


        System.out.println("Bem vindo a loja x!");
        while (isDone) {

            System.out.println(cliente.clientes);


            System.out.println("O que gostaria de saber?");


            System.out.println("-----------------------------------------------");
            System.out.println("Opção 1: Cadastro");
            System.out.println("Opção 2: Atualizar informações");
            System.out.println("Opção 3: Consultar informação");
            System.out.println("Opção 4: Apagar informação");
            System.out.println("Opção 5: sair");
            System.out.println("-----------------------------------------------");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    cliente.cadastro();

                    break;

                case 2:
                    cliente.atualizarCadastro();
                    break;
                case 3:
                    System.out.println(cliente.consultar());
                    sc.nextLine();

                    break;
                case 4:
                    cliente.deletar();


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
