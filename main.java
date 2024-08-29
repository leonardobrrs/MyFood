import easyaccept.EasyAccept;

public class main {
    public static void main(String[] args) {
        // Caminho para a classe de fachada e para os arquivos de teste
        args = new String[] {
                "Facade",                          // Nome da classe de fachada
                "testes/UserStory1.txt"             // Caminho relativo para o arquivo de teste
        };
        EasyAccept.main(args);
    }
}
