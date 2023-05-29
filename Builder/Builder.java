class Pessoa {
    private String nome;
    private int identidade;

    public Pessoa(String nome, int identidade) {
        this.nome = nome;
        this.identidade = identidade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdentidade() {
        return identidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdentidade(int identidade) {
        this.identidade = identidade;
    }
}

class Empresa {
    private String nome;
    private Pessoa responsavel;

    public Empresa(String nome, Pessoa responsavel) {
        this.nome = nome;
        this.responsavel = responsavel;
    }

    public String getNome(){
        return nome;
    }

    public Pessoa getResponsavel(){
        return responsavel;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setResponsavel(Pessoa responsavel){
        this.responsavel = responsavel;
    }
}

class PessoaBuilder {
    private String nome;
    private int identidade;

    public PessoaBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public PessoaBuilder setIdentidade(int identidade) {
        this.identidade = identidade;
        return this;
    }

    public Pessoa createPessoa() {
        if(nome == null || identidade == 0)
            throw new IllegalArgumentException("Nome ou identidade não podem ser nulos");

        return new Pessoa(nome, identidade);
    }
}

class EmpresaBuilder {
    private String nome;
    private int identidadeResponsavel;

    public EmpresaBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public EmpresaBuilder setIdentidadeResponsavel(int identidadeResponsavel) {
        this.identidadeResponsavel = identidadeResponsavel;
        return this;
    }

    public Empresa createEmpresa() {
        if(nome == null || identidadeResponsavel == 0)
            throw new IllegalArgumentException("Nome ou identidade do responsável não podem ser nulos");

        Pessoa responsavel = new PessoaBuilder()
            .setNome(nome)
            .setIdentidade(identidadeResponsavel)
            .createPessoa();

        return new Empresa(nome, responsavel);
    }
}

class Main {
    public static void main(String[] args) {
        Pessoa pessoa = new PessoaBuilder()
            .setNome("João")
            .setIdentidade(123456)
            .createPessoa();

        Empresa empresa = new EmpresaBuilder()
            .setNome("Dono Agenor")
            .setIdentidadeResponsavel(123456)
            .createEmpresa();
    
        System.out.println("Pessoa: " + pessoa.getNome() + " - " + pessoa.getIdentidade());
        System.out.println("Empresa: " + empresa.getNome());
        System.out.println("Responsável da Empresa: " + empresa.getResponsavel().getNome() + " - " + empresa.getResponsavel().getIdentidade());
    }
}
