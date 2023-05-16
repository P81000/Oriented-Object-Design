import java.util.*;

interface Publicacao {
    String getNome();
    int getArtigo();
    int getPublicacao();
    void toStrings();
}

class Artigo implements Publicacao {
    private String nome;
    private String autor;

    public Artigo(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public String getNome() {
        return nome;
    }

    public int getArtigo() {
        return 1;
    }

    public int getPublicacao(){
        return 0;
    }

    public void toStrings() {
        System.out.println("Artigo: " + getNome() + " - " + getAutor());
    }
}

class Revista implements Publicacao {
    private String nome;
    private String editor;

    private List<Publicacao> publicacoes = new ArrayList<>();

    public Revista(String nome, String editor) {
        this.nome = nome;
        this.editor = editor;
    }

    public String getEditor() {
        return editor;
    }

    public String getNome() {
        return nome;
    }

    public int getArtigo() {
        int totalArtigos = 0;
        for(Publicacao publicacao : publicacoes){
            totalArtigos += publicacao.getArtigo();
        }
        return totalArtigos;
    }

    public int getPublicacao(){
        return publicacoes.size();
    }

    public void toStrings(){
        System.out.println("Revista: " + getNome());
        for(Publicacao publicacao : publicacoes){
            publicacao.toStrings();
        }
    }

    public void addPublicacao(Publicacao publicacao) {
        publicacoes.add(publicacao);
    }

    public List<Publicacao> getPublicacoes(){
        return publicacoes;
    }
}

class Caderno implements Publicacao {
    private String nome;

    private List<Publicacao> publicacoes = new ArrayList<>();

    public Caderno(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getArtigo() {
        int totalArtigos = 0;
        for(Publicacao publicacao : publicacoes){
            totalArtigos += publicacao.getArtigo();
        }
        return totalArtigos;
    }

    public int getPublicacao(){
        int totalPublicacoes = 0;
        for(Publicacao publicacao : publicacoes){
            totalPublicacoes += publicacao.getPublicacao();
        }
        return totalPublicacoes;
    }

    public void toStrings(){
        System.out.println("Caderno: " + getNome());
        for(Publicacao publicacao : publicacoes){
            publicacao.toStrings();
        }
    }

    public void addPublicacao(Publicacao publicacao){
        publicacoes.add(publicacao);
    }
}

class Jornal implements Publicacao {
    private String nome;
    private String editor;

    private List<Publicacao> publicacoes = new ArrayList<>();

    public Jornal(String nome, String editor) {
        this.nome = nome;
        this.editor = editor;
    }

    public String getEditor() {
        return editor;
    }

    public String getNome() {
        return nome;
    }

    public int getArtigo() {
        int totalArtigos = 0;
        for(Publicacao publicacao : publicacoes){
            totalArtigos += publicacao.getArtigo();
        }
        return totalArtigos;
    }

    public int getPublicacao(){
        int totalPublicacoes = 0;
        for(Publicacao publicacao : publicacoes){
            totalPublicacoes += publicacao.getPublicacao();
        }
        return totalPublicacoes;
    }

    public void toStrings(){
        System.out.println("Jornal: " + getNome());
        for(Publicacao publicacao : publicacoes){
            publicacao.toStrings();
        }
    }

    public void addPublicacao(Publicacao publicacao){
        publicacoes.add(publicacao);
    }
}

class Colecao {
    private List<Publicacao> publicacoes = new ArrayList<>();

    public void addPublicacao(Publicacao publicacao){
        publicacoes.add(publicacao);
    }

    public List<Publicacao> getPublicacoes(){
        return publicacoes;
    }

    public void toStrings(){
        for(Publicacao publicacao : publicacoes){
            publicacao.toStrings();
        }
    }

    public int getArtigo(){
        int totalArtigos = 0;
        for(Publicacao publicacao : publicacoes){
            if(publicacao instanceof Artigo){
                totalArtigos++;
            }
            else {
                totalArtigos += publicacao.getArtigo();
            }
        }
      return totalArtigos;
    }

    public int getPublicacao(){
        return publicacoes.size();
    }
}

class Main {
    public static void main(String[] args) {
        Colecao colecao = new Colecao();
        
        Artigo a1 = new Artigo("Artigo1", "Autor1");
        Artigo a2 = new Artigo("Artigo2", "Autor2");
        Artigo a3 = new Artigo("Artigo3", "Autor3");
        Revista r1 = new Revista("Revista1", "Editora1");
        Caderno c1 = new Caderno("Caderno1");
        Jornal j1 = new Jornal("Jornal1", "Editor1");
        
        r1.addPublicacao(a1);
        c1.addPublicacao(a2);
        j1.addPublicacao(a1);
        j1.addPublicacao(a2);
        r1.addPublicacao(a3);
        colecao.addPublicacao(j1);
        colecao.addPublicacao(r1);
        colecao.addPublicacao(c1);

        colecao.toStrings();

        System.out.println("Total artigos: " + colecao.getArtigo());
        System.out.println("Total publicacoes: " + colecao.getPublicacao());
    }
}