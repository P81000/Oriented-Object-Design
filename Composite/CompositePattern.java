import java.util.*;

interface Participante {
    int getAssento();
}

class Individuo implements Participante {
    public int getAssento(){
        return 1;
    }
}

class Instituicao implements Participante{
    private List<Participante> membros = new ArrayList<>();

    public void addMembro(Participante membro){
        membros.add(membro);
    }

    public List<Participante> getMembros(){
        return membros;
    }

    public int getAssento(){
        int totalAssentos = 0;
        for (Participante membro : membros){
            totalAssentos += membro.getAssento();
        }
        return totalAssentos; 
    }
}

class Congresso {
    private List<Participante> participantes = new ArrayList<>();

    public void addParticipante (Participante participante) {
        participantes.add(participante);
    }

    public int totalParticipantes() {
        return participantes.size();
    }

    public int totalAssentos() {
        int totalAssentos = 0;
        for (Participante participante : participantes){
            totalAssentos += participante.getAssento();
        }
        return totalAssentos;
    }
}
class Main {
  public static void main(String[] args) {
      Congresso  congresso = new Congresso();
      Individuo p1 = new Individuo();
      Instituicao i1 = new Instituicao();

      congresso.addParticipante(i1);
      congresso.addParticipante(p1);
      int total = congresso.totalParticipantes();
      System.out.println(total);
  }
}