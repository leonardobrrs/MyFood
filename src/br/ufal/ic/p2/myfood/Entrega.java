package br.ufal.ic.p2.myfood;

public class Entrega {
    private int id; // ID único da entrega
    private int idPedido; // ID do pedido
    private int idEntregador; // ID do entregador
    private String destino; // Endereço de entrega

    // Construtor
    public Entrega(int id, int idPedido, int idEntregador, String destino) {
        this.id = id;
        this.idPedido = idPedido;
        this.idEntregador = idEntregador;
        this.destino = destino;
    }

    // Getters para acessar as informações
    public int getId() { return id; }
    public int getIdPedido() { return idPedido; }
    public int getIdEntregador() { return idEntregador; }
    public String getDestino() { return destino; }
}
