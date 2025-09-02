import java.util.*;

public class SpotifyMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AppData db = new AppData();
        SpotifyHelper.seed(db);

        System.out.println("Spotify6");
        boolean rodando = true;
        while (rodando) {
            try {
                SpotifyHelper.mostrarMenu();
                String op = sc.nextLine().trim();
                switch (op) {
                    case "1": SpotifyHelper.cadastrarUsuario(sc, db); break;
                    case "2": SpotifyHelper.cadastrarMidia(sc, db); break;
                    case "3": SpotifyHelper.criarPlaylist(sc, db); break;
                    case "4": SpotifyHelper.adicionarNaPlaylist(sc, db); break;
                    case "5": SpotifyHelper.removerDaPlaylist(sc, db); break;
                    case "6": SpotifyHelper.verPlaylistsDoUsuario(sc, db); break;
                    case "7": SpotifyHelper.buscarNoCatalogo(sc, db); break;
                    case "8": SpotifyHelper.listarCatalogo(db); break;
                    case "0": rodando = false; break;
                    default: System.out.println("Opção inválida\n");
                }
            } catch (AppException e) {
                System.out.println("[erro] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("[erro inesperado] " + e.getMessage());
            }
        }
        System.out.println("Encerrado.");
    }
}
