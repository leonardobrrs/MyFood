package scr.br.ufal.poo;

import easyaccept.EasyAccept;

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

    public String getAtributoUsuario(int id, String atributo) {
        return sistema.getAtributoUsuario(id, atributo);
    }

    public void encerrarSistema() {
        sistema.encerrarSistema();
    }
}
