import java.util.*;


public class AppData {
Catalogo catalogo = new Catalogo();
List<Usuario> usuarios = new ArrayList<>();


public Usuario cadastrarUsuario(String nome, String email) {
for (Usuario u : usuarios) if (u.getEmail().equalsIgnoreCase(email))
throw new ValidationException("Email já cadastrado");
Usuario u = new Usuario(nome, email);
usuarios.add(u);
return u;
}


public Usuario acharUsuarioPorEmail(String email) {
for (Usuario u : usuarios) if (u.getEmail().equalsIgnoreCase(email)) return u;
throw new NotFoundException("Usuário não encontrado");
}
}