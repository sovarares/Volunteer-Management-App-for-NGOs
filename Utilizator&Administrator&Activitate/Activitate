
import java.util.Date;

public class Activitate {
    private int id_activitate;
    private String titlu;
    private String descriere;
    private Date data_inceput;
    private TipActivitate tip_activitate;
    private int locuri_disponibile;

    public Activitate(int id_activitate, String titlu, String descriere, Date data_inceput, TipActivitate tip_activitate, int locuri_disponibile) {
        this.id_activitate = id_activitate;
        this.titlu = titlu;
        this.descriere = descriere;
        this.data_inceput = data_inceput;
        this.tip_activitate = tip_activitate;
        this.locuri_disponibile = locuri_disponibile;
    }
    
    public boolean verificaDisponibilitate() {
        System.out.println("Se verifică disponibilitatea pentru activitatea: " + this.titlu);
        
        if (this.locuri_disponibile > 0) {
            System.out.println("Locuri disponibile: " + this.locuri_disponibile);
            return true;
        } else {
            System.out.println("Ne pare rău, nu mai sunt locuri disponibile.");
            return false;
        }
    }
    
    public int getId_activitate() { return id_activitate; }
    public String getTitlu() { return titlu; }
    public void setTitlu(String titlu) { this.titlu = titlu; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public void setLocuri_disponibile(int locuri) { this.locuri_disponibile = locuri; }
    
    public String afisare() {
        return "ID: " + id_activitate + " | " + titlu + " (" + tip_activitate + ") - Locuri: " + locuri_disponibile;
    }
    
}
