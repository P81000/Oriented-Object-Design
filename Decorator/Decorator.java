/*
 * O padrão Decorator permite adicionar funcionalidades e comportamentos a objetos existentes de forma dinamica
 * e em tempo de execução, sendo uma alternativa para a herança, onde, a partir um objeto original, o padrão decorator faz a criação
 * de classes decoradoras que envolvem e encapsulam o objeto original, implementando a mesma interface mas acrescentando  funcionalidades
 * através da composição.
 *
 * Uma aplicação prática é um sistema de pedidos de uma pizzaria com diversos sabores de pizza. Uma metodo seria para cada novo tipo de pizza
 * criar uma classe para esse tipo, contudo isso torna o programa rigído para receber novas composições uma vez que para cada caso novo
 * uma nova classe deve ser criada. Isso não é eficaz e uma solução seria implementar o padrão decorator, onde a pizza final é uma composição
 * das classes de ingredientes e seus preços unicos, que envolvem uma classe base da pizza, com um elemento que é comum a todas, por exemplo a massa e o molho.
 *
 *
 */

// APLICAÇÃO DO EXEMPLO DADO

interface Pizza {
    String make();
    double cost();
}

class Base implements Pizza {
    public String make() {
        return "Massa + Molho de Tomate";
    }

    public double cost() {
        return 15.0;
    }
}

class CheeseDecorator implements Pizza {
    private Pizza pizza;
    
    public CheeseDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public String make() {
        return pizza.make() + " + Queijo";
    }

    public double cost() {
        return pizza.cost() + 5.0;
    }
}

class PepperoniDecorator implements Pizza {
    private Pizza pizza;

    public PepperoniDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public String make() {
        return pizza.make() + " + Pepperoni";
    }

    public double cost() {
        return pizza.cost() + 7.0;
    }
}

class BaconDecorator implements Pizza {
    private Pizza pizza;

    public BaconDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public String make() {
        return pizza.make() + " + Bacon";
    }

    public double cost() {
        return pizza.cost() + 10.0;
    }
}

class Main {
    public static void main(String[] args) {
        Pizza base = new Base();

        Pizza pizzaMussarela = new CheeseDecorator(base);
        Pizza pizzaPepperoni = new PepperoniDecorator(base);
        Pizza pizzaPepperoniCheese = new PepperoniDecorator(pizzaMussarela);
        Pizza pizzaBacon = new BaconDecorator(pizzaMussarela);

        System.out.println("Mussarela: ");
        System.out.println(pizzaMussarela.make());
        System.out.println(pizzaMussarela.cost());

        System.out.println("Pepperoni: ");
        System.out.println(pizzaPepperoni.make());
        System.out.println(pizzaPepperoni.cost());

        System.out.println("Pepperoni + Mussarela: ");
        System.out.println(pizzaPepperoniCheese.make());
        System.out.println(pizzaPepperoniCheese.cost());

        System.out.println("Bacon + Mussarela: ");
        System.out.println(pizzaBacon.make());
        System.out.println(pizzaBacon.cost());
    }
}