
import java.util.Date;

public class Donatie {

    private int id_donatie;
    private double suma;
    private Date data_donatie;
    private String metoda_plata;

    public Donatie(int id_donatie, double suma, String metoda_plata) {
        this.id_donatie = id_donatie;
        this.suma = suma;
        this.data_donatie = new Date(); 
        this.metoda_plata = metoda_plata;
    } 
    
    public boolean procesareDonatie() {
        System.out.println("Se proceseaza donatia in valoare de " + suma + " RON prin " + metoda_plata + "...");
        
        if (this.suma > 0 && this.metoda_plata != null && !this.metoda_plata.isEmpty()) {
            System.out.println("Donatie procesata cu succes!");
            return true;
        } else {
            System.out.println("Eroare la procesarea donatiei. Verificati datele introduse.");
            return false;
        }
    }

    public void emitereChitanta() {

        System.out.println("\n==================================");
        System.out.println("        CHITANTA DONATIE");
        System.out.println("==================================");
        System.out.println("ID Donatie: " + id_donatie);
        System.out.println("Data: " + data_donatie);
        System.out.println("Suma donata: " + suma + " RON");
        System.out.println("Metoda de plata: " + metoda_plata);
        System.out.println("===================================\n");
    }

    
    public int getId_donatie() { return id_donatie; }
    public void setId_donatie(int id_donatie) { this.id_donatie = id_donatie; }

    public double getSuma() { return suma; }
    public void setSuma(double suma) { this.suma = suma; }

    public Date getData_donatie() { return data_donatie; }
    public void setData_donatie(Date data_donatie) { this.data_donatie = data_donatie; }

    public String getMetoda_plata() { return metoda_plata; }
    public void setMetoda_plata(String metoda_plata) { this.metoda_plata = metoda_plata; }
}