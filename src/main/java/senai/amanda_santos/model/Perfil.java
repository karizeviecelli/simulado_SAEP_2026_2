package senai.amanda_santos.model;

public enum Perfil {
    ADMIN("Administrador"),
    ALMOXARIFE("Almoxarife");

    private final String label;

    Perfil(String label) { this.label = label; }

    public String getLabel() { return label; }
}
