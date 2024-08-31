package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.AtributoInvalidoException;
import br.ufal.ic.p2.myfood.Exceptions.AtributoNaoExisteException;
import br.ufal.ic.p2.myfood.Exceptions.ProdutoNaoEncontrado;

public class Produto {
    private static int idCounter = 0; // Gerador de ID automático
    private int id;
    private String nome;
    private float valor;
    private String categoria;

    public Produto(String nome, float valor, String categoria) {
        this.id = ++idCounter;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getAtributo(String atributo) throws AtributoNaoExisteException, ProdutoNaoEncontrado {
        switch (atributo) {
            case "nome":
                return getNome();
            case "categoria":
                return getCategoria();
            default:
                throw new AtributoNaoExisteException();
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}