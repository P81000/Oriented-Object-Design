import java.util.*;

// Classe que adapta a interface Enumeration para a interface Iterator
class EnumerationAdapter<T> implements Iterator<T> {
    private Enumeration<T> enumeration;

    public EnumerationAdapter(Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }

    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    public T next() {
        return enumeration.nextElement();
    }

    public void remove() {
        throw new UnsupportedOperationException("Operação remove() não suportada por Enumeração");
    }
}

class Main {
    public static void main(String[] args) {
              // Exemplo de uso do Adapter para percorrer uma lista que expõe somente a interface Enumeration
        Vector<String> vector = new Vector<>();
        vector.add("Joao");
        vector.add("Maria");
        vector.add("Jose");
        
        Enumeration<String> enumeration = vector.elements();
        Iterator<String> iterator = new EnumerationAdapter<>(enumeration);
        
        while(iterator.hasNext()) {
            String elemento = iterator.next();
            System.out.println(elemento);
        }
    }
}

