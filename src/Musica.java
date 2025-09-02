public class Musica extends Midia {
public Musica(String titulo, String artista, int duracaoSeg, Genero genero) {
super(titulo, artista, duracaoSeg, genero);
}
@Override public String getTipo() { return "Música"; }
}