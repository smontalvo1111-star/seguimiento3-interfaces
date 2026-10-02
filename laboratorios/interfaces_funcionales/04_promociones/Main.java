import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;

record Producto(String nombre, String categoria, double precio, int stock) {
    public boolean disponible() {
        return stock > 0;
    }
}

record Promocion(String nombre, Predicate<Producto> aplicaA, DoubleUnaryOperator ajuste) {
    double precioCon(Producto producto) {
        return aplicaA.test(producto) ? Math.max(0, ajuste.applyAsDouble(producto.precio())) : producto.precio();
    }
}

public class Main {
    static DoubleUnaryOperator porcentaje(double pct) {
        return precio -> precio * (1 - pct / 100.0);
    }

    static DoubleUnaryOperator montoFijo(double monto) {
        return precio -> Math.max(0, precio - monto);
    }

    public static void main(String[] args) {
        List<Producto> catalogo = List.of(
                new Producto("Portátil", "tecnología", 3_200_000, 4),
                new Producto("Mouse", "tecnología", 85_000, 25),
                new Producto("Cuaderno", "papelería", 12_000, 0),
                new Producto("Audífonos", "tecnología", 240_000, 8),
                new Producto("Lápiz", "papelería", 2_500, 120),
                new Producto("Silla ergonómica", "muebles", 890_000, 2));

        List<Promocion> promociones = List.of(
                new Promocion("Tecno 10 %",
                        p -> p.categoria().equals("tecnología") && p.disponible(),
                        porcentaje(10)),
                new Promocion("Bono $50.000",
                        p -> p.precio() >= 200_000 && p.disponible(),
                        montoFijo(50_000)),
                new Promocion("Liquidación",
                        p -> p.stock() > 100 && p.disponible(),
                        porcentaje(30)));

        Supplier<Promocion> sinPromocion = () ->
                new Promocion("Sin promoción", p -> true, DoubleUnaryOperator.identity());

        BiConsumer<Producto, Promocion> imprimir = (producto, promo) ->
                System.out.printf("%s %s $%,.0f → $%,.0f%n",
                        producto.nombre(), promo.nombre(), producto.precio(), promo.precioCon(producto));

        ToDoubleBiFunction<Producto, Promocion> ahorro =
                (producto, promo) -> producto.precio() - promo.precioCon(producto);

        double ahorroTotal = 0;

        for (Producto producto : catalogo) {
            if (!producto.disponible()) {
                continue;
            }

            Promocion mejor = sinPromocion.get();
            BinaryOperator<Promocion> menorPrecio = BinaryOperator.minBy(
                    Comparator.comparingDouble(p -> p.precioCon(producto)));

            for (Promocion promo : promociones) {
                mejor = menorPrecio.apply(mejor, promo);
            }

            imprimir.accept(producto, mejor);
            ahorroTotal += ahorro.applyAsDouble(producto, mejor);
        }

        System.out.printf("Ahorro total para el cliente: $%,.0f%n", ahorroTotal);
    }
}
