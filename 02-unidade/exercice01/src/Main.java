import classes.Produto;
import classes.ProdutoEletronico;
import enums.CategoriaProduto;
import util.GerenciadorProdutos;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorProdutos gerenciador = new GerenciadorProdutos();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Buscar produto");
            System.out.println("3. Atualizar produto");
            System.out.println("4. Excluir produto");
            System.out.println("5. Listar produtos por categoria");
            System.out.println("6. Listar todos os produtos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço do produto: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Categoria (ALIMENTOS, ELETRONICOS, ROUPAS, BELEZA, SAUDE): ");
                    CategoriaProduto categoria = CategoriaProduto.valueOf(scanner.nextLine().toUpperCase());
                    Produto produto;

                    if (categoria == CategoriaProduto.ELETRONICOS) {
                        System.out.print("Prazo de Garantia (AAAA-MM-DD): ");
                        LocalDate prazoDeGarantia = LocalDate.parse(scanner.nextLine());
                        System.out.print("Marca: ");
                        String marca = scanner.nextLine();
                        produto = new ProdutoEletronico(nome, preco, categoria, prazoDeGarantia, marca);
                    } else {
                        produto = new Produto(nome, preco, categoria);
                    }
                    gerenciador.adicionarProduto(produto);
                    System.out.println("Produto adicionado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    Produto produto = gerenciador.buscarProduto(nome);
                    System.out.println(produto != null ? produto : "Produto não encontrado.");
                }
                case 3 -> {
                    System.out.print("Nome do produto a atualizar: ");
                    String nome = scanner.nextLine();
                    Produto produtoAtual = gerenciador.buscarProduto(nome);
                    if (produtoAtual != null) {
                        System.out.print("Preço do produto: ");
                        double preco = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Categoria (ALIMENTOS, ELETRONICOS, ROUPAS, BELEZA, SAUDE): ");
                        CategoriaProduto categoria = CategoriaProduto.valueOf(scanner.nextLine().toUpperCase());
                        Produto novoProduto;

                        if (categoria.equals(CategoriaProduto.ELETRONICOS)) {
                            System.out.print("Prazo de Garantia (AAAA-MM-DD): ");
                            LocalDate prazoDeGarantia = LocalDate.parse(scanner.nextLine());
                            System.out.print("Marca: ");
                            String marca = scanner.nextLine();
                            novoProduto = new ProdutoEletronico(nome, preco, categoria, prazoDeGarantia, marca);
                        } else {
                            novoProduto = new Produto(nome, preco, categoria);
                        }
                        gerenciador.atualizarProduto(produtoAtual, novoProduto);
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("Nome do produto a excluir: ");
                    String nome = scanner.nextLine();
                    boolean excluido = gerenciador.excluirProduto(nome);
                    System.out.println(excluido ? "Produto excluído com sucesso!" : "Produto não encontrado.");
                }
                case 5 -> {
                    System.out.print("Categoria (ALIMENTOS, ELETRONICOS, ROUPAS, BELEZA, SAUDE): ");
                    CategoriaProduto categoria = CategoriaProduto.valueOf(scanner.nextLine().toUpperCase());
                    gerenciador.listarProdutosPorCategoria(categoria).forEach(System.out::println);
                }
                case 6 -> gerenciador.listarTodosProdutos().forEach(System.out::println);
                case 0 -> gerenciador.salvarProdutos();
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
