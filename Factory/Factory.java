import java.util.*;

interface Figura {
    public float area();
}

class Circulo implements Figura {
    private float raio;

    public Circulo(float raio) {
        this.raio = raio;
    }

    public float area() {
        return (float) (Math.PI * raio * raio);
    }
}

class Quadrado implements Figura {
    private float lado;

    public Quadrado(float lado) {
        this.lado = lado;
    }

    public float area() {
        return lado * lado;
    }
}

class Triangulo implements Figura {
    private float base;
    private float altura;

    public Triangulo(float base, float altura) {
        this.base = base;
        this.altura = altura;
    }

    public float area() {
        return base * altura / 2;
    }
}

class CirculoBuilder {
    private float raio;

    public CirculoBuilder setRadio(float raio) {
        this.raio = raio;
        return this;
    }

    public Circulo buildCirculo() {
        if (raio <= 0) {
            throw new IllegalArgumentException("Raio deve ser maior que zero");
        }

        return new Circulo(raio);
    }
}

class QuadradoBuilder {
    private float lado;

    public QuadradoBuilder setLado(float lado) {
        this.lado = lado;
        return this;
    }

    public Quadrado buildQuadrado() {
        if (lado <= 0) {
            throw new IllegalArgumentException("Lado deve ser maior que zero");
        }

        return new Quadrado(lado);
    }
}

class TrianguloBuilder {
    private float base;
    private float altura;

    public TrianguloBuilder setBase(float base) {
        this.base = base;
        return this;
    }

    public TrianguloBuilder setAltura(float altura) {
        this.altura = altura;
        return this;
    }

    public Triangulo buildTriangulo() {
        if (base <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Base e altura devem ser maiores que zero");
        }

        return new Triangulo(base, altura);
    }
}

class FiguraFactory {
    private static HashMap<String, Figura> figuras = new HashMap<>();

    public static Figura getFigura(String tipo, Map<String, Object> propriedades) {
        Figura fig = figuras.get(tipo);
        if (fig == null) {
            fig = criarFigura(tipo, propriedades);
        }
        return fig;
    }
    
    public static Figura criarFigura(String tipo, Map<String, Object> propriedades) {
        Figura fig;
        switch (tipo.toLowerCase()) {
            case "circulo":
                fig = new CirculoBuilder().setRadio((float) propriedades.get("raio")).buildCirculo();
                figuras.put(tipo, fig);
                return fig;
            case "quadrado":
                fig = new QuadradoBuilder().setLado((float) propriedades.get("lado")).buildQuadrado();
                figuras.put(tipo, fig);
                return fig;
            case "triangulo":
                fig = new TrianguloBuilder().setBase((float) propriedades.get("base"))
                        .setAltura((float) propriedades.get("altura")).buildTriangulo();
                figuras.put(tipo, fig);
                return fig;
            default:
                throw new IllegalArgumentException("Tipo de figura inválido");
        }
    }
}

class Main {
    public static void main(String[] args) {
        Map<String, Object> propriedades = new HashMap<>();
        propriedades.put("raio", 5.0f);
        propriedades.put("lado", 8.0f);
        propriedades.put("base", 5.0f);
        propriedades.put("altura", 10.0f);

        Figura circulo = FiguraFactory.getFigura("circulo", propriedades);
        System.out.println("Área do círculo: " + circulo.area());
      
        Figura quadrado = FiguraFactory.getFigura("quadrado", propriedades);
        System.out.println("Área do quadrado: " + quadrado.area());

        Figura triangulo = FiguraFactory.getFigura("triangulo", propriedades);
        System.out.println("Área do triângulo: " + triangulo.area());
    }
}