import java.util.Calendar;
import java.util.GregorianCalendar;

interface DiaSemanaStrategy {
    void imprimirMensagem();
}

class DomingoStrategy implements DiaSemanaStrategy {
    public void imprimirMensagem() {
        System.out.println("Hoje é domingo");
    }
}

class SegundaFeiraStrategy implements DiaSemanaStrategy {
    public void imprimirMensagem() {
        System.out.println("Hoje é segunda-feira.");
    }
}

class TercaFeiraStrategy implements DiaSemanaStrategy {
    public void imprimirMensagem() {
        System.out.println("Hoje é terça-feira.");
    }
}

class QuartaFeiraStrategy implements DiaSemanaStrategy {
    public void imprimirMensagem() {
        System.out.println("Hoje é quarta-feira.");
    }
}

class QuintaFeiraStrategy implements DiaSemanaStrategy {
    public void imprimirMensagem() {
        System.out.println("Hoje é quinta-feira.");
    }
}

class SextaFeiraStrategy implements DiaSemanaStrategy {
    public void imprimirMensagem() {
        System.out.println("Hoje é sexta-feira.");
    }
}

class SabadoStrategy implements DiaSemanaStrategy {
    public void imprimirMensagem() {
        System.out.println("Hoje é sábado.");
    }
}

class DiaSemanaContext {
    private DiaSemanaStrategy strategy;

    public DiaSemanaContext(DiaSemanaStrategy strategy) {
        this.strategy = strategy;
    }

    public void executarEstrategia() {
        strategy.imprimirMensagem();
    }
}

public class Main {
    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar();
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

        DiaSemanaStrategy strategy;

        switch (diaSemana) {
            case Calendar.SUNDAY:
                strategy = new DomingoStrategy();
                break;
            case Calendar.MONDAY:
                strategy = new SegundaFeiraStrategy();
                break;
            case Calendar.TUESDAY:
                strategy = new TercaFeiraStrategy();
                break;
            case Calendar.WEDNESDAY:
                strategy = new QuartaFeiraStrategy();
                break;
            case Calendar.THURSDAY:
                strategy = new QuintaFeiraStrategy();
                break;
            case Calendar.FRIDAY:
                strategy = new SextaFeiraStrategy();
                break;
            case Calendar.SATURDAY:
                strategy = new SabadoStrategy();
                break;
            default:
                strategy = null;
                break;
        }

        if (strategy != null) {
            DiaSemanaContext context = new DiaSemanaContext(strategy);
            context.executarEstrategia();
        } else {
            System.out.println("Dia da semana inválido.");
        }
    }
}
