package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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

    public int criarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, String tipoCozinha) throws EnderecoDuplicadoException, NomeEmpresaExistenteException, UsuarioNaoAutorizadoException{
        return sistema.criarEmpresa(tipoEmpresa, idDono, nome, endereco, tipoCozinha);
    }

    public String getEmpresasDoUsuario(int idDono) throws UsuarioNaoAutorizadoException{
        return sistema.getEmpresasDoUsuario(idDono);
    }

    public int getIdEmpresa(int idDono, String nome, int indice) {
        return sistema.getIdEmpresa(idDono, nome, indice);
    }

    public String getAtributoEmpresa(int empresaId, String atributo) {
        return sistema.getAtributoEmpresa(empresaId, atributo);
    }

/*    public void salvarDadosEmCSV(String caminhoArquivo) throws IOException {
        sistema.salvarDadosEmCSV(caminhoArquivo);
    }*/

    public void encerrarSistema() throws IOException {
/*        sistema.encerrarSistema();*/
    }
}
