package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Facade {
    private Sistema sistema;

    public Facade() {
        sistema = new Sistema();
    }

    public void zerarSistema(){
        sistema.zerarSistema();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco)  throws NomeInvalidoException
            , EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, EmailExistenteException {
        sistema.criarUsuario(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf)  throws NomeInvalidoException
            , EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, CpfInvalidoException, EmailExistenteException {
        sistema.criarUsuario(nome, email, senha, endereco, cpf);
    }

    public int login(String email, String senha) throws LoginSenhaInvalidosException{
        return sistema.login(email, senha);
    }

    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException{
        return sistema.getAtributoUsuario(id, atributo);
    }

/*    public void salvarDadosEmCSV(String caminhoArquivo) throws IOException {
        sistema.salvarDadosEmCSV(caminhoArquivo);
    }*/

    public void encerrarSistema() throws IOException {
/*        sistema.encerrarSistema();*/
    }
}
