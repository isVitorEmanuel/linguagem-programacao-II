package model;

public class Livro implements Material {
    private String titulo;
    private String autor;
    private int anoLancamento;
    private String edicao;

    public Livro() {  }

    public Livro(String titulo, String autor, int anoLancamento, String edicao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoLancamento = anoLancamento;
        this.edicao = edicao;
    }

    public String getTitulo() { return this.titulo; }
    public String getAutor() { return this.autor; }
    public int getAnoLancamento() { return this.anoLancamento; }
    public String getEdicao() { return this.edicao; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }
    public void setEdicao(String edicao) { this.edicao = edicao; }

    @Override
    public void exibirDetalhes() {
        System.out.println(" - - - - - - - - - - - - - - - - - - ");
        System.out.println("Título: " + this.getTitulo());
        System.out.println("Autor: " + this.getAutor());
        System.out.println("Ano Lancamento: " + this.getAnoLancamento());
        System.out.println("Edicao: " + this.getEdicao());
    }
}




















