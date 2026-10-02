interface FormateadorEj3 {
    String formatear(String texto);

    default String conMarco(String texto) {
        String contenido = "| " + formatear(texto) + " |";
        String borde = linea(contenido.length());
        return borde + "\n" + contenido + "\n" + borde;
    }

    static String limpiar(String texto) {
        return texto.trim().replaceAll("\\s+", " ");
    }

    private String linea(int largo) {
        return "+" + "-".repeat(largo - 2) + "+";
    }
}

class MayusculasEj3 implements FormateadorEj3 {
    @Override
    public String formatear(String texto) {
        return texto.toUpperCase();
    }
}

public class Ejercicio3_DefaultStaticPrivate {
    public static void main(String[] args) {
        String texto = FormateadorEj3.limpiar(" hola mundo java ");
        FormateadorEj3 f = new MayusculasEj3();
        System.out.println(f.conMarco(texto));
    }
}
