package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.*;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileWriter;

public class Sistema {

    private Map<Integer, Usuario> usuarios;
    private Map<String, Usuario> usuariosPorEmail;

    public Sistema() {
        this.usuarios = new HashMap<>();
        this.usuariosPorEmail = new HashMap<>();
    }

    public void zerarSistema(){
        this.usuarios.clear();
        this.usuariosPorEmail.clear();
    }

    ///Criando o usuario cliente
    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException
            , EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, EmailExistenteException {

        if (nome == null || nome.trim().isEmpty()) throw new NomeInvalidoException("Nome invalido");
        if (email == null || !email.contains("@")) throw new EmailInvalidoException("Email invalido");
        if (senha == null || senha.trim().isEmpty()) throw new SenhaInvalidaException("Senha invalido");
        if (endereco == null || endereco.trim().isEmpty())  throw new EnderecoInvalidoException("Endereco invalido");

        if (usuariosPorEmail.containsKey(email)) throw new EmailExistenteException("Conta com esse email ja existe");

        Cliente cliente = new Cliente(nome, email, senha, endereco);
        usuarios.put(cliente.getId(), cliente);
        usuariosPorEmail.put(email, cliente);
    }

    ///Criando o usuario dono
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException
            , EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, CpfInvalidoException, EmailExistenteException {

        if (nome == null || nome.trim().isEmpty()) throw new NomeInvalidoException("Nome invalido");
        if (email == null || !email.contains("@")) throw new EmailInvalidoException("Email invalido");
        if (senha == null || senha.trim().isEmpty()) throw new SenhaInvalidaException("Senha invalido");
        if (endereco == null || endereco.trim().isEmpty()) throw new EnderecoInvalidoException("Endereco invalido");
        if (cpf == null || cpf.length() != 14) throw new CpfInvalidoException("CPF invalido");

        if (usuariosPorEmail.containsKey(email)) throw new EmailExistenteException("Conta com esse email ja existe");

        DonoRestaurante donoRestaurante = new DonoRestaurante(nome, email, senha, endereco, cpf);

        usuarios.put(donoRestaurante.getId(), donoRestaurante);
        usuariosPorEmail.put(email, donoRestaurante);
    }

    public int login(String email, String senha) throws LoginSenhaInvalidosException {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario.getId();
            }
        }
        throw new LoginSenhaInvalidosException("Login ou senha invalidos");
    }

    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException{
        Usuario usuario = usuarios.get(id);
        if (usuario == null) throw new UsuarioNaoCadastradoException("Usuario nao cadastrado.");
        return usuario.getAtributo(atributo);
    }

/*    public void salvarDadosEmCSV(String caminhoArquivo) throws IOException {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            for (Map.Entry<Integer, Usuario> entry : usuarios.entrySet()) {
                Integer id = entry.getKey();
                Usuario usuario = entry.getValue();
                String linha = id + "," + usuario.getNome() + "," + usuario.getEmail() + "\n";
                writer.write(linha);
            }
        }
    }*/

    public void encerrarSistema() throws IOException {
//        salvarDadosEmCSV("usuarios.csv");
    }
}
