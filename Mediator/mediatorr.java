import java.util.*;

interface Mediator {
    void realizarPedido(ItemPedido item, PlataformaPedido plataforma);
}

class Lanchonete implements Mediator{
    private List<PlataformaPedido> plataformas = new ArrayList<>();

    public void realizarPedido(ItemPedido item, PlataformaPedido plataforma) {
        for (PlataformaPedido p : plataformas) {
            if (p.equals(plataforma)) {
                p.notificarPedido(item);
            }
        }
    }

    public void adicionarPlataforma(PlataformaPedido plataforma) {
        plataformas.add(plataforma);
    }

    public void removerPlataforma(PlataformaPedido plataforma) {
        plataformas.remove(plataforma);
    }
}

interface PlataformaPedido {
    void fazerPedido(ItemPedido item);
    void notificarPedido(ItemPedido item);
}

class PlataformaPresencial implements PlataformaPedido {
    private Mediator mediator;
    public PlataformaPresencial(Mediator mediator) {
        this.mediator = mediator;
    }
    
    public void fazerPedido(ItemPedido item) {
        mediator.realizarPedido(item, this);
    }
    
    public void notificarPedido(ItemPedido item) {
        System.out.println("Pedido recebido na plataforma presencial: " + item.toString());
    }
}

class PlataformaTelefone implements PlataformaPedido {
    private Mediator mediator;
    public PlataformaTelefone(Mediator mediator) {
        this.mediator = mediator;
    }
    
    public void fazerPedido(ItemPedido item) {
        mediator.realizarPedido(item, this);
    }
    
    public void notificarPedido(ItemPedido item) {
        System.out.println("Pedido recebido na plataforma telefone: " + item.toString());
    }
}

class PlataformaOnline implements PlataformaPedido {
    private Mediator mediator;
    public PlataformaOnline(Mediator mediator) {
        this.mediator = mediator;
    }
        
    public void fazerPedido(ItemPedido item) {
        mediator.realizarPedido(item, this);
    }
    
    public void notificarPedido(ItemPedido item) {
        System.out.println("Pedido recebido na plataforma online: " + item.toString());
    }
}

abstract class ItemPedido {
    private String nome;
    private int quantidade;
    private double preco;

    public ItemPedido(String nome, int quantidade, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public double getPrecoTotal() {
        return preco * quantidade;
    }

    public String toString() {
        return nome + " - " + quantidade + " - " + preco;
    }
}

class Hamburguer extends ItemPedido {
    public Hamburguer(int quantidade) {
        super("Hamburguer", quantidade, 10.0);
    }
}

class BatataFrita extends ItemPedido {
    public BatataFrita(int quantidade) {
        super("Batata Frita", quantidade, 5.0);
    }
}

class Refrigerante extends ItemPedido {
    public Refrigerante(int quantidade) {
        super("Refrigerante", quantidade, 7.0);
    }
}

class Cliente {
    public static void main(String[] args) {
        Lanchonete lanchonete = new Lanchonete();

        PlataformaPedido plataformaPresencial = new PlataformaPresencial(lanchonete);
        PlataformaPedido plataformaTelefone = new PlataformaTelefone(lanchonete);
        PlataformaPedido plataformaOnline = new PlataformaOnline(lanchonete);

        lanchonete.adicionarPlataforma(plataformaPresencial);
        lanchonete.adicionarPlataforma(plataformaTelefone);
        lanchonete.adicionarPlataforma(plataformaOnline);
        
        lanchonete.realizarPedido(new Hamburguer(2), plataformaPresencial);
        lanchonete.realizarPedido(new BatataFrita(1), plataformaTelefone);
        lanchonete.realizarPedido(new Refrigerante(1), plataformaOnline);
    }
}