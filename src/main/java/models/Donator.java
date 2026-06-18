package models;
import java.util.ArrayList;
import java.util.List;

public class Donator extends Utilizator {

    private TipDonator tip_donator;
    
    // Relatia de 1 la 1..* catre Donatie
    private List<Donatie> istoricDonatii;

    public Donator(int id_utilizator, String nume, String email, String parola, TipDonator tip_donator) {
        super(id_utilizator, nume, email, parola); 
        this.tip_donator = tip_donator;
        this.istoricDonatii = new ArrayList<>(); 
    }
    
    public void realizeazaDonatie(double suma) {
        this.realizeazaDonatie(suma, "Transfer Bancar");
    }

    public void realizeazaDonatie(double suma, String metodaPlataDorita) {
        
        System.out.println("Donatorul " + this.nume + " doreste sa faca o donatie de " + suma + " RON.");
        
        int idNouaDonatie = istoricDonatii.size() + 1;
        
        Donatie donatieNoua = new Donatie(idNouaDonatie, suma, metodaPlataDorita);
        
        boolean status = donatieNoua.procesareDonatie();
        
        if (status) {
            istoricDonatii.add(donatieNoua);
            donatieNoua.emitereChitanta();
        }
    }
    public TipDonator getTip_donator() { return tip_donator; }
    public void setTip_donator(TipDonator tip_donator) { this.tip_donator = tip_donator; }

    public List<Donatie> getIstoricDonatii() { return istoricDonatii; }
}