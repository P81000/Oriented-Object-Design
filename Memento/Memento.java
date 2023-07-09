import java.util.ArrayList;
import java.util.List;

// Mediador das diferentes plataformas da lanchonete
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

// Plataformas da lanchonete
interface PlataformaPedido {
  void fazerPedido(ItemPedido item);

  void notificarPedido(ItemPedido item);

  void restaurarCarrinho();
}

class PlataformaPresencial implements PlataformaPedido {
  private PedidoMediator mediator;
  private Carrinho carrinho;

  public PlataformaPresencial(PedidoMediator mediator) {
    this.mediator = mediator;
    this.carrinho = new Carrinho();
    mediator.adicionarPlataforma(this);
  }

  public void fazerPedido(ItemPedido item) {
    carrinho.adicionarItem(item);
    carrinho.salvarEstado();
    mediator.realizarPedido(item, this);
  }

  public void notificarPedido(ItemPedido item) {
    System.out.println("Pedido recebido na plataforma presencial: " + carrinho.toString());
  }

  public void restaurarCarrinho() {
    carrinho.restaurarEstado();
  }
}

class PlataformaTelefone implements PlataformaPedido {
  private PedidoMediator mediator;
  private Carrinho carrinho;

  public PlataformaTelefone(PedidoMediator mediator) {
    this.mediator = mediator;
    this.carrinho = new Carrinho();
    mediator.adicionarPlataforma(this);
  }

  public void fazerPedido(ItemPedido item) {
    carrinho.adicionarItem(item);
    carrinho.salvarEstado();
    mediator.realizarPedido(item, this);
  }

  public void notificarPedido(ItemPedido item) {
    System.out.println("Pedido recebido na plataforma telefone: " + carrinho.toString());
  }

  public void restaurarCarrinho() {
    carrinho.restaurarEstado();
  }
}

class PlataformaDelivery implements PlataformaPedido {
  private PedidoMediator mediator;
  private Carrinho carrinho;

  public PlataformaDelivery(PedidoMediator mediator) {
    this.mediator = mediator;
    this.carrinho = new Carrinho();
    mediator.adicionarPlataforma(this);
  }

  public void fazerPedido(ItemPedido item) {
    carrinho.adicionarItem(item);
    carrinho.salvarEstado();
    mediator.realizarPedido(item, this);
  }

  public void notificarPedido(ItemPedido item) {
    System.out.println("Pedido recebido na plataforma delivery: " + carrinho.toString());
  }

  public void restaurarCarrinho() {
    carrinho.restaurarEstado();
  }
}

abstract class ItemPedido {
  private String nome;
  private double preco;

  public ItemPedido(String nome, double preco) {
    this.nome = nome;
    this.preco = preco;
  }

  public double getPreco() {
    return preco;
  }

  public abstract double calcularPreco();

  public String toString() {
    return nome + " - R$" + preco;
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

class Carrinho {
  private List<ItemPedido> itens;
  private Memento estadoAtual;

  public Carrinho() {
    this.itens = new ArrayList<>();
    this.estadoAtual = null;
  }

  public void adicionarItem(ItemPedido item) {
    itens.add(item);
  }

  public void removerItem(ItemPedido item) {
    itens.remove(item);
  }

  public void salvarEstado() {
    estadoAtual = new Memento(new ArrayList<>(itens));
  }

  public void restaurarEstado() {
    if (estadoAtual != null) {
      itens = new ArrayList<>(estadoAtual.getItens());
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (ItemPedido item : itens) {
      sb.append(item.toString()).append("\n");
    }
    return sb.toString();
  }
}

class Memento {
  private List<ItemPedido> itens;

  public Memento(List<ItemPedido> itens) {
    this.itens = itens;
  }

  public List<ItemPedido> getItens() {
    return itens;
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

    presencial.fazerPedido(h3);

    telefone.fazerPedido(h1);
    telefone.fazerPedido(b1);

    delivery.fazerPedido(h2);
    delivery.fazerPedido(b2);

    System.out.println("=== Restaurando estado do carrinho na plataforma presencial ===");
    ((PlataformaPresencial) presencial).restaurarCarrinho();
    ((PlataformaPresencial) presencial).notificarPedido(null);

    System.out.println("=== Restaurando estado do carrinho na plataforma telefone ===");
    ((PlataformaTelefone) telefone).restaurarCarrinho();
    ((PlataformaTelefone) telefone).notificarPedido(null);

    System.out.println("=== Restaurando estado do carrinho na plataforma delivery ===");
    ((PlataformaDelivery) delivery).restaurarCarrinho();
    ((PlataformaDelivery) delivery).notificarPedido(null);
  }
}
