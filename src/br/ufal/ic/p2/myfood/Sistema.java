package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.*;

import java.util.*;
import java.io.IOException;

public class Sistema {

    private Map<Integer, Usuario> usuarios;
    private Map<String, Usuario> usuariosPorEmail;
    private Map<Integer, Restaurante> restaurantes;
    private Map<Integer, List<Restaurante>> restaurantesPorDono;
    private Map<Integer, Produto> produtos;
    private Map<Integer, List<Produto>> produtosPorRestaurante;
    private Map<Integer, Pedido> pedidos;
    private Map<Integer, List<Pedido>> pedidosPorRestaurante;

    public Sistema() {
        this.usuarios = new HashMap<>();
        this.usuariosPorEmail = new HashMap<>();
        this.restaurantes = new HashMap<>();
        this.restaurantesPorDono = new HashMap<>();
        this.produtos = new HashMap<>();
        this.produtosPorRestaurante = new HashMap<>();
        this.pedidos = new HashMap<>();
        this.pedidosPorRestaurante = new HashMap<>();
    }

    public void zerarSistema(){
        this.usuarios.clear();
        this.usuariosPorEmail.clear();
        this.restaurantes.clear();
        this.restaurantesPorDono.clear();
        this.produtos.clear();
        this.produtosPorRestaurante.clear();
        this.pedidos.clear();
        this.pedidosPorRestaurante.clear();
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
        produtos.put(produto.getId(), produto);

        return produto.getId();
    }

    public void editarProduto(int produto, String nome, float valor, String categoria) throws NomeInvalidoException,
            CategoriaInvalidaException, ValorInvalidoException, ProdutoNaoCadastradoException {

        Produto produto1 = produtos.get(produto);

        if (nome == null || nome.trim().isEmpty()) throw new NomeInvalidoException();
        if (categoria == null || categoria.trim().isEmpty()) throw new CategoriaInvalidaException();
        if (valor <= 0) throw new ValorInvalidoException();

        if (produto1 == null) {
            throw new ProdutoNaoCadastradoException();
        }

        produto1.setNome(nome);
        produto1.setValor(valor);
        produto1.setCategoria(categoria);

    }

    public String getProduto(String nome, int empresa, String atributo) throws AtributoNaoExisteException, ProdutoNaoEncontradoException {
        if (atributo == null) {
            throw new AtributoNaoExisteException();
        }

        List<Produto> produtosDoRestaurante = produtosPorRestaurante.get(empresa);
        if (produtosDoRestaurante == null) {
            throw new ProdutoNaoEncontradoException();
        }

        // Procurar o produto pelo nome dentro da lista de produtos do restaurante
        for (Produto produto : produtosDoRestaurante) {
            if (produto.getNome().equals(nome)) {
                if (atributo.equals("valor")) {
                    return String.format(Locale.US, "%.2f", produto.getValor());
                } else if (atributo.equals("empresa")) {
                    Restaurante restaurante = restaurantes.get(empresa);
                    if (restaurante != null) {
                        return restaurante.getNome();
                    } else {
                        throw new ProdutoNaoEncontradoException(); // Se o restaurante não for encontrado
                    }
                } else {
                    return produto.getAtributo(atributo);
                }
            }
        }

        throw new ProdutoNaoEncontradoException(); // Produto com o nome especificado não encontrado
    }

    public String listarProdutos(int empresa)throws EmpresaNaoEncontradaException{

        Restaurante restaurante = restaurantes.get(empresa);
        if (restaurante == null) {
            throw new EmpresaNaoEncontradaException(); // Lançar exceção se a empresa não for encontrada
        }
        // Obter as empresas do dono
        List<Produto> produtoDoRestaurante = produtosPorRestaurante.get(empresa);
        if (produtoDoRestaurante == null || produtoDoRestaurante.isEmpty()) {
            return "{[]}"; //
        }

        // Construir a string com o formato desejado
        StringBuilder resultado = new StringBuilder("{[");
        for (int i = 0; i < produtoDoRestaurante.size(); i++) {
            Produto produto = produtoDoRestaurante.get(i);
            if (i > 0) {
                resultado.append(", ");
            }
            resultado.append(produto.getNome());
        }
        resultado.append("]}");

        return resultado.toString();
    }

    public int criarPedido(int clienteId, int empresaId) throws DonoNaoPodePedidoException, PedidoEmAbertoException {
        Usuario cliente = usuarios.get(clienteId);
        Restaurante restaurante = restaurantes.get(empresaId);

        // Verifica se o cliente e o restaurante existem
        if (cliente == null || restaurante == null) {
            throw new DonoNaoPodePedidoException(); // Ou uma exceção mais específica
        }

        // Verifica se o cliente é o dono do restaurante
        Usuario donoRestaurante = null;
        for (Map.Entry<Integer, List<Restaurante>> entry : restaurantesPorDono.entrySet()) {
            if (entry.getValue().contains(restaurante)) {
                donoRestaurante = usuarios.get(entry.getKey());
                break;
            }
        }

        if (donoRestaurante != null && donoRestaurante.equals(cliente)) {
            throw new DonoNaoPodePedidoException(); // O dono não pode fazer um pedido para sua própria empresa
        }

        // Verifica se há um pedido em aberto do mesmo cliente para o mesmo restaurante
        List<Pedido> pedidosDoRestaurante = pedidosPorRestaurante.get(empresaId);
        if (pedidosDoRestaurante != null) {
            for (Pedido pedido : pedidosDoRestaurante) {
                if (pedido.getCliente().equals(cliente.getNome()) && pedido.getEstado().equals("aberto")) {
                    throw new PedidoEmAbertoException(); // Não é permitido ter dois pedidos abertos para a mesma empresa e cliente
                }
            }
        }

        // Cria um novo pedido
        Pedido pedido = new Pedido(cliente.getNome(), restaurante.getNome()); // Alterado para passar objetos completos
        pedidosDoRestaurante = pedidosPorRestaurante.get(empresaId);
        if (pedidosDoRestaurante == null) {
            pedidosDoRestaurante = new ArrayList<>();
            pedidosPorRestaurante.put(empresaId, pedidosDoRestaurante);
        }
        pedidosDoRestaurante.add(pedido);
        pedidos.put(pedido.getNumero(), pedido);

        return pedido.getNumero();
    }

    public void adicionarProduto(int numeroPedido, int idProduto) throws ProdutoNaoEncontradoException,
            EmpresaNaoEncontradaException, ProdutoNaoPertenceEmpresaException, NaoExistePedidoAbertoException, PedidoFechadoException {
        Pedido pedido = pedidos.get(numeroPedido);
        if (pedido == null) {
            throw new NaoExistePedidoAbertoException();
        }
        if(pedido.getEstado().equals("preparando")){
            throw new PedidoFechadoException();
        }

        Produto produto = produtos.get(idProduto);
        if (produto == null) {
            throw new ProdutoNaoEncontradoException();
        }

        // Verifica se o produto pertence à empresa do pedido
        String nomeEmpresaPedido = pedido.getEmpresa();
        Restaurante restaurante = null;

        // Encontrar o restaurante com base no nome
        for (Restaurante r : restaurantes.values()) {
            if (r.getNome().equals(nomeEmpresaPedido)) {
                restaurante = r;
                break;
            }
        }

        if (restaurante == null) {
            throw new EmpresaNaoEncontradaException();
        }

        // Verifica se o produto pertence ao restaurante
        List<Produto> produtosDoRestaurante = produtosPorRestaurante.get(restaurante.getId());
        if (produtosDoRestaurante == null || !produtosDoRestaurante.contains(produto)) {
            throw new ProdutoNaoPertenceEmpresaException();
        }

        pedido.adicionarProduto(produto);
    }


    public String getPedidos(int numeroPedido, String atributo) throws NaoExistePedidoAbertoException,
            AtributoInvalidoException, AtributoNaoExisteException {
        Pedido pedido = pedidos.get(numeroPedido);

        if (pedido == null) {
            throw new NaoExistePedidoAbertoException();
        }

        if (atributo == null || atributo.trim().isEmpty()) {
            throw new AtributoInvalidoException();
        }

        switch (atributo.toLowerCase()) {
            case "cliente":
                return pedido.getCliente();
            case "empresa":
                return pedido.getEmpresa();
            case "estado":
                return pedido.getEstado();
            case "valor":
                return String.format(Locale.US, "%.2f", pedido.getValor());
            case "produtos":
                List<Produto> produtos = pedido.getProdutos();
                if (produtos == null || produtos.isEmpty()) {
                    return "{[]}"; // Nenhum produto encontrado
                }
                StringBuilder resultado = new StringBuilder("{[");
                for (int i = 0; i < produtos.size(); i++) {
                    Produto produto = produtos.get(i);
                    if (i > 0) {
                        resultado.append(", ");
                    }
                    resultado.append(produto.getNome());
                }
                resultado.append("]}");
                return resultado.toString();
            default:
                throw new AtributoNaoExisteException();
        }
    }

    public void fecharPedido(int numeroPedido) throws PedidoNaoEncontradoException {
        Pedido pedido = pedidos.get(numeroPedido);

        if (pedido == null) {
            throw new PedidoNaoEncontradoException();
        }

        // Altera o estado do pedido para "fechado"
        pedido.finalizarPedido();
    }

    public void removerProduto(int numeroPedido, String nomeProduto) throws PedidoNaoEncontradoException,
            ProdutoNaoEncontradoException, RemoverProdutoPedidoFechadoException, ProdutoInvalidoException {

        if (nomeProduto == null || nomeProduto.trim().isEmpty()) {
            throw new ProdutoInvalidoException();
        }
        Pedido pedido = pedidos.get(numeroPedido);

        if (pedido == null) {
            throw new PedidoNaoEncontradoException();
        }

        if(pedido.getEstado().equals("preparando")){
            throw new RemoverProdutoPedidoFechadoException();
        }

        boolean produtoRemovido = pedido.removerProdutoPorNome(nomeProduto);

        if (!produtoRemovido) {
            throw new ProdutoNaoEncontradoException();
        }
    }

    public int getNumeroPedido(int clienteId, int empresaId, int indice) {
        List<Pedido> pedidosDoRestaurante = pedidosPorRestaurante.get(empresaId);
        Usuario cliente = usuarios.get(clienteId);

        if (cliente == null || pedidosDoRestaurante == null) {
            throw new IllegalArgumentException();
        }

        if (indice < 0 || indice >= pedidosDoRestaurante.size()) {
            throw new IndexOutOfBoundsException();
        }

        Pedido pedido = pedidosDoRestaurante.get(indice);
        return pedido.getNumero();
    }






    public void encerrarSistema(){
    }
}
