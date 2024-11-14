package model;

public class Revista implements Material {
    private String titulo;
    private String editora;
    private int numeroPaginas;

    public Revista() { }

    public Revista(String titulo, String editora, int numeroPaginas) {
        this.titulo = titulo;
        this.editora = editora;
        this.numeroPaginas = numeroPaginas;
    }

    public String getTitulo() { return this.titulo; }
    public String getEditora() { return this.editora; }
    public int getNumeroPaginas() { return this.numeroPaginas; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setEditora(String editora) { this.editora = editora; }
    public void setNumeroPaginas(int numeroPaginas) { this.numeroPaginas = numeroPaginas; }

    @Override
    public void exibirDetalhes() {
        System.out.println(" - - - - - - - - - - - - - - - - - - ");
        System.out.println("Título: " + this.getTitulo());
        System.out.println("Editora: " + this.getEditora());
        System.out.println("Numero Paginas: " + this.getNumeroPaginas());
    }
}