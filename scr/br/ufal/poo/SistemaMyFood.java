package scr.br.ufal.poo;

import java.util.HashMap;
import java.util.Map;

public class SistemaMyFood {
    private Map<Integer, Usuario> usuarios;
    private Map<String, Usuario> usuariosPorEmail;

    public SistemaMyFood() {
        this.usuarios = new HashMap<>();
        this.usuariosPorEmail = new HashMap<>();
    }


    public void zerarSistema() {
        usuarios.clear();
        usuariosPorEmail.clear();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) {
        // Validação dos dados
        validarDados(nome, email, senha, endereco, null);

        // Verificação de email existente
        if (usuariosPorEmail.containsKey(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }

        // Criação do usuário
        Cliente cliente = new Cliente(nome, email, senha, endereco);
        usuarios.put(cliente.getId(), cliente);
        usuariosPorEmail.put(email, cliente);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        // Validação dos dados
        validarDados(nome, email, senha, endereco, cpf);

        // Verificação de email existente
        if (usuariosPorEmail.containsKey(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }

        // Criação do usuário
        DonoRestaurante dono = new DonoRestaurante(nome, email, senha, endereco, cpf);
        usuarios.put(dono.getId(), dono);
        usuariosPorEmail.put(email, dono);
    }

    private void validarDados(String nome, String email, String senha, String endereco, String cpf) {
        // Validação do nome
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }

        // Validação do email
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email invalido");
        }

        // Validação da senha
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha invalido");
        }

        // Validação do endereço
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereco invalido");
        }

        // Validação do CPF, se fornecido
        if (cpf != null && (cpf.length() != 14))  {
            throw new IllegalArgumentException("CPF invalido");
        }
    }


    public int login(String email, String senha) {
        Usuario usuario = usuariosPorEmail.get(email);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Login ou senha invalidos");
        }
        return usuario.getId();
    }

    public String getAtributoUsuario(int id, String atributo) {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario nao cadastrado.");
        }
        return usuario.getAtributo(atributo);
    }

    public void encerrarSistema() {
        System.out.println("Sistema encerrado.");
    }
}

