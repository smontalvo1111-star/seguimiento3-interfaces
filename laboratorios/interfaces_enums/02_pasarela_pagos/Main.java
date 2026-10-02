interface MetodoPago {
    String nombre();
    boolean pagar(double monto);

    default double comision(double monto) {
        return 0;
    }

    default double totalACobrar(double monto) {
        return monto + comision(monto);
    }
}

class TarjetaCredito implements MetodoPago {
    private double cupoDisponible;

    TarjetaCredito(double cupo) {
        this.cupoDisponible = cupo;
    }

    @Override
    public String nombre() {
        return "Tarjeta de crédito";
    }

    @Override
    public double comision(double monto) {
        return monto * 0.03;
    }

    @Override
    public boolean pagar(double monto) {
        double total = totalACobrar(monto);
        if (total > cupoDisponible) {
            return false;
        }
        cupoDisponible -= total;
        return true;
    }
}

class BilleteraDigital implements MetodoPago {
    private double saldo;

    BilleteraDigital(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String nombre() {
        return "Billetera digital";
    }

    @Override
    public boolean pagar(double monto) {
        if (monto > saldo) {
            return false;
        }
        saldo -= monto;
        return true;
    }
}

class Efectivo implements MetodoPago {
    @Override
    public String nombre() {
        return "Efectivo";
    }

    @Override
    public boolean pagar(double monto) {
        return true;
    }
}

class Caja {
    static void cobrar(MetodoPago metodo, double monto) {
        String resultado = metodo.pagar(monto) ? "APROBADO" : "RECHAZADO";
        System.out.printf("%-20s $%,10.0f comisión $%,7.0f %s%n",
                metodo.nombre(), monto, metodo.comision(monto), resultado);
    }
}

public class Main {
    public static void main(String[] args) {
        Caja.cobrar(new TarjetaCredito(500_000), 120_000);
        Caja.cobrar(new BilleteraDigital(50_000), 80_000);
        Caja.cobrar(new Efectivo(), 35_000);
        Caja.cobrar(new TarjetaCredito(100_000), 99_000);
    }
}
