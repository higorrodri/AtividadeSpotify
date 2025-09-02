public class Podcast extends Midia {
private int episodio;
public Podcast(String titulo, String host, int duracaoSeg, Genero genero, int episodio) {
super(titulo, host, duracaoSeg, genero);
this.episodio = Math.max(episodio, 1);
}
public int getEpisodio() { return episodio; }
@Override public String getTipo() { return "Podcast"; }
@Override public String toString() {
return super.toString() + " ep." + episodio;
}
}