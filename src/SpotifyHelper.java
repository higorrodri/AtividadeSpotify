import java.util.*;

public class SpotifyHelper {

    static void mostrarMenu() {
        System.out.println("\nMENU");
        System.out.println("1) Cadastrar usuário");
        System.out.println("2) Cadastrar mídia (música/podcast/audiobook)");
        System.out.println("3) Criar playlist");
        System.out.println("4) Adicionar mídia na playlist");
        System.out.println("5) Remover mídia da playlist por título");
        System.out.println("6) Ver playlists do usuário");
        System.out.println("7) Buscar no catálogo (título/artista/gênero)");
        System.out.println("8) Listar catálogo inteiro");
        System.out.println("0) Sair");
        System.out.print("Escolha: ");
    }

    static void cadastrarUsuario(Scanner sc, AppData db) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        Usuario u = db.cadastrarUsuario(nome, email);
        System.out.println("Usuário ok: " + u.getNome());
    }

    static void cadastrarMidia(Scanner sc, AppData db) {
        System.out.println("Tipo? (1=Musica, 2=Podcast, 3=Audiobook)");
        String tipo = sc.nextLine();

        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Artista/Host/Autor: ");
        String artista = sc.nextLine();
        System.out.print("Duração em segundos: ");
        int dur = Integer.parseInt(sc.nextLine());

        System.out.println("Gênero? " + Arrays.toString(Genero.values()));
        Genero g = Genero.valueOf(sc.nextLine().trim().toUpperCase());

        Midia m;
        if ("1".equals(tipo)) {
            m = new Musica(titulo, artista, dur, g);
        } else if ("2".equals(tipo)) {
            System.out.print("Número do episódio: ");
            int ep = Integer.parseInt(sc.nextLine());
            m = new Podcast(titulo, artista, dur, g, ep);
        } else if ("3".equals(tipo)) {
            System.out.print("Narrador: ");
            String narr = sc.nextLine();
            m = new Audiobook(titulo, artista, dur, g, narr);
        } else {
            throw new ValidationException("Tipo inválido");
        }
        db.catalogo.adicionar(m);
        System.out.println("Mídia adicionada: " + m);
    }

    static void criarPlaylist(Scanner sc, AppData db) {
        Usuario u = pedirUsuario(sc, db);
        System.out.print("Nome da playlist: ");
        String nome = sc.nextLine();
        Playlist p = u.criarPlaylist(nome);
        System.out.println("Playlist criada: " + p.getNome());
    }

    static void adicionarNaPlaylist(Scanner sc, AppData db) {
        Usuario u = pedirUsuario(sc, db);
        Playlist p = escolherPlaylist(sc, u);
        System.out.print("Título da mídia: ");
        String t = sc.nextLine();
        Midia m = db.catalogo.buscarPorTitulo(t);
        if (m == null) throw new NotFoundException("Não achei essa mídia no catálogo");
        p.adicionar(m);
        System.out.println("ok: adicionada");
    }

    static void removerDaPlaylist(Scanner sc, AppData db) {
        Usuario u = pedirUsuario(sc, db);
        Playlist p = escolherPlaylist(sc, u);
        System.out.print("Título da mídia pra remover: ");
        String t = sc.nextLine();
        boolean rm = p.removerPorTitulo(t);
        System.out.println(rm ? "removida" : "não achei esse título na playlist");
    }

    static void verPlaylistsDoUsuario(Scanner sc, AppData db) {
        Usuario u = pedirUsuario(sc, db);
        if (u.getPlaylists().isEmpty()) {
            System.out.println("(Usuário sem playlists)");
            return;
        }
        for (Playlist p : u.getPlaylists()) System.out.println(p);
    }

    static void buscarNoCatalogo(Scanner sc, AppData db) {
        System.out.println("Buscar por: 1=título  2=artista  3=gênero");
        String op = sc.nextLine();
        if ("1".equals(op)) {
            System.out.print("Título: ");
            String t = sc.nextLine();
            Midia m = db.catalogo.buscarPorTitulo(t);
            System.out.println(m == null ? "Nada encontrado" : m);
        } else if ("2".equals(op)) {
            System.out.print("Artista/Host: ");
            String a = sc.nextLine();
            listarMidias(db.catalogo.buscarPorArtista(a));
        } else if ("3".equals(op)) {
            System.out.println("Gênero? " + Arrays.toString(Genero.values()));
            Genero g = Genero.valueOf(sc.nextLine().trim().toUpperCase());
            listarMidias(db.catalogo.buscarPorGenero(g));
        } else {
            System.out.println("Opção inválida");
        }
    }

    static void listarCatalogo(AppData db) {
        List<Midia> tudo = db.catalogo.listarTudo();
        if (tudo.isEmpty()) System.out.println("Catálogo vazio");
        for (Midia m : tudo) System.out.println(m);
    }

    static Usuario pedirUsuario(Scanner sc, AppData db) {
        System.out.print("Email do usuário: ");
        String email = sc.nextLine();
        return db.acharUsuarioPorEmail(email);
    }

    static Playlist escolherPlaylist(Scanner sc, Usuario u) {
        if (u.getPlaylists().isEmpty()) throw new ValidationException("Usuário não tem playlists");
        System.out.println("Playlists do usuário:");
        for (int i = 0; i < u.getPlaylists().size(); i++) {
            System.out.println((i+1) + ") " + u.getPlaylists().get(i).getNome());
        }
        System.out.print("Escolha (número): ");
        int idx = Integer.parseInt(sc.nextLine()) - 1;
        if (idx < 0 || idx >= u.getPlaylists().size()) throw new ValidationException("Índice inválido");
        return u.getPlaylists().get(idx);
    }

    static void listarMidias(List<Midia> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nada encontrado");
            return;
        }
        int i = 1;
        for (Midia m : lista) System.out.println((i++) + ") " + m);
    }

    static void seed(AppData db) {
        db.cadastrarUsuario("Ana", "ana@email.com");
        db.cadastrarUsuario("Bruno", "bruno@email.com");

        db.catalogo.adicionar(new Musica("Imagine", "John Lennon", 183, Genero.ROCK));
        db.catalogo.adicionar(new Musica("Garota de Ipanema", "Tom Jobim", 210, Genero.MPB));
        db.catalogo.adicionar(new Musica("Billie Jean", "Michael Jackson", 294, Genero.POP));

        db.catalogo.adicionar(new Podcast("Ciência Hoje", "Dra. Paula", 1800, Genero.JAZZ, 12));
        db.catalogo.adicionar(new Audiobook("Dom Casmurro", "Machado de Assis", 7200, Genero.CLASSICA, "Fulano"));

        Usuario ana = db.acharUsuarioPorEmail("ana@email.com");
        Playlist p = ana.criarPlaylist("Favoritas");
        p.adicionar(db.catalogo.buscarPorTitulo("Imagine"));
        p.adicionar(db.catalogo.buscarPorTitulo("Billie Jean"));
    }
}
