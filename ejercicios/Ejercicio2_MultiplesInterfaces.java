interface VoladorEj2 {
    void volar();
}

interface NadadorEj2 {
    void nadar();
}

class PatoEj2 implements VoladorEj2, NadadorEj2 {
    @Override
    public void volar() {
        System.out.println("Pato: aleteo sobre el lago");
    }

    @Override
    public void nadar() {
        System.out.println("Pato: remo con mis patas");
    }
}

public class Ejercicio2_MultiplesInterfaces {
    public static void main(String[] args) {
        PatoEj2 pato = new PatoEj2();
        pato.nadar();
        pato.volar();
    }
}
