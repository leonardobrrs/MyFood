package scr.br.ufal.poo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaMyFood {
    private Map<Integer, Usuario> usuarios;
    private Map<String, Usuario> usuariosPorEmail;
    private Map<String, List<Restaurante>> restaurantesPorNome; // Alterado para Map<String, List<Restaurante>>
    private Map<Integer, Restaurante> restaurantesPorId;

    public SistemaMyFood() {
        this.usuarios = new HashMap<>();
        this.usuariosPorEmail = new HashMap<>();
        this.restaurantesPorNome = new HashMap<>();
        this.restaurantesPorId = new HashMap<>();
    }


    public void zerarSistema() {
        usuarios.clear();
        usuariosPorEmail.clear();
        restaurantesPorNome.clear();
        restaurantesPorId.clear();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) {
        validarDados(nome, email, senha, endereco, null);

        if (usuariosPorEmail.containsKey(email)) {
            throw new IllegalArgumentException("Conta com esse email ja existe");
        }

        Cliente cliente = new Cliente(nome, email, senha, endereco);
        usuarios.put(cliente.getId(), cliente);
        usuariosPorEmail.put(email, cliente);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        validarDados(nome, email, senha, endereco, cpf);

        if (usuariosPorEmail.containsKey(email)) {
            throw new IllegalArgumentException("CPF invalido");
        }

        DonoRestaurante dono = new DonoRestaurante(nome, email, senha, endereco, cpf);
        usuarios.put(dono.getId(), dono);
        usuariosPorEmail.put(email, dono);
    }

    private void validarDados(String nome, String email, String senha, String endereco, String cpf) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email invalido");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha invalido");
        }
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereco invalido");
        }
        if (cpf != null && cpf.length() != 14) {
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

    public int criarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, String tipoCozinha) {
        if (!tipoEmpresa.equals("restaurante")) {
            throw new IllegalArgumentException("Tipo de empresa invalido");
        }

        Usuario usuario = usuarios.get(idDono);
        if (usuario == null || !(usuario instanceof DonoRestaurante)) {
            throw new IllegalArgumentException("Usuario nao pode criar uma empresa");
        }

        List<Restaurante> restaurantesExistentes = restaurantesPorNome.getOrDefault(nome, new ArrayList<>());
        for (Restaurante r : restaurantesExistentes) {
            if (r.getEndereco().equals(endereco)) {
                throw new IllegalArgumentException("Proibido cadastrar duas empresas com o mesmo nome e local");
            }
        }

        Restaurante restaurante = new Restaurante(nome, endereco, tipoCozinha, idDono);
        restaurantesPorNome.computeIfAbsent(nome, k -> new ArrayList<>()).add(restaurante);
        restaurantesPorId.put(restaurante.getId(), restaurante);
        return restaurante.getId();
    }

    public String getEmpresasDoUsuario(int idDono) {
        Usuario usuario = usuarios.get(idDono);
        if (usuario == null || !(usuario instanceof DonoRestaurante)) {
            throw new IllegalArgumentException("Usuario nao pode criar uma empresa");
        }

        List<String> restaurantesDoDono = new ArrayList<>();
        for (Restaurante r : restaurantesPorId.values()) {
            if (r.getDonoId() == idDono) {
                restaurantesDoDono.add("[" + r.getNome() + ", " + r.getEndereco() + "]");
            }
        }
        return restaurantesDoDono.toString();
    }

    public String getAtributoEmpresa(int idEmpresa, String atributo) {
        Restaurante restaurante = restaurantesPorId.get(idEmpresa);
        if (restaurante == null) {
            throw new IllegalArgumentException("Empresa nao cadastrada");
        }

        switch (atributo) {
            case "nome":
                return restaurante.getNome();
            case "endereco":
                return restaurante.getEndereco();
            case "tipoCozinha":
                return restaurante.getTipoCozinha();
            case "dono":
                return usuarios.get(restaurante.getDonoId()).getNome();
            default:
                throw new IllegalArgumentException("Atributo invalido");
        }
    }

    public int getIdEmpresa(int idDono, String nome, int indice) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }

        List<Restaurante> restaurantes = restaurantesPorNome.getOrDefault(nome, new ArrayList<>());
        List<Restaurante> filtrados = new ArrayList<>();

        for (Restaurante r : restaurantes) {
            if (r.getDonoId() == idDono) {
                filtrados.add(r);
            }
        }

        if (indice < 0 || indice >= filtrados.size()) {
            throw new IllegalArgumentException(indice < 0 ? "Indice invalido" : "Indice maior que o esperado");
        }

        return filtrados.get(indice).getId();
    }

    public void encerrarSistema() {
        System.out.println("Sistema encerrado.");
    }
}
