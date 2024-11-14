package util;

import classes.Produto;
import enums.CategoriaProduto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {
    private List<Produto> produtos = new ArrayList<>();
    private final String arquivoProdutos = "produtos.dat";

    public GerenciadorProdutos() {
        recuperarProdutos();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto buscarProduto(String nome) {
        return produtos.stream().filter(p -> p.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }

    public boolean atualizarProduto(Produto produtoDesatualizado, Produto produtoAtualizado) {
        if (produtoDesatualizado != null) {
            produtos.set(produtos.indexOf(produtoDesatualizado), produtoAtualizado);
            return true;
        }
        return false;
    }

    public boolean excluirProduto(String nome) {
        Produto produto = buscarProduto(nome);
        if (produto != null) {
            produtos.remove(produto);
            return true;
        }
        return false;
    }

    public List<Produto> listarProdutosPorCategoria(CategoriaProduto categoria) {
        List<Produto> lista = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getCategoria() == categoria) {
                lista.add(p);
            }
        }
        return lista;
    }

    public List<Produto> listarTodosProdutos() {
        return produtos;
    }

    public void salvarProdutos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivoProdutos))) {
            out.writeObject(produtos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public void recuperarProdutos() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoProdutos))) {
            produtos = (List<Produto>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, começando com lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }
    }
}

