import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Salvar uma pessoa");
            System.out.println("2. Listar todas as pessoas");
            System.out.println("3. Deletar uma pessoa pelo e-mail");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o e-mail: ");
                    String email = scanner.nextLine();
                    Pessoa pessoa = new Pessoa(nome, email);
                    if (pessoaDAO.addPessoa(pessoa)) {
                        System.out.println("Pessoa salva com sucesso!");
                    } else {
                        System.out.println("Erro: Já existe uma pessoa com esse e-mail.");
                    }
                    break;
                case 2:
                    System.out.println("Pessoas cadastradas:");
                    for (Pessoa p : pessoaDAO.getPessoas()) {
                        System.out.println(p);
                    }
                    break;
                case 3:
                    System.out.print("Digite o e-mail da pessoa a ser deletada: ");
                    String emailToDelete = scanner.nextLine();
                    if (pessoaDAO.deletePessoaByEmail(emailToDelete)) {
                        System.out.println("Pessoa deletada com sucesso!");
                    } else {
                        System.out.println("Erro: Pessoa não encontrada.");
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
