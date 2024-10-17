package clase;
public class Main {
    public static void main(String[] args) {
        RelojAcelerado reloj = new RelojAcelerado(120);  //acelearado 120 veces el tiempo real
        while (true) {  
            System.out.println(reloj.getTiempoAcelerado());
            try {
                Thread.sleep(1000); //espera 1 segundo entre cada impresion, es para que lo vean, lo volamos despues
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
