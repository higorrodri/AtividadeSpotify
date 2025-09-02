public class Audiobook extends Midia {
    private String narrador;

    public Audiobook(String titulo, String autorOuObra, int duracaoSeg, Genero genero, String narrador) {
        super(titulo, autorOuObra, duracaoSeg, genero);
        this.narrador = (narrador == null || narrador.trim().isEmpty()) ? "desconhecido" : narrador.trim();
    }

    public String getNarrador() { return narrador; }

    @Override public String getTipo() { return "Audiobook"; }

    @Override
    public String toString() {
        return super.toString() + " narrado por " + narrador;
    }
}
