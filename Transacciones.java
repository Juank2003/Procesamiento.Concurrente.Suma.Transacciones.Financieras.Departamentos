import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;



public class Transacciones {
    public static void generarTransacciones(String archivo, int cantidadTransacciones) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            Random random = new Random();
            for (int i = 0; i < cantidadTransacciones; i++) {
                long transaccion = random.nextLong(Long.MAX_VALUE) + 1; // Genera un número aleatorio mayor o igual a 1
                pw.println(transaccion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
