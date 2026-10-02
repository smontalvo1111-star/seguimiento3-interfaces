import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Cancion> playlist = new ArrayList<>(List.of(
                new Cancion("Bailando", "Enrique Iglesias", 243),
                new Cancion("Despacito", "Luis Fonsi", 228),
                new Cancion("La Bicicleta", "Carlos Vives", 227),
                new Cancion("Tusa", "Karol G", 200)
        ));

        Collections.sort(playlist);
        System.out.println("Orden natural (título):");
        playlist.forEach(c -> System.out.println(c.getTitulo() + " " + c.getArtista() + " " + c.duracion()));

        playlist.sort(Comparator.comparingInt(Cancion::getDuracionSeg).reversed());
        System.out.println("De la más larga a la más corta:");
        playlist.forEach(c -> System.out.println(c.getTitulo() + " (" + c.duracion() + ")"));

        Predicate<Cancion> esLarga = c -> c.getDuracionSeg() > 200;
        List<String> largas = playlist.stream()
                .filter(esLarga)
                .map(c -> c.getTitulo().toUpperCase())
                .toList();

        System.out.println("Canciones largas: " + largas);
    }
}
