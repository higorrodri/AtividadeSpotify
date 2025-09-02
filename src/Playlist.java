import java.util.*;

public class Playlist {
    private String nome;
    private Usuario dono;
    private List<Midia> itens = new ArrayList<>();

    public Playlist(String nome, Usuario dono) {
        if (nome == null || nome.trim().isEmpty()) throw new ValidationException("Nome da playlist inválido");
        if (dono == null) throw new ValidationException("Dono obrigatório");
        this.nome = nome.trim();
        this.dono = dono;
    }

    public String getNome() { return nome; }
    public Usuario getDono() { return dono; }
    public List<Midia> getItens() { return Collections.unmodifiableList(itens); }

    public void adicionar(Midia m) {
        if (m == null) throw new ValidationException("Mídia nula");
        itens.add(m);
    }

    public boolean removerPorTitulo(String titulo) {
        return itens.removeIf(m -> m.getTitulo().equalsIgnoreCase(titulo));
    }

    public int duracaoTotal() {
        int total = 0;
        for (Midia m : itens) total += m.getDuracaoSeg();
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist \"" + nome + "\" de " + dono.getNome() + "\n");
        if (itens.isEmpty()) {
            sb.append("(vazia)\n");
        } else {
            int i = 1;
            for (Midia m : itens) {
                sb.append(i++).append(". ").append(m).append("\n");
            }
            sb.append("Duração total: ").append(duracaoTotal()).append("s\n");
        }
        return sb.toString();
    }
}
