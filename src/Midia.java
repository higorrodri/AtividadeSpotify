public abstract class Midia {
    private String titulo;
    private String artista;
    private int duracaoSeg;
    private Genero genero;

    public Midia(String titulo, String artista, int duracaoSeg, Genero genero) {
        if (titulo == null || titulo.trim().isEmpty()) throw new ValidationException("Título inválido");
        if (artista == null || artista.trim().isEmpty()) throw new ValidationException("Artista inválido");
        if (duracaoSeg <= 0) throw new ValidationException("Duração deve ser > 0");
        if (genero == null) throw new ValidationException("Gênero obrigatório");
        this.titulo = titulo.trim();
        this.artista = artista.trim();
        this.duracaoSeg = duracaoSeg;
        this.genero = genero;
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracaoSeg() { return duracaoSeg; }
    public Genero getGenero() { return genero; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("[%s] %s — %s (%s, %ds)", getTipo(), titulo, artista, genero, duracaoSeg);
    }
}
