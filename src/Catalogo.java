import java.util.*;

public class Catalogo {
    private Map<String, Midia> porTitulo = new HashMap<>();
    private List<Midia> todas = new ArrayList<>();

    public void adicionar(Midia m) {
        if (m == null) throw new ValidationException("Mídia nula");
        String key = m.getTitulo().toLowerCase();
        if (porTitulo.containsKey(key)) throw new ValidationException("Já existe uma mídia com esse título");
        porTitulo.put(key, m);
        todas.add(m);
    }

    public Midia buscarPorTitulo(String titulo) {
        if (titulo == null) return null;
        return porTitulo.get(titulo.toLowerCase());
    }

    public List<Midia> buscarPorArtista(String artista) {
        if (artista == null) return Collections.emptyList();
        List<Midia> res = new ArrayList<>();
        for (Midia m : todas) {
            if (m.getArtista().equalsIgnoreCase(artista)) res.add(m);
        }
        return res;
    }

    public List<Midia> buscarPorGenero(Genero g) {
        if (g == null) return Collections.emptyList();
        List<Midia> res = new ArrayList<>();
        for (Midia m : todas) {
            if (m.getGenero() == g) res.add(m);
        }
        return res;
    }

    public List<Midia> listarTudo() {
        return Collections.unmodifiableList(todas);
    }
}
