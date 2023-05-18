
import java.util.*; //Using hashtable to map numbers

class Algarismo {
    private final int numero;
    private String cor;

    public Algarismo(int numero) {
        this.numero = numero;
        this.cor = obterCorAleatoria();
    }

    public int getNumero() {
        return numero;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String toString() {
        return String.valueOf(numero);
    }

    public String obterCorAleatoria() {
        String[] cores = {"vermelho", "azul", "amarelo", "verde"};
        Random random = new Random();
        return cores[random.nextInt(cores.length)];
    }
}

class AlgarismoFactory {
    private static final Map<Integer, Algarismo> algarismos = new HashMap<>();

    public static Algarismo getAlgarismo(int numero) {
        if (!algarismos.containsKey(numero)) {
            algarismos.put(numero, new Algarismo(numero));
        }
        return algarismos.get(numero);
    }
}

class Main {
    public static void main(String[] args) {
        List<String> numeros = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            StringBuilder numero = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                int algarismo = new Random().nextInt(9) + 1;
                Algarismo objAlgarismo = AlgarismoFactory.getAlgarismo(algarismo);
                numero.append(objAlgarismo.toString()).append(" (").append(objAlgarismo.getCor()).append(") ");
                objAlgarismo.setCor(objAlgarismo.obterCorAleatoria());
            }
            numeros.add(numero.toString());
        }

        for (String numero : numeros) {
            System.out.println(numero);
        }
    }
}
