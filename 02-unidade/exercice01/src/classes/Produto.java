package classes;

import enums.CategoriaProduto;

import java.io.Serializable;

public class Produto implements Serializable {
    private String nome;
    private double preco;
    private CategoriaProduto categoria;

    public Produto(String nome, double preco, CategoriaProduto categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public String getNome() { return this.nome; }
    public double getPreco() { return this.preco; }
    public CategoriaProduto getCategoria() { return this.categoria; }

    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setCategoria(CategoriaProduto categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "Produto{" + "nome='" + nome + '\'' + ", preco=" + preco + ", categoria=" + categoria + '}';
    }
}

