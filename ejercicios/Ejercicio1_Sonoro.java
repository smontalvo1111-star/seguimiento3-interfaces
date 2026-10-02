interface SonoroEj1 {
    String hacerSonido();
}

class PerroEj1 implements SonoroEj1 {
    @Override
    public String hacerSonido() {
        return "¡Guau!";
    }
}

class GatoEj1 implements SonoroEj1 {
    @Override
    public String hacerSonido() {
        return "¡Miau!";
    }
}

public class Ejercicio1_Sonoro {
    static void hacerSonar(SonoroEj1 animal) {
        System.out.println(animal.hacerSonido());
    }

    public static void main(String[] args) {
        SonoroEj1 firulais = new PerroEj1();
        SonoroEj1 michi = new GatoEj1();
        hacerSonar(firulais);
        hacerSonar(michi);
    }
}
