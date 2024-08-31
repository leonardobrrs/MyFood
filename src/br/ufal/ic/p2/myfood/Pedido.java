package br.ufal.ic.p2.myfood;

import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private static int contador = 0;

    private int numero;
    private String cliente;
    private String empresa;
    private String estado;
    private List<Produto> produtos;
    private float valor;

    public Pedido(String cliente, String empresa) {
        this.numero = ++contador;
        this.cliente = cliente;
        this.empresa = empresa;
        this.estado = "aberto";  // Estado inicial do pedido
        this.produtos = new ArrayList<>();
        this.valor = 0;
    }

    public int getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getEstado() {
        return estado;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public float getValor() {
        return valor;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        valor += produto.getValor(); // Atualiza o valor total do pedido
    }


    public void finalizarPedido() {
        this.estado = "Finalizado";
    }

    public void cancelarPedido() {
        this.estado = "Cancelado";
    }
}
