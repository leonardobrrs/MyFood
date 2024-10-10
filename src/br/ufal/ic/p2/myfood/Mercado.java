package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.AtributoInvalidoException;

import java.io.Serializable;

public class Mercado extends Empresa  {

    private String abre; // Hora em HH:MM.
    private String fecha; // Hora em HH:MM.
    private String tipoMercado; // supermercado, minimercado ou atacadista.

    public Mercado(String nome, String endereco, String abre, String fecha, String tipoMercado) {
        super(nome, endereco);
        this.abre = abre;
        this.fecha = fecha;
        this.tipoMercado = tipoMercado;
    }

    // Getter para o horário de abertura
    public String getAbre() {
        return abre;
    }

    // Getter para o horário de fechamento
    public String getFecha() {
        return fecha;
    }

    // Getter para o tipo de mercado
    public String getTipoMercado() {
        return tipoMercado;
    }

    // Sobrescrevendo o método getAtributo para lidar com os atributos do Mercado
    @Override
    public String getAtributo(String atributo) throws AtributoInvalidoException {
        switch (atributo.toLowerCase()) {
            case "abre":
                return getAbre();
            case "fecha":
                return getFecha();
            case "tipomercado":
                return getTipoMercado();
            default:
                // Para outros atributos, chamamos o método da superclasse (Empresa)
                return super.getAtributo(atributo);
        }
    }
}
