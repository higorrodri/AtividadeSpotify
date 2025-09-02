import java.util.*;

public class Usuario {
    private String nome;
    private String email;
    private List<Playlist> playlists = new ArrayList<>();

    public Usuario(String nome, String email) {
        if (nome == null || nome.trim().isEmpty()) throw new ValidationException("Nome inválido");
        if (email == null || !email.contains("@")) throw new ValidationException("Email inválido");
        this.nome = nome.trim();
        this.email = email.trim();
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public List<Playlist> getPlaylists() { return Collections.unmodifiableList(playlists); }

    public Playlist criarPlaylist(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new ValidationException("Nome da playlist inválido");
        Playlist p = new Playlist(nome.trim(), this);
        playlists.add(p);
        return p;
    }
}
