package scr.br.ufal.poo;

public class DonoRestaurante extends Usuario {
    private String cpf;

    public DonoRestaurante(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha, endereco);
        this.cpf = validarCpf(cpf);
    }

    // Validação específica para CPF
    private String validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 14) {
            throw new IllegalArgumentException("CPF invalido");
        }
        return cpf;
    }

    public String getCpf() {
        return cpf;
    }

    // Sobrescrevendo o método para tratar o atributo "cpf"
    @Override
    public String getAtributo(String atributo) {
        if ("cpf".equalsIgnoreCase(atributo)) {
            return getCpf();
        }
        return super.getAtributo(atributo);  // Chama o método da classe pai
    }
}
