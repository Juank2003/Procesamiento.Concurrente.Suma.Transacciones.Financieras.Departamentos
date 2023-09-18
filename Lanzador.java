import java.io.*;

public class Lanzador {
    public static void main(String[] args) {
        String[] departamentos = { "informatica", "gerencia", "contabilidad", "comercio", "recursos_humanos" };

        // Generar transacciones aleatorias para cada departamento
        for (String departamento : departamentos) {
            Transacciones.generarTransacciones(departamento + ".txt", 1000); // Cambia la cantidad según sea necesario
        }

        // Crear hilos para procesar cada archivo
        Thread[] threads = new Thread[departamentos.length];
        for (int i = 0; i < departamentos.length; i++) {
            threads[i] = new Thread(new ProcesadorContabilidad(departamentos[i] + ".txt"));
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

        // Sumar todas las sumas de departamentos
        long sumaGlobal = UtilidadesFicheros.obtenerSumaTransacciones(departamentos);

        // Guardar resultado global
        try (PrintWriter pw = new PrintWriter(new FileWriter("Resultado_global.txt"))) {
            pw.println(sumaGlobal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
