import utils.*;

public class Main {

    public static void main(String[] args) {
        //Cadastro de livros
        BibliotecaFunctions.cadastarMaterial("Algebra Linear", "Gustavo Antunes", 2017, "4ª ed");
        BibliotecaFunctions.cadastarMaterial("Quimica Fundamental", "Pedro Paulo de Arantes", 2001, "1ª ed");
        BibliotecaFunctions.cadastarMaterial("Linguagem Java - Easy version", "Gustavo Guanabara", 2020, "2ª ed");
        BibliotecaFunctions.cadastarMaterial("Estrutura basica de dados", "Willian Frederico", 1998, "2ª ed");
        BibliotecaFunctions.cadastarMaterial("Fundamentos da educação contemporanea", "Paulo Freire", 1975, "8ª ed");
        

        //Cadastro de revistas
        BibliotecaFunctions.cadastarMaterial("Epoca", "Globo", 45);
        BibliotecaFunctions.cadastarMaterial("Galineu", "Abreu", 39);
        BibliotecaFunctions.cadastarMaterial("Mundo Curioso", "Abril", 20);
        BibliotecaFunctions.cadastarMaterial("SuperInteressante", "Abril", 41);
        BibliotecaFunctions.cadastarMaterial("Cientifica", "UFCR", 41);

        //Lista todas aos Materiais (livros e revistas)
        BibliotecaFunctions.exibirListaMateriais();

        //Pesquisar material - (Achar)
        BibliotecaFunctions.pesquisarMaterial("Linguagem Java - Easy version");
        
        //Pesquisar material - (Não achar)
        BibliotecaFunctions.pesquisarMaterial("Nada para pesquisar");

        //Remove um material
        BibliotecaFunctions.removerMaterial("Linguagem Java - Easy version");

         //Pesquisar material - (Não achar por ter sido deletado)
         BibliotecaFunctions.pesquisarMaterial("Linguagem Java - Easy version");
    }
}
