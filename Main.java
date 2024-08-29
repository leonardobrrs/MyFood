import easyaccept.EasyAccept;

public class Main {  // Nome da classe alterado para 'Main'
    public static void main(String[] args) {
        // Caminho para a classe de fachada e para os arquivos de teste
        args = new String[] {
                "scr.br.ufal.poo.Facade",   // Nome completo da classe de fachada, incluindo o pacote
                "testes/us1_1.txt",
                "testes/us1_2.txt",// Caminho relativo para o arquivo de teste
                "testes/us2_1.txt"
        };
        EasyAccept.main(args);
    }
}
