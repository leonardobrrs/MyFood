package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.*;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

public class Sistema {

    private Map<Integer, Usuario> usuarios;
    private Map<String, Usuario> usuariosPorEmail;
    private Map<Integer, Restaurante> restaurantes;
    private Map<Integer, List<Restaurante>> restaurantesPorDono;
    private Map<Integer, Integer> empresasPorDono;

    public Sistema() {
        this.usuarios = new HashMap<>();
        this.usuariosPorEmail = new HashMap<>();
        this.restaurantes = new HashMap<>();
        this.restaurantesPorDono = new HashMap<>();
        this.empresasPorDono = new HashMap<>();
    }

    public void zerarSistema(){
        this.usuarios.clear();
        this.usuariosPorEmail.clear();
        this.restaurantes.clear();
        this.restaurantesPorDono.clear();
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

    public int criarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, String tipoCozinha) throws NomeEmpresaExistenteException, EnderecoDuplicadoException, UsuarioNaoAutorizadoException{

        // Verificar se o usuário com o ID fornecido é um DonoRestaurante
        Usuario usuario = usuarios.get(idDono);
        if (usuario == null || !usuario.podeCriarEmpresa()) {
            throw new UsuarioNaoAutorizadoException("Usuario nao pode criar uma empresa");
        }

        // Verificar se o dono já possui uma empresa com o mesmo nome e endereço
        List<Restaurante> empresasDoDono = restaurantesPorDono.get(idDono);
        if (empresasDoDono != null) {
            for (Restaurante restaurante : empresasDoDono) {
                if (restaurante.getNome().equals(nome) && restaurante.getEndereco().equals(endereco)) {
                    throw new EnderecoDuplicadoException("Proibido cadastrar duas empresas com o mesmo nome e local");
                }
            }
        }

        // Verificar se existe uma empresa com o mesmo nome para qualquer dono
        for (Restaurante restaurante : restaurantes.values()) {
            if (restaurante.getNome().equals(nome) && restaurante.getEndereco().equals(endereco)) {
                throw new NomeEmpresaExistenteException("Empresa com esse nome ja existe");
            }
        }

        Restaurante restaurante = new Restaurante(nome, endereco, tipoCozinha);
        restaurantes.put(restaurante.getId(), restaurante);

        // Adicionar o restaurante à lista do dono
        empresasDoDono = restaurantesPorDono.get(idDono);
        if (empresasDoDono == null) {
            empresasDoDono = new ArrayList<>();
            restaurantesPorDono.put(idDono, empresasDoDono);
        }
        empresasDoDono.add(restaurante);

        return restaurante.getId();
    }

    public String getEmpresasDoUsuario(int idDono) throws UsuarioNaoAutorizadoException{

        // Verificar se o usuário com o ID fornecido é um DonoRestaurante
        Usuario usuario = usuarios.get(idDono);
        if (usuario == null || !usuario.podeCriarEmpresa()) {
            throw new UsuarioNaoAutorizadoException("Usuario nao pode criar uma empresa");
        }

        // Obter as empresas do dono
        List<Restaurante> empresasDoDono = restaurantesPorDono.get(idDono);
        if (empresasDoDono == null || empresasDoDono.isEmpty()) {
            return "{[]}"; // Nenhuma empresa encontrada
        }

        // Construir a string com o formato desejado
        StringBuilder resultado = new StringBuilder("{[");
        for (int i = 0; i < empresasDoDono.size(); i++) {
            Restaurante restaurante = empresasDoDono.get(i);
            if (i > 0) {
                resultado.append(", ");
            }
            resultado.append("[")
                    .append(restaurante.getNome())
                    .append(", ")
                    .append(restaurante.getEndereco())
                    .append("]");
        }
        resultado.append("]}");

        return resultado.toString();
    }

    // Método para retornar o ID da empresa a partir do índice
    public int getIdEmpresa(int idDono, String nome, int indice) {
        List<Restaurante> empresas = restaurantesPorDono.get(idDono);
        if (empresas != null && indice >= 0 && indice < empresas.size()) {
            Restaurante restaurante = empresas.get(indice);
            if (restaurante.getNome().equals(nome)) {
                return restaurante.getId();
            }
        }
        throw new IllegalArgumentException("Nao existe empresa com esse nome");
    }

    // Método para retornar o valor de um atributo específico da empresa
    public String getAtributoEmpresa(int empresaId, String atributo) {
        Restaurante restaurante = restaurantes.get(empresaId);
        if (restaurante == null) {
            throw new IllegalArgumentException("Empresa nao cadastrada");
        }

        if (atributo.equals("dono")) {
            // Itera sobre o mapa de donos e verifica quem possui esse restaurante
            for (Map.Entry<Integer, List<Restaurante>> entry : restaurantesPorDono.entrySet()) {
                List<Restaurante> restaurantesDoDono = entry.getValue();
                for (Restaurante r : restaurantesDoDono) {
                    if (r.getId() == empresaId) {
                        // Retorna o nome do dono
                        return usuarios.get(entry.getKey()).getNome();
                    }
                }
            }
        }

        return restaurante.getAtributo(atributo);
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
