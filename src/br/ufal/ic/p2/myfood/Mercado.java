package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.AtributoInvalidoException;

public class Mercado extends Empresa {
    private static int contador = 0;
    private int id;
    private int dono;
    private String nome;
    private String endereco;
    private String abre; // Hora em HH:MM.
    private String fecha; // Hora em HH:MM.
    private String tipoMercado; // supermercado, minimercado ou atacadista.

    public Mercado(int dono, String nome, String endereco, String abre, String fecha, String tipoMercado) {
        this.id = contador++;
        this.dono = dono;
        this.nome = nome;
        this.endereco = endereco;
        this.abre = abre;
        this.fecha = fecha;
        this.tipoMercado = tipoMercado;
    }

    public String getTipoMercado() {
        return tipoMercado;
    }

    public String getAtributo(String atributo) throws AtributoInvalidoException {
        if ("tipoMercado".equalsIgnoreCase(atributo)) {
            return getTipoMercado();
        }
        return super.getAtributo(atributo);
    }
}
