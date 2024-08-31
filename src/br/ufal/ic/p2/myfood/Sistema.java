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
    private Map<Integer, Produto> produtos;
    private Map<Integer, List<Produto>> produtosPorRestaurante;

    public Sistema() {
        this.usuarios = new HashMap<>();
        this.usuariosPorEmail = new HashMap<>();
        this.restaurantes = new HashMap<>();
        this.restaurantesPorDono = new HashMap<>();
        this.produtos = new HashMap<>();
        this.produtosPorRestaurante = new HashMap<>();
    }

    public void zerarSistema(){
        this.usuarios.clear();
        this.usuariosPorEmail.clear();
        this.restaurantes.clear();
        this.restaurantesPorDono.clear();
        this.produtos.clear();
        this.produtosPorRestaurante.clear();
    }

    ///Criando o usuario cliente
    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException
            , EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, EmailExistenteException {

        if (nome == null || nome.trim().isEmpty()) throw new NomeInvalidoException();
        if (email == null || !email.contains("@")) throw new EmailInvalidoException();
        if (senha == null || senha.trim().isEmpty()) throw new SenhaInvalidaException();
        if (endereco == null || endereco.trim().isEmpty())  throw new EnderecoInvalidoException();

        if (usuariosPorEmail.containsKey(email)) throw new EmailExistenteException();

        Cliente cliente = new Cliente(nome, email, senha, endereco);
        usuarios.put(cliente.getId(), cliente);
        usuariosPorEmail.put(email, cliente);
    }

    ///Criando o usuario dono
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException
            , EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, CpfInvalidoException, EmailExistenteException {

        if (nome == null || nome.trim().isEmpty()) throw new NomeInvalidoException();
        if (email == null || !email.contains("@")) throw new EmailInvalidoException();
        if (senha == null || senha.trim().isEmpty()) throw new SenhaInvalidaException();
        if (endereco == null || endereco.trim().isEmpty()) throw new EnderecoInvalidoException();
        if (cpf == null || cpf.length() != 14) throw new CpfInvalidoException();

        if (usuariosPorEmail.containsKey(email)) throw new EmailExistenteException();

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
        throw new LoginSenhaInvalidosException();
    }

    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException, AtributoInvalidoException{
        Usuario usuario = usuarios.get(id);
        if (usuario == null) throw new UsuarioNaoCadastradoException();
        return usuario.getAtributo(atributo);
    }

    public int criarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, String tipoCozinha) throws NomeEmpresaExistenteException, EnderecoDuplicadoException, UsuarioNaoAutorizadoException{

        // Verificar se o usuário com o ID fornecido é um DonoRestaurante
        Usuario usuario = usuarios.get(idDono);
        if (usuario == null || !usuario.podeCriarEmpresa()) {
            throw new UsuarioNaoAutorizadoException();
        }

        // Verificar se o dono já possui uma empresa com o mesmo nome e endereço
        List<Restaurante> empresasDoDono = restaurantesPorDono.get(idDono);
        if (empresasDoDono != null) {
            for (Restaurante restaurante : empresasDoDono) {
                if (restaurante.getNome().equals(nome) && restaurante.getEndereco().equals(endereco)) {
                    throw new EnderecoDuplicadoException();
                }
            }
        }

        // Verificar se existe uma empresa com o mesmo nome para qualquer dono
        for (Restaurante restaurante : restaurantes.values()) {
            if (restaurante.getNome().equals(nome) && restaurante.getEndereco().equals(endereco)) {
                throw new NomeEmpresaExistenteException();
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
            throw new UsuarioNaoAutorizadoException();
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
    public int getIdEmpresa(int idDono, String nome, int indice) throws NomeInvalidoException,
            NomeEmpresaNaoExisteException, IndiceInvalidoException, IndiceMaiorException {
        // Verifica se o nome é válido
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }

        // Obtém a lista de empresas do dono
        List<Restaurante> empresas = restaurantesPorDono.get(idDono);

        // Verifica se a lista de empresas é nula ou vazia
        if (empresas == null || empresas.isEmpty()) {
            throw new NomeEmpresaNaoExisteException();
        }

        // Verifica se o índice é negativo
        if (indice < 0) {
            throw new IndiceInvalidoException();
        }

        // Cria uma lista para armazenar os IDs das empresas que correspondem ao nome fornecido
        List<Integer> idsCorrespondentes = new ArrayList<>();

        // Itera sobre a lista de empresas para encontrar todas as empresas com o nome fornecido
        for (Restaurante restaurante : empresas) {
            if (restaurante.getNome().equals(nome)) {
                idsCorrespondentes.add(restaurante.getId());
            }
        }

        // Verifica se há empresas com o nome fornecido
        if (idsCorrespondentes.isEmpty()) {
            throw new NomeEmpresaNaoExisteException();
        }

        // Verifica se o índice é maior do que o número de empresas encontradas com o nome fornecido
        if (indice >= idsCorrespondentes.size()) {
            throw new IndiceMaiorException();
        }

        // Retorna o ID da empresa no índice fornecido
        return idsCorrespondentes.get(indice);
    }



    // Método para retornar o valor de um atributo específico da empresa
    public String getAtributoEmpresa(int empresaId, String atributo) throws EmpresaNaoCadastradaException, AtributoInvalidoException{
        if(atributo == null){
            throw new AtributoInvalidoException();
        }

        Restaurante restaurante = restaurantes.get(empresaId);
        if (restaurante == null) {
            throw new EmpresaNaoCadastradaException();
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

    public int criarProduto(int empresa, String nome, float valor, String categoria) throws NomeProdutoExisteException, NomeInvalidoException, ValorInvalidoException, CategoriaInvalidaException{

        if (nome == null || nome.trim().isEmpty()) throw new NomeInvalidoException();
        if (categoria == null || categoria.trim().isEmpty()) throw new CategoriaInvalidaException();
        if (valor <= 0) throw new ValorInvalidoException();

        List<Produto> produtosDoRestaurante = produtosPorRestaurante.get(empresa);
        if (produtosDoRestaurante != null) {
            for (Produto produto : produtosDoRestaurante) {
                if (produto.getNome().equals(nome)) {
                    throw new NomeProdutoExisteException();
                }
            }
        }


        Produto produto = new Produto(nome, valor, categoria);

        // Adicionar o restaurante à lista do dono
        produtosDoRestaurante = produtosPorRestaurante.get(empresa);
        if (produtosDoRestaurante == null) {
            produtosDoRestaurante = new ArrayList<>();
            produtosPorRestaurante.put(empresa, produtosDoRestaurante);
        }

        produtosDoRestaurante.add(produto);

        return produto.getId();
    }

    public void editarProduto(int produto, String nome, float valor, String categoria){


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
