package scr.br.ufal.poo;

public class Facade {
    private SistemaMyFood sistema;

    public Facade() {
        sistema = new SistemaMyFood();
    }

    public void zerarSistema() {
        sistema.zerarSistema();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) {
        sistema.criarUsuario(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        sistema.criarUsuario(nome, email, senha, endereco, cpf);
    }

    public int login(String email, String senha) {
        return sistema.login(email, senha);
    }



    public void criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, String tipoCozinha) {
        sistema.criarEmpresa(tipoEmpresa, donoId, nome, endereco, tipoCozinha);
    }

    public String getEmpresasDoUsuario(int idDono) {
        return sistema.getEmpresasDoUsuario(idDono);
    }

    public int getIdEmpresa(int idDono, String nome, int indice) {
        return sistema.getIdEmpresa(idDono, nome, indice);
    }

    public String getAtributoEmpresa(int empresaId, String atributo) {
        return sistema.getAtributoEmpresa(empresaId, atributo);
    }

    public void encerrarSistema() {
        sistema.encerrarSistema();
    }
}
