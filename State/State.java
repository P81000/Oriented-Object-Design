interface EstadoAutenticacao {
    void exibirOpcoes();
    void lidarComEntradaDoUsuario(String entrada);
}

class EstadoAutenticado implements EstadoAutenticacao {
    public void exibirOpcoes() {
        System.out.println("Opções disponíveis:");
        System.out.println("1. Visualizar perfil");
        System.out.println("2. Fazer logout");
    }

    public void lidarComEntradaDoUsuario(String entrada) {
        if (entrada.equals("1")) {
            System.out.println("Visualizando perfil...");
        } else if (entrada.equals("2")) {
            System.out.println("Fazendo logout...");
            ContextoAutenticacao.setEstadoAtual(new EstadoNaoAutenticado());
        } else {
            System.out.println("Opção inválida.");
        }
    }
}

class EstadoNaoAutenticado implements EstadoAutenticacao {
    public void exibirOpcoes() {
        System.out.println("Opções disponíveis:");
        System.out.println("1. Fazer login");
    }

    public void lidarComEntradaDoUsuario(String entrada) {
        if (entrada.equals("1")) {
            System.out.println("Fazendo login...");
            // Simulação de autenticação bem-sucedida
            ContextoAutenticacao.setEstadoAtual(new EstadoAutenticado());
        } else {
            System.out.println("Opção inválida.");
        }
    }
}

class ContextoAutenticacao {
    private static EstadoAutenticacao estadoAtual;

    public static void setEstadoAtual(EstadoAutenticacao estado) {
        estadoAtual = estado;
    }

    public static void exibirOpcoes() {
        estadoAtual.exibirOpcoes();
    }

    public static void lidarComEntradaDoUsuario(String entrada) {
        estadoAtual.lidarComEntradaDoUsuario(entrada);
    }
}

class Main {
    public static void main(String[] args) {
        ContextoAutenticacao.setEstadoAtual(new EstadoNaoAutenticado());

        // Exibe as opções iniciais
        ContextoAutenticacao.exibirOpcoes();

        // Simula a entrada do usuário
        String entrada = "1";
        ContextoAutenticacao.lidarComEntradaDoUsuario(entrada);

        // Exibe as opções após o login
        ContextoAutenticacao.exibirOpcoes();

        // Simula a entrada do usuário
        entrada = "2";
        ContextoAutenticacao.lidarComEntradaDoUsuario(entrada);

        // Exibe as opções após o logout
        ContextoAutenticacao.exibirOpcoes();
    }
}
