import java.util.*;

class Aluno {

  private int matricula;
  private String nome;

  public Aluno(int matricula, String nome) {
    this.matricula = matricula;
    this.nome = nome;
  }

  public int getMatricula() {
    return matricula;
  }

  public String getNome() {
    return nome;
  }
}

class Curso {

  private int codigo;
  private String nome;

  public Curso(int codigo, String nome) {
    this.codigo = codigo;
    this.nome = nome;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }
}

class Turma {

  private Curso curso;
  private List<Aluno> alunos;

  public Turma(Curso curso) {
    this.curso = curso;
    this.alunos = new ArrayList<>();
  }

  public Curso getCurso() {
    return curso;
  }

  public List<Aluno> getAlunos() {
    return alunos;
  }

  public Aluno getAluno(int matricula) {
    for (Aluno aluno : alunos) {
      if (aluno.getMatricula() == matricula) {
        return aluno;
      }
    }
    return null;
  }

  public void adicionaAluno(Aluno aluno) {
    alunos.add(aluno);
  }
}

class Escola {

  private List<Aluno> alunos;
  private List<Curso> cursos;
  private List<Turma> turmas;

  public Escola() {
    this.alunos = new ArrayList<>();
    this.cursos = new ArrayList<>();
    this.turmas = new ArrayList<>();
  }

  public Aluno criaAluno(int matricula, String nome) {
    Aluno aluno = new Aluno(matricula, nome);
    alunos.add(aluno);
    return aluno;
  }

  public Curso adicionaCurso(Curso curso) {
    cursos.add(curso);
    return curso;
  }

  public Aluno getAluno(int matricula) {
    for (Aluno aluno : alunos) {
      if (aluno.getMatricula() == matricula) {
        return aluno;
      }
    }
    return null;
  }

  public Curso getCurso(int codigo) {
    for (Curso curso : cursos) {
      if (curso.getCodigo() == codigo) return curso;
    }
    return null;
  }

  public Turma getTurma(int codigoCurso) {
    for (Turma turma : turmas) {
      if (turma.getCurso().getCodigo() == codigoCurso) return turma;
    }
    return null;
  }

  public Turma encontraTurma(int codigoCurso) {
    for (Turma turma : turmas) {
      if (turma.getCurso().getCodigo() == codigoCurso) return turma;
    }
    return null;
  }

  public Turma criaTurma(int codigoCurso) {
    Curso curso = getCurso(codigoCurso);
    Turma turma = new Turma(curso);
    turmas.add(turma);
    return turma;
  }
}

class MatriculaGUIFacade {

  private Escola escola;

  public MatriculaGUIFacade(Escola escola) {
    this.escola = escola;
  }

  public void matricularAluno(int matricula, int codigoCurso) {
    Aluno aluno = escola.getAluno(matricula);
    Turma turma = escola.encontraTurma(codigoCurso);

    if (turma == null) {
      turma = escola.criaTurma(codigoCurso);
    }
    turma.adicionaAluno(aluno);
  }

  public Curso adicionaCurso(int codigo, String nome) {
    Curso curso = new Curso(codigo, nome);
    escola.adicionaCurso(curso);
    return curso;
  }

  public Turma criaTurma(int codigoCurso) {
    Turma turma = escola.criaTurma(codigoCurso);
    return turma;
  }

  public Aluno criaAluno(int matricula, String nome) {
    Aluno aluno = escola.criaAluno(matricula, nome);
    return aluno;
  }

  public void exibirStatus(int codigoCurso) {
    Turma turma = escola.encontraTurma(codigoCurso);
    System.out.println("\nCurso: " + turma.getCurso().getNome());
    List<Aluno> alunosTurma = turma.getAlunos();
    for (Aluno aluno : alunosTurma) {
      System.out.println("Nome: " + aluno.getNome() + " | Matricula: " + aluno.getMatricula());
    }
  }
}

class Main {

  public static void main(String[] args) {
    Escola escola = new Escola();
    MatriculaGUIFacade matriculaFacade = new MatriculaGUIFacade(escola);

    Curso Java = matriculaFacade.adicionaCurso(1, "Java");
    Curso C = matriculaFacade.adicionaCurso(2, "C");
    Curso Python = matriculaFacade.adicionaCurso(3, "Python");
    Curso Rust = matriculaFacade.adicionaCurso(4, "Rust");
    Aluno Caio = matriculaFacade.criaAluno(1, "Caio");
    Aluno Joao = matriculaFacade.criaAluno(2, "Joao");
    Aluno Maria = matriculaFacade.criaAluno(3, "Maria");
    Aluno Pedro = matriculaFacade.criaAluno(4, "Pedro");
    matriculaFacade.matricularAluno(Caio.getMatricula(), Java.getCodigo());

    matriculaFacade.matricularAluno(Joao.getMatricula(), C.getCodigo());

    matriculaFacade.matricularAluno(Maria.getMatricula(), C.getCodigo());

    matriculaFacade.matricularAluno(Pedro.getMatricula(), Rust.getCodigo());

    matriculaFacade.matricularAluno(Joao.getMatricula(), Python.getCodigo());

    matriculaFacade.exibirStatus(Java.getCodigo());
    matriculaFacade.exibirStatus(C.getCodigo());
    matriculaFacade.exibirStatus(Rust.getCodigo());
    matriculaFacade.exibirStatus(Python.getCodigo());
  }
}
