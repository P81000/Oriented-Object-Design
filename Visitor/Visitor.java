/*
 * O padrão visitor permite acrescentar novas operações a uma estrutura de objetos sem modificar as classes desses objetos
 * onde as operações são implementadas em uma classe visitor separada. O funcionamento consiste em duas partes: a estrutura
 * de dados que contem os objetos que podem ser visitados pelo visitor e a classe visitor que define diferentes métodos para visitar cada tipo de elemento
 * da estrutura. Quando um elemento é visitado ele chama o metodo correspondente do visitor passando a si mesmo como parâmetro, dessa forma,
 * o visitor pode acessar e atuar sobre o elemento de acordo com a operação especificada no método.
 *
 * Esse padrão é bom pois propaga o princípio de projeto "Aberto para extensão, fechado para modificação" e facilita a adição de novas operações
 * contúdo da trabalho adicionar novos elementos na hierarquia pois requer alteração em todos os visitors, ou seja, não é útil e prático
 * para aplicações em que a estrutura muda com frequência, e outro problema é a quebra de encapsulamento pois os métodos e dados do elemento tem
 * de estar acessíveis para o visitor.
 *
 * Um exemplo de aplicação pode ser o processamento de elementos de uma lista.
 */

 //APLICAÇÃO DO EXEMPLO DADO

import java.util.ArrayList;
import java.util.List;

interface Element {
    void accept(Visitor visitor);
}

interface Visitor {
    void visit(Element element);
}

class ConcreteElementA implements Element {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA() {
        return "Operation A";
    }
}

class ConcreteElementB implements Element {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB() {
        return "Operation B";
    }
}

class ConcreteVisitor1 implements Visitor {
    public void visit(Element element) {
        if (element instanceof ConcreteElementA) {
            System.out.println("Realized " + ((ConcreteElementA) element).operationA() + " on element A by visitor 1");
        } else if (element instanceof ConcreteElementB) {
            System.out.println("Realized " + ((ConcreteElementB) element).operationB() + " on element B by visitor 1");
        }
    }
}

class ConcreteVisitor2 implements Visitor {
    public void visit(Element element) {
        if (element instanceof ConcreteElementA) {
            System.out.println("Realized " + ((ConcreteElementA) element).operationA() + " on element A by visitor 2");
        } else if (element instanceof ConcreteElementB) {
            System.out.println("Realized " + ((ConcreteElementB) element).operationB() + " on element B by visitor 2");
        }
    }
}

class Main {
    public static void main(String[] args) {
        List<Element> elements = new ArrayList<>();
        elements.add(new ConcreteElementA());
        elements.add(new ConcreteElementB());

        Visitor visitor1 = new ConcreteVisitor1();
        Visitor visitor2 = new ConcreteVisitor2();

        for(Element element : elements) {
            element.accept(visitor1);
            element.accept(visitor2);
        }
    }
}