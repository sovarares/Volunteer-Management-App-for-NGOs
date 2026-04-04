public abstract class Utilizator {
    // clasa pt test
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

    public String getNume() {
        return nume;
    }
}