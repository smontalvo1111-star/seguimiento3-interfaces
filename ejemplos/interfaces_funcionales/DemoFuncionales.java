import java.util.List;
import java.util.function.*;

@FunctionalInterface
interface Operacion {
    int aplicar(int a, int b);
}

public class DemoFuncionales {
    public static void main(String[] args) {
        Operacion suma = (a, b) -> a + b;
        Operacion potenciaNo; // se inicializa abajo para mostrar distintas formas

        Predicate<String> esLarga = s -> s.length() > 5;
        Function<String, Integer> longitud = String::length;
        Consumer<String> imprimir = System.out::println;
        Supplier<String> saludo = () -> "¡Bienvenido al laboratorio!";
        UnaryOperator<String> gritar = s -> s.toUpperCase() + "!";
        BiFunction<Integer, Integer, Integer> mayor = Math::max;

        System.out.println("8 + 2 = " + suma.aplicar(8, 2));
        System.out.println("¿'interfaz' es larga? " + esLarga.test("interfaz"));
        System.out.println("Longitud de 'enum': " + longitud.apply("enum"));
        imprimir.accept(saludo.get());
        System.out.println("Mayor entre 17 y 42: " + mayor.apply(17, 42));

        Predicate<String> empiezaConJ = s -> s.startsWith("J");
        Predicate<String> largaYConJ = esLarga.and(empiezaConJ);

        List<String> palabras = List.of("Java", "JavaScript", "Kotlin", "JetBrains", "Go");
        palabras.stream()
                .filter(largaYConJ)
                .map(gritar)
                .forEach(imprimir);

        potenciaNo = (a, b) -> (int) Math.pow(a, b);
        System.out.println("2 ^ 10 = " + potenciaNo.aplicar(2, 10));
    }
}
