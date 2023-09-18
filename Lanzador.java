import java.io.*;

public class Lanzador {
    public static void main(String[] args) {
        String[] departamentos = { "informatica", "gerencia", "contabilidad", "comercio", "recursos_humanos" };

        // Crear hilos para procesar cada archivo
        Thread[] threads = new Thread[departamentos.length];
        for (int i = 0; i < departamentos.length; i++) {
            threads[i] = new Thread(new ProcesadorContabilidad(departamentos[i] + ".txt", departamentos[i] + ".txt.res"));
            threads[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Crear un nuevo array para los nombres de archivos de resultados
        String[] archivosResultados = new String[departamentos.length];
        for (int i = 0; i < departamentos.length; i++) {
            archivosResultados[i] = departamentos[i] + ".txt.res";
        }

        // Sumar todas las sumas de departamentos
        long sumaGlobal = UtilidadesFicheros.obtenerSumaTransacciones(archivosResultados);

        // Guardar resultado global
        try (PrintWriter pw = new PrintWriter(new FileWriter("Resultado_global.txt"))) {
            pw.println(sumaGlobal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}