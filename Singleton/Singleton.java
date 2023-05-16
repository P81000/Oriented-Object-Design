import java.util.*;

class MatriculaGUIFacade {

    private Escola escola;
    private static MatriculaGUIFacade instance;

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
        MatriculaGUIFacade matriculaFacade = MatriculaGUIFacade.getInstance(escola);

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