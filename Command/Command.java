/*
 * Strategy vs Command: O padrão Strategy permite selecionar um algoritmo em tempo de execução,
 * proporcionando uma maneira de realizar comportamentos específicos. Por outro lado, o padrão
 * Command encapsula uma solicitação como um objeto, permitindo aos usuários parametrizar clientes
 * com filas, solicitações e operações. Em essência, a Strategy trata de determinar "o que deve ser feito",
 * enquanto o Command aborda "quando e como fazer".

 * State vs Command: O padrão State permite que um objeto altere seu comportamento conforme seu
 * estado interno muda. Em contraste, o padrão Command encapsula uma solicitação como um objeto,
 * separando a solicitação do objeto que a executa. Portanto, o State está mais relacionado à modificação
 * do comportamento de um objeto com base em seu estado, enquanto o Command está focado em encapsular uma
 * ação como um objeto.
 */


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Command Interface
interface Command {
    void execute(String[] args);
}

// Command implementation
class NewCommand implements Command {
    Map<Integer, Pessoa> db;

    NewCommand(Map<Integer, Pessoa> db) {
        this.db = db;
    }

    @Override
    public void execute(String[] args) {
        int id = Integer.parseInt(args[0]);
        String name = args[1];
        db.put(id, new Pessoa(id, name));
    }
}

class DeleteCommand implements Command {
    Map<Integer, Pessoa> db;

    DeleteCommand(Map<Integer, Pessoa> db) {
        this.db = db;
    }

    @Override
    public void execute(String[] args) {
        int id = Integer.parseInt(args[0]);
        db.remove(id);
    }
}

class GetAllCommand implements Command {
    Map<Integer, Pessoa> db;

    GetAllCommand(Map<Integer, Pessoa> db) {
        this.db = db;
    }

    @Override
    public void execute(String[] args) {
        for (Pessoa pessoa : db.values()) {
            System.out.println(pessoa);
        }
    }
}

class GetCommand implements Command {
    Map<Integer, Pessoa> db;

    GetCommand(Map<Integer, Pessoa> db) {
        this.db = db;
    }

    @Override
    public void execute(String[] args) {
        int id = Integer.parseInt(args[0]);
        System.out.println(db.get(id));
    }
}

// Class Pessoa
class Pessoa {
    int id;
    String nome;

    Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome;
    }
}

// BancoPessoas
public class BancoPessoas {
    static Map<String, Command> commands = new HashMap<>();
    static Map<Integer, Pessoa> db = new HashMap<>();

    public static void main(String[] args) {
        commands.put("new", new NewCommand(db));
        commands.put("delete", new DeleteCommand(db));
        commands.put("all", new GetAllCommand(db));
        commands.put("get", new GetCommand(db));

        if (args.length > 0) {
            String commandName = args[0];
            String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
            Command command = commands.get(commandName);
            if (command != null) {
                command.execute(commandArgs);
            }
        }
    }
}