interface GeometricShape {
    void draw();
}

class Ponto implements GeometricShape {
    private int x;
    private int y;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        System.out.println("Ponto: (" + x + ", " + y + ")");
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}


class Circulo implements GeometricShape {
    private Ponto centro;
    private int raio;

    public Circulo(Ponto centro, int raio) {
        this.centro = centro;
        this.raio = raio;
    }

    
    public void draw() {
        System.out.println("Círculo: Centro " + centro.toString() + ", Raio " + raio);
    }
}


class Retangulo implements GeometricShape {
    private Ponto cantoSuperiorEsquerdo;
    private Ponto cantoInferiorDireito;

    public Retangulo(Ponto cantoSuperiorEsquerdo, Ponto cantoInferiorDireito) {
        this.cantoSuperiorEsquerdo = cantoSuperiorEsquerdo;
        this.cantoInferiorDireito = cantoInferiorDireito;
    }

    
    public void draw() {
        System.out.println("Retângulo: Canto Superior Esquerdo " + cantoSuperiorEsquerdo.toString()
                + ", Canto Inferior Direito " + cantoInferiorDireito.toString());
    }
}

class Triangulo implements GeometricShape {
    private Ponto ponto1;
    private Ponto ponto2;
    private Ponto ponto3;

    public Triangulo(Ponto ponto1, Ponto ponto2, Ponto ponto3) {
        this.ponto1 = ponto1;
        this.ponto2 = ponto2;
        this.ponto3 = ponto3;
    }

    
    public void draw() {
        System.out.println("Triângulo: Ponto 1 " + ponto1.toString()
                + ", Ponto 2 " + ponto2.toString()
                + ", Ponto 3 " + ponto3.toString());
    }
}

interface GeometricShapeFactory {
    Ponto criarPonto(int x, int y);
    Circulo criarCirculo(Ponto centro, int raio);
    Retangulo criarRetangulo(Ponto cantoSuperiorEsquerdo, Ponto cantoInferiorDireito);
    Triangulo criarTriangulo(Ponto ponto1, Ponto ponto2, Ponto ponto3);
}

class GeometricShapeFactoryImpl implements GeometricShapeFactory {
    
    public Ponto criarPonto(int x, int y) {
        return new Ponto(x, y);
    }

    
    public Circulo criarCirculo(Ponto centro, int raio) {
        return new Circulo(centro, raio);
    }

    
    public Retangulo criarRetangulo(Ponto cantoSuperiorEsquerdo, Ponto cantoInferiorDireito) {
        return new Retangulo(cantoSuperiorEsquerdo, cantoInferiorDireito);
    }

    
    public Triangulo criarTriangulo(Ponto ponto1, Ponto ponto2, Ponto ponto3) {
        return new Triangulo(ponto1, ponto2, ponto3);
    }
}

class Main {
    public static void main(String[] args) {
        GeometricShapeFactory fabrica = new GeometricShapeFactoryImpl();

        Ponto ponto = fabrica.criarPonto(2, 3);
        Circulo circulo = fabrica.criarCirculo(ponto, 5);
        Retangulo retangulo = fabrica.criarRetangulo(ponto, fabrica.criarPonto(7, 8));
        Triangulo triangulo = fabrica.criarTriangulo(ponto, fabrica.criarPonto(4, 5), fabrica.criarPonto(6, 7));

        ponto.draw();
        circulo.draw();
        retangulo.draw();
        triangulo.draw();
    }
}
