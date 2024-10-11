package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.AtributoInvalidoException;

public class Mercado extends Empresa  {

    private String abre; // Hora em HH:MM.
    private String fecha; // Hora em HH:MM.
    private String tipoMercado; // supermercado, minimercado ou atacadista.

    public Mercado(String tipoEmpresa, String nome, String endereco, String abre, String fecha, String tipoMercado) {
        super(tipoEmpresa, nome, endereco);
        this.abre = abre;
        this.fecha = fecha;
        this.tipoMercado = tipoMercado;
    }

    @Override
    public boolean isMercado() {
        return true;
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

    // Métodos para definir horários
    public void setAbre(String abre) {
        this.abre = abre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public void setAtributo(String atributo, String valor) throws AtributoInvalidoException {
        switch (atributo.toLowerCase()) {
            case "abre":
                setAbre(valor); // Passa o valor para o método setAbre
                return;
            case "fecha":
                setFecha(valor); // Passa o valor para o método setFecha
                return;
            default:
                return;
        }
    }

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
                return super.getAtributo(atributo);
        }
    }
}