package utils;

import DAO.BibliotecaDAO;
import model.Livro;
import model.Material;
import model.Revista;

import java.sql.SQLOutput;
import java.util.ArrayList;

public abstract class BibliotecaFunctions {
    private static BibliotecaDAO bibliotecaDAO = BibliotecaDAO.getIntance();

    private static Material buscaPorNome(String titulo) {
        ArrayList<Material> materiais =  bibliotecaDAO.getArrayMateriais();

        for (Material material : materiais) {
            if (material instanceof Revista) {
                if (((Revista) material).getTitulo().equals(titulo)) {
                    System.out.println(titulo + " - " + ((Revista) material).getTitulo());
                    return material;
                }
            }

            if (material instanceof Livro) {
                if (((Livro) material).getTitulo().equals(titulo)) {
                    return material;
                }
            }
        }
        return null;
    }

    public static void exibirListaMateriais() {
        bibliotecaDAO.getArrayMateriais().forEach((element) -> {
            if (element instanceof Revista) { ((Revista) element).exibirDetalhes(); }
            if (element instanceof Livro) { ((Livro) element).exibirDetalhes(); }
        });
        System.out.println("====================================================");
    }

    public static void pesquisarMaterial(String titulo) {
        Material element = buscaPorNome(titulo);

        if (element == null) {
            System.out.println("\n+++++++++++++++++++");
            System.out.println("Não encontrado!");
            System.out.println("+++++++++++++++++++\n");
            return;
        }

        System.out.println("- > (Material encontado) < -");
        if (element instanceof Revista) { ((Revista) element).exibirDetalhes(); }
        if (element instanceof Livro) { ((Livro) element).exibirDetalhes(); }
        System.out.println("====================================================");
    }

    public static void cadastarMaterial(String titulo, String autor, int anoLancamento, String edicao) {
        bibliotecaDAO.getArrayMateriais().add(new Livro(titulo, autor, anoLancamento, edicao));
    }

    public static void cadastarMaterial(String titulo, String editora, int numeroPaginas) {
        bibliotecaDAO.getArrayMateriais().add(new Revista(titulo, editora, numeroPaginas));
    }

    public static void removerMaterial(String titulo) {
        Material material = buscaPorNome(titulo);

        if (material != null) {
            bibliotecaDAO.getArrayMateriais().remove(material);
            return;
        }

        System.out.println("\n====================================================");
        System.out.println("Erro: Elemento não encontrado!");
        System.out.println("====================================================\n");
    }
}
