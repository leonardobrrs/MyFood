package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

public class Facade {
    private Sistema sistema;

    public Facade() throws IOException, ClassNotFoundException {
        sistema = new Sistema();
    }

    public void zerarSistema() {
        sistema.zerarSistema();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException,
            EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, EmailExistenteException {
        sistema.criarUsuario(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException,
            EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, CpfInvalidoException, EmailExistenteException {
        sistema.criarUsuario(nome, email, senha, endereco, cpf);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String veiculo, String placa) throws NomeInvalidoException
            , EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, PlacaInvalidaException, VeiculoInvalidoException,
            EmailExistenteException {
        sistema.criarUsuario(nome, email, senha, endereco, veiculo, placa);
    }

    public void cadastrarEntregador(int idEmpresa, int idEntregador)
            throws EmpresaNaoEncontradaException, UsuarioNaoEntregadorException {
        sistema.cadastrarEntregador(idEmpresa, idEntregador);
    }

    public String getEntregadores(int idEmpresa) throws EmpresaNaoEncontradaException {
        return sistema.getEntregadores(idEmpresa);
    }

    public String getEmpresas(int idEntregador) throws UsuarioNaoEntregadorException {
        return sistema.getEmpresas(idEntregador);
    }

    public int login(String email, String senha) throws LoginSenhaInvalidosException {
        return sistema.login(email, senha);
    }

    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException, AtributoInvalidoException {
        return sistema.getAtributoUsuario(id, atributo);
    }

    public int criarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, String tipoCozinha) throws EnderecoDuplicadoException, NomeEmpresaExistenteException, UsuarioNaoAutorizadoException {
        return sistema.criarEmpresa(tipoEmpresa, idDono, nome, endereco, tipoCozinha);
    }

    public int criarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, String abre, String fecha,
                            String tipoMercado) throws NomeEmpresaExistenteException, EnderecoDuplicadoException,
            UsuarioNaoAutorizadoException, FormatoHoraInvalidoException,
            TipoEmpresaInvalidoException, TipoMercadoInvalidoException, NomeInvalidoException,
            EnderecoInvalidoException, EnderecoEmpresaInvalidoException, HorarioInvalidoException {
        return sistema.criarEmpresa(tipoEmpresa, idDono, nome, endereco, abre, fecha, tipoMercado);
    }

    public void alterarFuncionamento(int mercadoId, String abre, String fecha) throws AtributoInvalidoException,
            FormatoHoraInvalidoException, HorarioInvalidoException, MercadoInvalidoException {
        sistema.alterarFuncionamento(mercadoId, abre, fecha);
    }

    public int criarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, boolean aberto24Horas,
                            int numeroFuncionarios)
            throws TipoEmpresaInvalidoException, NomeInvalidoException, EnderecoInvalidoException, NomeEmpresaExistenteException, EnderecoDuplicadoException,
            UsuarioNaoAutorizadoException, EnderecoEmpresaInvalidoException{
        return sistema.criarEmpresa(tipoEmpresa, idDono, nome, endereco, aberto24Horas, numeroFuncionarios);
    }


    public String getEmpresasDoUsuario(int idDono) throws UsuarioNaoAutorizadoException {
        return sistema.getEmpresasDoUsuario(idDono);
    }

    public int getIdEmpresa(int idDono, String nome, int indice) throws NomeInvalidoException,
            NomeEmpresaNaoExisteException, IndiceInvalidoException, IndiceMaiorException {
        return sistema.getIdEmpresa(idDono, nome, indice);
    }

    public String getAtributoEmpresa(int empresaId, String atributo) throws EmpresaNaoCadastradaException, AtributoInvalidoException {
        return sistema.getAtributoEmpresa(empresaId, atributo);
    }

    public int criarProduto(int empresa, String nome, float valor, String categoria) throws NomeProdutoExisteException, NomeInvalidoException, ValorInvalidoException, CategoriaInvalidaException {
        return sistema.criarProduto(empresa, nome, valor, categoria);
    }

    public void editarProduto(int produto, String nome, float valor, String categoria) throws NomeInvalidoException,
            CategoriaInvalidaException, ValorInvalidoException, ProdutoNaoCadastradoException {
        sistema.editarProduto(produto, nome, valor, categoria);
    }

    public String getProduto(String nome, int empresa, String atributo) throws AtributoNaoExisteException,
            ProdutoNaoEncontradoException {
        return sistema.getProduto(nome, empresa, atributo);
    }

    public String listarProdutos(int empresa) throws EmpresaNaoEncontradaException {
        return sistema.listarProdutos(empresa);
    }

    public int criarPedido(int clienteId, int empresaId) throws DonoNaoPodePedidoException, PedidoEmAbertoException {
        return sistema.criarPedido(clienteId, empresaId);
    }

    public void adicionarProduto(int numero, int produto) throws NaoExistePedidoAbertoException,
            ProdutoNaoEncontradoException, ProdutoNaoPertenceEmpresaException, EmpresaNaoEncontradaException, PedidoFechadoException {
        sistema.adicionarProduto(numero, produto);
    }

    public String getPedidos(int numeroPedido, String atributo) throws NaoExistePedidoAbertoException,
            AtributoInvalidoException, AtributoNaoExisteException {
        return sistema.getPedidos(numeroPedido, atributo);
    }

    public void fecharPedido(int numeroPedido) throws PedidoNaoEncontradoException {
        sistema.fecharPedido(numeroPedido);
    }

    public void removerProduto(int numeroPedido, String nomeProduto) throws PedidoNaoEncontradoException,
            ProdutoNaoEncontradoException, ProdutoInvalidoException, RemoverProdutoPedidoFechadoException {
        sistema.removerProduto(numeroPedido, nomeProduto);
    }

    public int getNumeroPedido(int clienteId, int empresaId, int indice) {
        return sistema.getNumeroPedido(clienteId, empresaId, indice);
    }

    public void liberarPedido(int numero) throws PedidoNaoEncontradoException, PedidoJaLiberadoException, NaoEhPossivelLiberarException {
        sistema.liberarPedido(numero);
    }

    public int obterPedido(int idEntregador) throws UsuarioNaoEntregadorException, PedidoNaoEncontradoException,
            UsuarioNaoEntregadorDoisException, EntregadorSemEmpresaException {
        return sistema.obterPedido(idEntregador);
    }

    public void encerrarSistema() throws IOException {
        sistema.encerrarSistema();
    }
}
