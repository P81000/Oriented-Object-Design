/*
 * O padrão de design Interpreter é um padrão que permite interpretar e avaliar expressões em uma linguagem expecífica
 * onde cria para essa linguagem uma gramática e um interpretador responsável por analisar e executar as expressões desejadas.
 *
 * O padrão possui um funcionamento simples que consiste na criação de uma estrutura de dados que representa a gramática da linguagem
 * e na implementação das classes que irão interpretar cada elemento da expressão, interpretando um aspecto expecífico.
 *
 * Para melhor visualização, uma aplicação seria na resolução de operações matemáticas, como as de uma calculadora simples
 * por exemplo, poderiamos definir uma gramática da seguinte forma:
 *
 * expressão -> numero | expressão + expressão | expressão - expressão | expressão * expressão | expressão / expressão
 * numero -> 0 | 1 | ... | 9
 *
 * onde aplicariamos um interpretador para receber e executar a expressão dada, onde cada elemento da gramática seria uma classe
 *
 */

// APLICAÇÃO DO EXEMPLO DADO

interface expression {
    public int interpret();
}

class number implements expression {
    private int number;

    public number(int number) {
        this.number = number;
    }

    public int interpret() {
        return number;
    }
}

class sum implements expression {
    private expression left;
    private expression right;

    public sum(expression left, expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

class sub implements expression {
    private expression left;
    private expression right;

    public sub(expression left, expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        return left.interpret() - right.interpret();
    }
}

class mult implements expression {
    private expression left;
    private expression right;

    public mult(expression left, expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        return left.interpret() * right.interpret();
    }
}

class div implements expression {
    private expression left;
    private expression right;

    public div(expression left, expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        if(right.interpret() != 0){
            return left.interpret() / right.interpret();
        }
        System.out.println("Cannot divide by zero");
        return 0;
    }
}

//Expressão exemplo = 5 * 10 + 10 / 10
class Main {
    public static void main(String[] args) {
        expression exp1 = new mult(new number(5), new number(10));
        expression exp2 = new div(new number(10), new number(10));
        expression exp3 = new sum(exp1, exp2);

        int result = exp3.interpret();

        System.out.println("Result for 5 * 10 + 10 / 10 is = " + result);

        /* OUTPUT HAS TO BE */
        /* Result for 5 * 10 + 10 / 10 is = 51 */
    }
}