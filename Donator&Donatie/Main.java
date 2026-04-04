public class Main {
    public static void main(String[] args) {

        Donator donator1 = new Donator(101, "Ion Pop", "ionpop@email.com", "parola123!", TipDonator.PERSOANA_FIZICA);
        
        donator1.realizeazaDonatie(250.50, "PayPal");
        donator1.realizeazaDonatie(100.00, "Revolut");
        donator1.realizeazaDonatie(50.00);
        donator1.realizeazaDonatie(0,"Cash");
    }
}