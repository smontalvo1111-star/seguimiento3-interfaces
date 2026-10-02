import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Ejercicio5_Function {
    public static void main(String[] args) {
        Function<Integer, Integer> cuadrado = n -> n * n;
        Predicate<Integer> positivo = n -> n > 0;
        Supplier<String> saludo = () -> "¡Bienvenido!";

        System.out.println("Cuadrado de 7: " + cuadrado.apply(7));
        System.out.println("¿17 es positivo? " + positivo.test(17));
        System.out.println(saludo.get());
    }
}
