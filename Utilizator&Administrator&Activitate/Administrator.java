import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrator extends Utilizator {

    private List<Activitate> activitati;
    private List<Raport> rapoarte;

    
    public Administrator(int id_utilizator, String nume, String email, String parola) {
        super(id_utilizator, nume, email, parola);
        this.activitati = new ArrayList<>();
        this.rapoarte = new ArrayList<>();
    }


    public void gestionareUtilizatori(List<Utilizator> listaSistem) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true; 

        while (running) {
            System.out.println("\n--- Panou Gestiune Utilizatori ---");
            System.out.println("1. Afișare toți utilizatorii");
            System.out.println("2. Ștergere utilizator după ID");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");

            int optiune = -1;
            try {
                optiune = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Eroare: Format invalid! Introduceți un număr.");
                scanner.nextLine(); 
                continue; 
            }

            switch (optiune) {
                case 1:
                    System.out.println("\nLista Utilizatori:");
                    if (listaSistem.isEmpty()) {
                        System.out.println("Nu există utilizatori în sistem.");
                    } else {
                        for (Utilizator u : listaSistem) {
                            System.out.println("ID: " + u.id_utilizator + " | Nume: " + u.nume + " | Email: " + u.email);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Introduceți ID-ul utilizatorului pentru ștergere: ");
                    try {
                        int idCautat = scanner.nextInt();
                        scanner.nextLine();
                        boolean gasit = listaSistem.removeIf(u -> u.id_utilizator == idCautat);
                        
                        if (gasit) {
                            System.out.println("Utilizatorul cu ID " + idCautat + " a fost eliminat.");
                        } else {
                            System.out.println("Utilizatorul nu a fost găsit.");
                        }
                    } catch (Exception e) {
                        System.out.println("Eroare: ID-ul trebuie să fie un număr.");
                        scanner.nextLine();
                    }
                    break;

                case 0:
                    running = false; 
                    break;

                default:
                    System.out.println("Opțiune inexistentă.");
            }
        }
        System.out.println("Ați părăsit panoul de gestiune."); 
    }

    public void gestionareRapoarte() {
        //DE MODIFICAT IN FUNCTIE DE CLASA RAPOARTE - ADRIAN
    	Scanner scanner = new Scanner(System.in);
        boolean running = true; 
        
        while (running)
        {
        	System.out.println("\n--- Panou Gestiune Rapoarte ---");
            System.out.println("1. Afișare toate rapoatele");
            System.out.println("2. Ștergere raport după ID");
            System.out.println("3. Creare raport nou");
            System.out.println("4. Afisare raport detaliat dupa ID");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");
            
            int optiune = -1;
            try {
                optiune = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Eroare: Format invalid! Introduceți un număr.");
                scanner.nextLine(); 
                continue; 
            }
            
            switch (optiune)
            {
            case 1:
            	
            	System.out.println("\nLista Rapoarte:");
                if (rapoarte.isEmpty()) {
                    System.out.println("Nu există rapoarte în sistem.");
                } else {
                    for (Raport r : rapoarte) {
                        System.out.println("ID: " + r.getId_raport() + " | Tip: " + r.getTip_raport() + " | Data: " + r.getData_generare());
                    }
                }
            	
            	break;
            	
            case 2:
            	
            	System.out.print("Introduceți ID-ul raportului pentru ștergere: ");
                try {
                    int idCautat = scanner.nextInt();
                    scanner.nextLine();
                    boolean gasit = rapoarte.removeIf(r -> r.getId_raport() == idCautat);
                    
                    if (gasit) {
                        System.out.println("Raportul cu ID " + idCautat + " a fost eliminat.");
                    } else {
                        System.out.println("Raportul nu a fost găsit.");
                    }
                } catch (Exception e) {
                    System.out.println("Eroare: ID-ul trebuie să fie un număr.");
                    scanner.nextLine();
                }
            	
            	break;
            	
            case 3:
            	
            	System.out.print("Introduceți ID-ul pentru noul raport: ");
            	
            	try {
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    boolean gasit = rapoarte.removeIf(r -> r.getId_raport() == id);
                    
                    if (gasit) {
                        System.out.println("Raportul cu ID " + id + " exista deja.");
                    }
                    else
                    {
                    	System.out.print("Introduceți tipul de raport: ");
                    	String tip = scanner.nextLine();
                    	
                    	if (tip.isBlank() || tip.isEmpty()) break;
                    	
                    	Raport newRaport = new Raport(id, tip, this);
                    	
                    	rapoarte.add(newRaport);
                    }
                    
                } catch (Exception e) {
                    System.out.println("Eroare: ID-ul trebuie să fie un număr.");
                    scanner.nextLine();
                }
            	
            	break;
            	
            case 4:
            	
            	System.out.println();
            	System.out.print("Introduceți ID-ul raportului pentru afisare detaliata: ");
                try {
                    int idCautat = scanner.nextInt();
                    scanner.nextLine();
                    boolean gasit = rapoarte.removeIf(r -> r.getId_raport() == idCautat);
                    
                    if (gasit) {
                        
                    	rapoarte.forEach(r -> {
                    		if (r.getId_raport() == idCautat)
                    		{
                    			r.afisareDetaliata();
                    		}
                    	});
                    } else {
                        System.out.println("Raportul nu a fost găsit.");
                    }
                } catch (Exception e) {
                    System.out.println("Eroare: ID-ul trebuie să fie un număr.");
                    scanner.nextLine();
                }
            	
            	break;
            	
            case 0:
                running = false; 
                break;

            default:
                System.out.println("Opțiune inexistentă.");
            }
        }
    }

    public void vizualizareDonatii(List<Donator> listaDonatori) {
        System.out.println("\n========= RAPORT DONAȚII ONG =========");
        
        if (listaDonatori == null || listaDonatori.isEmpty()) {
            System.out.println("Nu există donatori înregistrați în sistem.");
            return;
        }

        boolean sauGasitDonatii = false;

        for (Donator d : listaDonatori) {
            List<Donatie> istoric = d.getIstoricDonatii();
            
            if (!istoric.isEmpty()) {
                sauGasitDonatii = true;
                System.out.println("Donator: " + d.getNume() + " (" + d.email + ")");
                
                for (Donatie don : istoric) {
                    System.out.println("  [ID: " + don.getId_donatie() + "] " +
                                       "Suma: " + don.getSuma() + " RON | " +
                                       "Metoda: " + don.getMetoda_plata() + " | " +
                                       "Data: " + don.getData_donatie());
                }
                System.out.println("--------------------------------------");
            }
        }

        if (!sauGasitDonatii) {
            System.out.println("Nu s-a realizat nicio donație până în acest moment.");
        }
        System.out.println("======================================\n");
    }


    public void creareActivitate(Activitate a) {
        if (a != null) {
            activitati.add(a);
            System.out.println("Administratorul " + this.nume + " a creat activitatea: " + a.getTitlu());
        }
    }

    public void modificareActivitate(int id, String nouTitlu, int noiLocuri) {
        for (Activitate a : activitati) {
            if (a.getId_activitate() == id) {
                a.setTitlu(nouTitlu);
                a.setLocuri_disponibile(noiLocuri);
                System.out.println("Activitatea " + id + " a fost modificată succes.");
                return;
            }
        }
        System.out.println("Eroare: Activitatea cu ID " + id + " nu a fost găsită.");
    }

    public void stergereActivitate(int id) {
        boolean eliminat = activitati.removeIf(a -> a.getId_activitate() == id);
        if (eliminat) {
            System.out.println("Activitatea cu ID " + id + " a fost eliminată.");
        } else {
            System.out.println("Eroare: Nu s-a putut șterge activitatea.");
        }
    }
    
    public void afisareActivitati() {
        System.out.println("\n--- LISTA ACTIVITĂȚI ONG ---");
        for (Activitate a : activitati) {
            System.out.println(a.afisare());
        }
    }
    
    public List<Raport> getRapoarte()
    {
    	return rapoarte;
    }
    
    public List<Activitate> getActivitati()
    {
    	return activitati;
    }
}
