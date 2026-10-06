package senai.amanda_santos.model;

public enum TipoMovimentacao {
    ENTRADA("Entrada"), SAIDA("Saída");

    private final String label;

    TipoMovimentacao(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
