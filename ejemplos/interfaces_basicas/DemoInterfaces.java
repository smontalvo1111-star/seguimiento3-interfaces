import java.util.List;

interface Sonoro {
    String hacerSonido();
}

class Perro implements Sonoro {
    @Override
    public String hacerSonido() {
        return "¡Guau!";
    }
}

class Gato implements Sonoro {
    @Override
    public String hacerSonido() {
        return "¡Miau!";
    }
}

class Vaca implements Sonoro {
    @Override
    public String hacerSonido() {
        return "¡Muuu!";
    }
}

interface Notificador {
    void enviar(String mensaje);

    default void enviarUrgente(String mensaje) {
        enviar("[URGENTE] " + mensaje.toUpperCase());
    }
}

class NotificadorEmail implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Email → " + mensaje);
    }
}

class NotificadorSMS implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("SMS → " + mensaje);
    }

    @Override
    public void enviarUrgente(String mensaje) {
        enviar("!!! " + mensaje + " (responde SI)");
    }
}

interface Volador {
    void volar();
}

interface Nadador {
    void nadar();
}

class Pato implements Volador, Nadador {
    @Override
    public void volar() {
        System.out.println("Pato: aleteo sobre el lago");
    }

    @Override
    public void nadar() {
        System.out.println("Pato: remo con mis patas");
    }
}

public class DemoInterfaces {
    public static void main(String[] args) {
        List<Sonoro> granja = List.of(new Perro(), new Gato(), new Vaca());
        for (Sonoro animal : granja) {
            System.out.println(animal.getClass().getSimpleName() + " dice " + animal.hacerSonido());
        }

        Notificador[] canales = {new NotificadorEmail(), new NotificadorSMS()};
        for (Notificador canal : canales) {
            canal.enviar("Tu pedido fue enviado");
            canal.enviarUrgente("Tu clave expira hoy");
        }

        Pato lucas = new Pato();
        lucas.nadar();
        lucas.volar();
    }
}
