package scr.br.ufal.poo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaMyFood {
    private Map<Integer, Usuario> usuarios;
    private Map<String, Usuario> usuariosPorEmail;
    private Map<Integer, Empresa> empresas;
    private Map<Integer, List<Empresa>> empresasPorDono;

    public SistemaMyFood() {
        this.usuarios = new HashMap<>();
        this.usuariosPorEmail = new HashMap<>();
        this.empresas = new HashMap<>();
        this.empresasPorDono = new HashMap<>();
    }

    public void zerarSistema() {
        usuarios.clear();
        usuariosPorEmail.clear();
        empresas.clear();
        empresasPorDono.clear();
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
            throw new IllegalArgumentException("Conta com esse email ja existe");
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

        if (cpf != null && (cpf.length() != 14)) {
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

    public void criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, String tipoCozinha) {
        Usuario dono = usuarios.get(donoId);
        if (dono == null || !(dono instanceof DonoRestaurante)) {
            throw new IllegalArgumentException("Usuario nao pode criar uma empresa");
        }

        if (tipoEmpresa.equals("restaurante")) {
            verificarNomeEndereco(nome, endereco, donoId);
            Restaurante restaurante = new Restaurante(nome, endereco, tipoCozinha);
            empresas.put(restaurante.getId(), restaurante);
            empresasPorDono.computeIfAbsent(donoId, k -> new ArrayList<>()).add(restaurante);
        } else {
            throw new IllegalArgumentException("Tipo de empresa nao suportado");
        }
    }

    private void verificarNomeEndereco(String nome, String endereco, int donoId) {
        List<Empresa> empresasDoDono = empresasPorDono.get(donoId);
        if (empresasDoDono != null) {
            for (Empresa empresa : empresasDoDono) {
                if (empresa.getNome().equals(nome)) {
                    throw new IllegalArgumentException("Empresa com esse nome ja existe");
                }
                if (empresa.getNome().equals(nome) && empresa.getEndereco().equals(endereco)) {
                    throw new IllegalArgumentException("Proibido cadastrar duas empresas com o mesmo nome e local");
                }
            }
        }
    }

    public String getEmpresasDoUsuario(int idDono) {
        List<Empresa> empresasDoDono = empresasPorDono.get(idDono);
        if (empresasDoDono == null) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (Empresa empresa : empresasDoDono) {
            sb.append("[").append(empresa.getNome()).append(", ").append(empresa.getEndereco()).append("], ");
        }
        return "{" + sb.toString().replaceAll(", $", "") + "}";
    }

    public int getIdEmpresa(int idDono, String nome, int indice) {
        List<Empresa> empresasDoDono = empresasPorDono.get(idDono);
        if (empresasDoDono == null || indice < 0 || indice >= empresasDoDono.size()) {
            throw new IllegalArgumentException("Indice maior que o esperado");
        }
        Empresa empresa = empresasDoDono.stream()
                .filter(e -> e.getNome().equals(nome))
                .skip(indice)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nao existe empresa com esse nome"));
        return empresa.getId();
    }

    public String getAtributoEmpresa(int empresaId, String atributo) {
        Empresa empresa = empresas.get(empresaId);
        if (empresa == null) {
            throw new IllegalArgumentException("Empresa nao cadastrada");
        }
        return empresa.getAtributo(atributo);
    }

    public void encerrarSistema() {
        System.out.println("Sistema encerrado.");
    }
}
