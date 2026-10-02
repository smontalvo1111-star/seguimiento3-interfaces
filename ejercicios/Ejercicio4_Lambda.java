@FunctionalInterface
interface OperacionEj4 {
    int aplicar(int a, int b);
}

public class Ejercicio4_Lambda {
    public static void main(String[] args) {
        OperacionEj4 suma = (a, b) -> a + b;
        OperacionEj4 resta = (a, b) -> a - b;

        System.out.println("8 + 2 = " + suma.aplicar(8, 2));
        System.out.println("8 - 2 = " + resta.aplicar(8, 2));
    }
}
