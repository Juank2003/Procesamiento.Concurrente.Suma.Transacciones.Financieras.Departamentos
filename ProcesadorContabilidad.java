import java.io.*;


public class ProcesadorContabilidad implements Runnable {
    private String archivoEntrada;
    private String archivoSalida;

    public ProcesadorContabilidad(String archivoEntrada, String archivoSalida) {
        this.archivoEntrada = archivoEntrada;
        this.archivoSalida = archivoSalida;
    }

    @Override
    public void run() {
        long sumaDepartamento = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             PrintWriter pw = new PrintWriter(new FileWriter(archivoSalida))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                long transaccion = Long.parseLong(linea);
                sumaDepartamento += transaccion;
            }
            
            pw.println(sumaDepartamento);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
