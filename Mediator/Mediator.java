import java.util.*;

interface PedidoMediator {
  void realizarPedido(ItemPedido item, PlataformaPedido plataforma);

  void adicionarPlataforma(PlataformaPedido plataforma);
}

class LanchoneteMediator implements PedidoMediator {
  private List<PlataformaPedido> plataformas = new ArrayList<>();

  public void realizarPedido(ItemPedido item, PlataformaPedido plataforma) {
    for (PlataformaPedido p : plataformas) {
      if (!p.equals(plataforma)) {
        p.notificarPedido(item);
      }
    }
  }

  public void adicionarPlataforma(PlataformaPedido plataforma) {
    plataformas.add(plataforma);
  }
}

interface PlataformaPedido {
  void fazerPedido(ItemPedido item);

  void notificarPedido(ItemPedido item);
}

class PlataformaPresencial implements PlataformaPedido {
  private PedidoMediator mediator;

  public PlataformaPresencial(PedidoMediator mediator) {
    this.mediator = mediator;
    mediator.adicionarPlataforma(this);
  }

  public void fazerPedido(ItemPedido item) {
    mediator.realizarPedido(item, this);
  }

  public void notificarPedido(ItemPedido item) {
    System.out.println("Pedido recebido na plataforma presencial: " + item.toString());
  }
}

class PlataformaTelefone implements PlataformaPedido {
  private PedidoMediator mediator;

  public PlataformaTelefone(PedidoMediator mediator) {
    this.mediator = mediator;
    mediator.adicionarPlataforma(this);
  }

  public void fazerPedido(ItemPedido item) {
    mediator.realizarPedido(item, this);
  }

  public void notificarPedido(ItemPedido item) {
    System.out.println("Pedido recebido na plataforma telefone: " + item.toString());
  }
}

class PlataformaDelivery implements PlataformaPedido {
  private PedidoMediator mediator;

  public PlataformaDelivery(PedidoMediator mediator) {
    this.mediator = mediator;
    mediator.adicionarPlataforma(this);
  }

  public void fazerPedido(ItemPedido item) {
    mediator.realizarPedido(item, this);
  }

  public void notificarPedido(ItemPedido item) {
    System.out.println("Pedido recebido na plataforma delivery: " + item.toString());
  }
}

abstract class ItemPedido {
  private String nome;
  private double preco;

  public ItemPedido(String nome, double preco) {
    this.nome = nome;
    this.preco = preco;
  }

  public String getNome() {
    return nome;
  }

  public double getPreco() {
    return preco;
  }

  public abstract double calcularPreco();

  public String toString() {
    return nome + " - R$" + String.format("%.2f", preco);
  }
}

class ItemSimples extends ItemPedido {

  public ItemSimples(String nome, double preco) {
    super(nome, preco);
  }

  public double calcularPreco() {
    return getPreco();
  }
}

class ItemComposto extends ItemPedido {
  private List<ItemPedido> itens = new ArrayList<>();
  private double preco;
  
  public ItemComposto(String nome) {
    super(nome, 0);
  }

  public void adicionarItem(ItemPedido item) {
    itens.add(item);
    this.preco = getPreco() + item.getPreco();
  }

  public void removerItem(ItemPedido item) {
    itens.remove(item);
    this.preco = getPreco() - item.getPreco();
  }

  public double calcularPreco() {
    double precoTotal = 0;
    for (ItemPedido item : itens) {
      precoTotal += item.calcularPreco();
    }
    return precoTotal;
  }

  public double getPreco(){
    return this.preco;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getNome()).append(" - R$").append(String.format("%.2f", getPreco())).append("\n");
    for (ItemPedido item : itens) {
      sb.append("  - ").append(item.toString()).append("\n");
    }
    return sb.toString();
  }
}

class Main {
  public static void main(String[] args) {
    PedidoMediator mediator = new LanchoneteMediator();

    PlataformaPedido presencial = new PlataformaPresencial(mediator);
    PlataformaPedido telefone = new PlataformaTelefone(mediator);
    PlataformaPedido delivery = new PlataformaDelivery(mediator);

    ItemPedido h1 = new ItemSimples("Hambúrguer comum", 10);
    ItemPedido h2 = new ItemSimples("Hambúrguer com queijo", 12);
    ItemPedido h3 = new ItemSimples("Hambúrguer com bacon", 14);

    ItemPedido b1 = new ItemSimples("Batata frita pequena", 5);
    ItemPedido b2 = new ItemSimples("Batata frita grande", 8);

    ItemComposto combo = new ItemComposto("Combo");
    combo.adicionarItem(h1);
    combo.adicionarItem(b1);

    ItemComposto megaCombo = new ItemComposto("Mega Combo");
    megaCombo.adicionarItem(h2);
    megaCombo.adicionarItem(b2);
    megaCombo.adicionarItem(new ItemSimples("Refrigerante 500ml", 6));

    presencial.fazerPedido(h3);
    telefone.fazerPedido(combo);
    delivery.fazerPedido(megaCombo);
  }
}
