Spotify Básico em Java

Bem-vindo ao Spotify Básico – um projeto 100% Java que simula um miniaplicativo de streaming!
Com ele você pode ouvir músicas incríveis, criar playlists personalizadas, gerenciar audiobooks, descobrir novos sons e organizar sua biblioteca digital.

👉 É como se fosse uma versão simplificada do Spotify, mas feita inteiramente em Java, para praticar Programação Orientada a Objetos (POO).

✨ Funcionalidades

✅ Criar usuários
✅ Cadastrar músicas, podcasts e audiobooks
✅ Criar playlists personalizadas
✅ Adicionar e remover músicas de playlists
✅ Pesquisar músicas pelo nome, autor/artista ou gênero
✅ Visualizar playlists de cada usuário
✅ Listar todo o catálogo disponível
✅ Dados de exemplo (seed) já pré-carregados

🛠 Tecnologias Utilizadas

☕ Java (100%)

📦 Estruturas de dados: List, ArrayList

🎼 Conceitos de POO:

Herança

Polimorfismo

Encapsulamento

Como rodar

Clone este repositório:

git clone https://github.com/higorrodri/AtividadeSpotify


Abra o projeto no IntelliJ IDEA, Eclipse ou outro editor Java.

Compile e execute a classe principal (Main.java).

O menu será exibido no console e você poderá navegar digitando as opções.

Estrutura do Projeto (vai ficar mais detalhado com o código que você vai mandar)

SpotifyHelper.java → Interface de console (menu e interações com o usuário)

AppData.java → "Banco de dados" que guarda usuários e catálogo de mídias

Usuario.java → Representa o usuário (nome, email, playlists)

Playlist.java → Lista de mídias criada por um usuário

Midia.java → Classe abstrata para mídias (música, podcast, audiobook)

Musica.java

Podcast.java

Audiobook.java

Genero.java → Enum de gêneros musicais

ValidationException.java e NotFoundException.java → Tratamento de erros
