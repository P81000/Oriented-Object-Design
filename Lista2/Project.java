package Lista2;

import java.util.*;

class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String identificador;
    private boolean emprestado;
    
    public Livro(String titulo, String autor, int anoPublicacao, String identificador) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.identificador = identificador;
        this.emprestado = false;
    }
    
    // Getters e setters
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    
    public String getIdentificador() {
        return identificador;
    }
    
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    public boolean isEmprestado() {
        return emprestado;
    }
    
    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }
    
    @Override
    public String toString() {
        return "Livro [Título: " + titulo + ", Autor: " + autor + ", Ano de Publicação: " + anoPublicacao + ", Identificador: " + identificador + "]";
    }
}

class Membro {
    private String nome;
    private List<Livro> livrosEmprestados;
    
    public Membro(String nome) {
        this.nome = nome;
        this.livrosEmprestados = new ArrayList<>();
    }
    
    public String getNome() {
        return nome;
    }
    
    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }
    
    public void emprestarLivro(Livro livro) {
        livrosEmprestados.add(livro);
    }
    
    public void devolverLivro(Livro livro) {
        livrosEmprestados.remove(livro);
    }
}

class DatabaseConnection {
    private static DatabaseConnection instance;
    
    // Construtor privado para impedir a criação de instâncias diretas
    private DatabaseConnection() {
        System.out.println("Sucessfully connected to database");
    }
    
    // Método estático para obter a instância única da conexão
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}

class Biblioteca {
    private String nome;
    private List<Livro> catalogo;
    private List<Membro> membros;
    
    public Biblioteca(String nome) {
        this.nome = nome;
        this.catalogo = new ArrayList<>();
        this.membros = new ArrayList<>();
    }
    
    public void adicionarLivro(Livro livro) {
        catalogo.add(livro);
    }
    
    public void removerLivro(Livro livro) {
        catalogo.remove(livro);
    }
    
    public List<Livro> pesquisarLivro(String termo) {
        List<Livro> resultados = new ArrayList<>();
        for (Livro livro : catalogo) {
            if (livro.getTitulo().contains(termo) || livro.getAutor().contains(termo)) {
                resultados.add(livro);
            }
        }
        return resultados;
    }
    
    public void adicionarMembro(Membro membro) {
        membros.add(membro);
    }
    
    public void removerMembro(Membro membro) {
        membros.remove(membro);
    }
    
    public List<Livro> getLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = new ArrayList<>();
        for (Livro livro : catalogo) {
            if (!livro.isEmprestado()) {
                livrosDisponiveis.add(livro);
            }
        }
        return livrosDisponiveis;
    }
    
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Livro> getCatalogo() {
        return catalogo;
    }
    
    public void setCatalogo(List<Livro> catalogo) {
        this.catalogo = catalogo;
    }
    
    public List<Membro> getMembros() {
        return membros;
    }
    
    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }
}

class BibliotecaFacade {
    private static BibliotecaFacade instance;
    private Biblioteca biblioteca;
    
    private BibliotecaFacade(String nome) {
        this.biblioteca = new Biblioteca(nome);
    }
    
    public static BibliotecaFacade getInstance(String nome) {
        if (instance == null) {
            instance = new BibliotecaFacade(nome);
        }
        return instance;
    }
    
    public void adicionarLivro(Livro livro) {
        biblioteca.adicionarLivro(livro);
    }
    
    public void removerLivro(Livro livro) {
        biblioteca.removerLivro(livro);
    }
    
    public List<Livro> pesquisarLivro(String termo) {
        return biblioteca.pesquisarLivro(termo);
    }
    
    public void adicionarMembro(Membro membro) {
        biblioteca.adicionarMembro(membro);
    }
    
    public void removerMembro(Membro membro) {
        biblioteca.removerMembro(membro);
    }
    
    public List<Livro> getLivrosDisponiveis() {
        return biblioteca.getLivrosDisponiveis();
    }

    public List<Livro> getLivrosEmprestados(Membro membro) {
        return membro.getLivrosEmprestados();
    }
    
    // Outros métodos e funcionalidades simplificadas
    
    public boolean emprestarLivro(Membro membro, Livro livro) {
        if (!livro.isEmprestado()) {
            membro.emprestarLivro(livro);
            livro.setEmprestado(true);
            System.out.println("Livro emprestado com sucesso.");
        } else {
            System.out.println("O livro não está disponível para empréstimo no momento.");
        }
        return false;
    }
    
    public boolean devolverLivro(Membro membro, Livro livro) {
        if (membro.getLivrosEmprestados().contains(livro)) {
            membro.devolverLivro(livro);
            livro.setEmprestado(false);
            System.out.println("Livro devolvido com sucesso.");
        } else {
            System.out.println("O membro não possui este livro emprestado.");
        }
        return false;
    }
}

class MediadorDeEmprestimo {
    private static MediadorDeEmprestimo instance;
    private Map<Membro, List<Livro>> emprestimos;
    
    private MediadorDeEmprestimo() {
        emprestimos = new HashMap<>();
    }
    
    public static synchronized MediadorDeEmprestimo getInstance() {
        if (instance == null) {
            instance = new MediadorDeEmprestimo();
        }
        return instance;
    }
    
    public boolean emprestarLivro(Membro membro, Livro livro) {
        // Verificar se o membro já possui o livro emprestado ou se atingiu o limite de empréstimos
        if (emprestimos.containsKey(membro) && emprestimos.get(membro).contains(livro)) {
            System.out.println("O membro já possui o livro emprestado.");
            return false;
        }
        
        // Realizar a operação de empréstimo
        // ...
        
        // Atualizar o registro de empréstimos
        if (!emprestimos.containsKey(membro)) {
            emprestimos.put(membro, new ArrayList<>());
        }
        emprestimos.get(membro).add(livro);
        
        return true;
    }
    
    public boolean devolverLivro(Membro membro, Livro livro) {
        // Verificar se o membro possui o livro emprestado
        if (!emprestimos.containsKey(membro) || !emprestimos.get(membro).contains(livro)) {
            System.out.println("O membro não possui o livro emprestado.");
            return false;
        }
        
        // Realizar a operação de devolução
        // ...
        
        // Atualizar o registro de empréstimos
        emprestimos.get(membro).remove(livro);
        
        return true;
    }
}

class LivroBuilder {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String identificador;
    
    public LivroBuilder() {
        // Valores padrão
        titulo = "";
        autor = "";
        anoPublicacao = 0;
        identificador = "";
    }
    
    public LivroBuilder comTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }
    
    public LivroBuilder comAutor(String autor) {
        this.autor = autor;
        return this;
    }
    
    public LivroBuilder comAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
        return this;
    }
    
    public LivroBuilder comIdentificador(String identificador) {
        this.identificador = identificador;
        return this;
    }
    
    public Livro construir() {
        // Verificar se todos os campos obrigatórios foram preenchidos
        if (titulo.isEmpty() || autor.isEmpty() || anoPublicacao == 0 || identificador.isEmpty()) {
            throw new IllegalStateException("Campos obrigatórios do livro não foram preenchidos.");
        }
        
        // Construir e retornar o objeto Livro
        return new Livro(titulo, autor, anoPublicacao, identificador);
    }
}

class Main {
    public static void main(String[] args) {
        // Obtém a instância da fachada da biblioteca
        BibliotecaFacade bibliotecaFacade = BibliotecaFacade.getInstance("teste");
        
        // Criação de membros
        Membro membro1 = new Membro("João");
        Membro membro2 = new Membro("Maria");
        
        // Criação de livros usando o LivroBuilder
        Livro livro1 = new LivroBuilder()
                .comTitulo("Dom Quixote")
                .comAutor("Miguel de Cervantes")
                .comAnoPublicacao(1605)
                .comIdentificador("123456")
                .construir();
        
        Livro livro2 = new LivroBuilder()
                .comTitulo("Orgulho e Preconceito")
                .comAutor("Jane Austen")
                .comAnoPublicacao(1813)
                .comIdentificador("789012")
                .construir();
        
        // Pesquisa de livros
        List<Livro> resultadosPesquisa = bibliotecaFacade.pesquisarLivro("Dom");
        System.out.println("Resultados da pesquisa:");
        for (Livro livro : resultadosPesquisa) {
            System.out.println(livro);
        }
        
        // Empréstimo de livros
        boolean emprestimo1 = bibliotecaFacade.emprestarLivro(membro1, livro1);
        boolean emprestimo2 = bibliotecaFacade.emprestarLivro(membro2, livro2);
        
        System.out.println("Empréstimo 1: " + (emprestimo1 ? "Sucesso" : "Falha"));
        System.out.println("Empréstimo 2: " + (emprestimo2 ? "Sucesso" : "Falha"));
        
        // Livros emprestados por um membro
        List<Livro> livrosEmprestados = bibliotecaFacade.getLivrosEmprestados(membro1);
        System.out.println("Livros emprestados por " + membro1.getNome() + ":");
        for (Livro livro : livrosEmprestados) {
            System.out.println(livro);
        }
        
        // Devolução de livros
        boolean devolucao1 = bibliotecaFacade.devolverLivro(membro1, livro1);
        boolean devolucao2 = bibliotecaFacade.devolverLivro(membro2, livro2);
        
        System.out.println("Devolução 1: " + (devolucao1 ? "Sucesso" : "Falha"));
        System.out.println("Devolução 2: " + (devolucao2 ? "Sucesso" : "Falha"));
    }
}

