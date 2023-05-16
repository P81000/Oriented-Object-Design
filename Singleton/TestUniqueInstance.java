import java.util.*;

class MatriculaGUIFacade {

    private Escola escola;
    private static MatriculaGUIFacade  instance;

    public MatriculaGUIFacade(Escola escola) {
      this.escola = escola;
    }
    
    public static MatriculaGUIFacade getInstance(Escola escola) {
        if (instance == null) {
          instance = new MatriculaGUIFacade(escola);
        }
        return instance;
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

        MatriculaGUIFacade facade1 = new MatriculaGUIFacade(escola);
        MatriculaGUIFacade facade2 = new MatriculaGUIFacade(escola);
        MatriculaGUIFacade facade3 = new MatriculaGUIFacade(escola);
        
        if(facade1 == facade2 && facade1 == facade3){
            System.out.println("Instâncias iguais");
        }
        else {
            System.out.println("Instâncias diferentes");
        }
    }
}


