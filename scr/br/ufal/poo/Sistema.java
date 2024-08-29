package scr.br.ufal.poo;

import java.util.HashMap;
import java.util.Map;

public class Sistema {
    private Map<Integer, Usuario> usuarios = new HashMap<>();
    private Map<String, Usuario> emailParaUsuario = new HashMap<>();

    public void zerarSistema() {
        usuarios.clear();
        emailParaUsuario.clear();
    }

    public String getAtributoUsuario(int id, String atributo) {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) throw new IllegalArgumentException("Usuario nao cadastrado.");

        switch (atributo) {
            case "nome":
                return usuario.getNome();
            case "email":
                return usuario.getEmail();
            case "endereco":
                if (usuario instanceof Cliente) {
                    return ((Cliente) usuario).getEndereco();
                } else if (usuario instanceof DonoRestaurante) {
                    return ((DonoRestaurante) usuario).getEndereco();
                }
                break;
            case "cpf":
                if (usuario instanceof DonoRestaurante) {
                    return ((DonoRestaurante) usuario).getCpf();
                }
                break;
            default:
                throw new IllegalArgumentException("Atributo invalido.");
        }
        return null;
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) {
        if (emailParaUsuario.containsKey(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }
        Cliente cliente = new Cliente(nome, email, senha, endereco);
        usuarios.put(cliente.getId(), cliente);
        emailParaUsuario.put(email, cliente);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        if (emailParaUsuario.containsKey(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }
        if (cpf.length() != 14) {
            throw new IllegalArgumentException("CPF invalido");
        }
        DonoRestaurante donoRestaurante = new DonoRestaurante(nome, email, senha, endereco, cpf);
        usuarios.put(donoRestaurante.getId(), donoRestaurante);
        emailParaUsuario.put(email, donoRestaurante);
    }

    public int login(String email, String senha) {
        Usuario usuario = emailParaUsuario.get(email);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Login ou senha invalidos");
        }
        return usuario.getId();
    }

    public void encerrarSistema() {
        System.exit(0);
    }
}
