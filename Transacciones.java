import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;



public class Transacciones {
    public static void generarTransacciones(String archivo, int cantidadTransacciones) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            Random random = new Random();
            for (int i = 0; i < cantidadTransacciones; i++) {
                long transaccion = random.nextLong();
                pw.println(transaccion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}