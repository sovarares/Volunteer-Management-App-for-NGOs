import java.util.Scanner;

public abstract class Utilizator {
    protected int id_utilizator;
    protected String nume;
    protected String email;
    protected String parola;

    public Utilizator(int id_utilizator, String nume, String email, String parola) {
        this.id_utilizator = id_utilizator;
        this.nume = nume;
        this.email = email;
        this.parola = parola;
    }

    public boolean autentificare(String email, String parola) {
        System.out.println("Se incearca autentificarea pentru: " + email);
        return verificaCredentiale(email, parola);
    }

    public void creareCont(String nume, String email, String parola) {
        if (valideazaEmail(email) && valideazaParola(parola)) {
            this.nume = nume;
            this.email = email;
            this.parola = parola;
            System.out.println("Cont creat cu succes pentru " + nume);
        } 
    }

    public void actualizareProfil() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Meniu Actualizare Profil: " + this.nume + " ---");
        System.out.println("1. Actualizare Nume");
        System.out.println("2. Actualizare Email");
        System.out.println("3. Actualizare Parolă");
        System.out.println("0. Revocare");
        System.out.print("Alegeți o opțiune: ");

        int optiune = -1;
        try {
            optiune = scanner.nextInt();
            scanner.nextLine(); 
        } catch (Exception e) {
            System.out.println("Eroare: Vă rugăm să introduceți un număr valid.");
            return;
        }

        switch (optiune) {
            case 1:
                System.out.print("Introduceți noul nume: ");
                this.nume = scanner.nextLine();
                System.out.println("Numele a fost actualizat la: " + this.nume);
                break;

            case 2:
                System.out.print("Introduceți noua adresă de email: ");
                String nouEmail = scanner.nextLine();
                if (valideazaEmail(nouEmail)) {
                    this.email = nouEmail;
                    System.out.println("Email actualizat cu succes!");
                } 
                break;

            case 3:
                System.out.print("Introduceți noua parolă: ");
                String nouaParola = scanner.nextLine();
                if (valideazaParola(nouaParola)) {
                    this.parola = nouaParola;
                    System.out.println("Parola a fost schimbată cu succes!");
                } 
                break;

            case 0:
                System.out.println("Actualizarea a fost anulată.");
                break;

            default:
                System.out.println("Opțiune invalidă. Încercați din nou.");
                break;
        }
    }

    private boolean verificaCredentiale(String email, String parola) {
        return this.email.equals(email) && this.parola.equals(parola);
    }

    public void recuperareParola(String email) {
        System.out.println("Un email de recuperare a fost trimis la adresa: " + email);
    }

    private boolean valideazaEmail(String email) {
        if (email == null) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,10}$"; 
        
        boolean esteValid = email.matches(emailRegex);
        if (!esteValid) {
            System.out.println("Eroare: Emailul nu este valid"); 
        }
        return esteValid;
        
    }
    
    private boolean valideazaParola(String parola) {
        if (parola == null) {
            return false;
        }
        String parolaRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$";

        boolean esteValida = parola.matches(parolaRegex);
        if (!esteValida) {
            System.out.println("Eroare: Parola trebuie să aibă minim 8 caractere, o majusculă, o cifră și un caracter special.");
        }

        return esteValida;
    }


    public String getNume() {
        return nume;
    }
}
