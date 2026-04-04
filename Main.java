
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. Inițializare Bază de Date simulată
        List<Utilizator> totiUtilizatorii = new ArrayList<>();
        List<Donator> listaDonatori = new ArrayList<>();

        // 2. Creare conturi inițiale
        Administrator admin = new Administrator(1, "Cosmulete Cosmin", "Cosmin@admin.ro", "Admin123!");
        Donator donator1 = new Donator(101, "Ion Pop", "ion@test.ro", "Ion123!", TipDonator.PERSOANA_FIZICA);
        
        totiUtilizatorii.add(admin);
        totiUtilizatorii.add(donator1);
        listaDonatori.add(donator1);

        // 3. Setăm utilizatorul logat implicit
        Utilizator userLogat = admin; 

        boolean running = true;
        while (running) {
            System.out.println("\n=================================================");
            System.out.println("   SISTEM ONG - Logat ca: " + userLogat.getNume() + " (Rol: " + userLogat.getClass().getSimpleName() + ")");
            System.out.println("=================================================");
            System.out.println("1. Autentificare (Schimbă utilizatorul curent)");
            System.out.println("2. Creare Cont Nou (Înregistrare)");
            System.out.println("3. Actualizare Profil (Accesibil oricui)");
            System.out.println("4. Realizează Donație (DOAR Donator)");
            System.out.println("5. Gestiune Utilizatori (DOAR Admin)");
            System.out.println("6. Vizualizare Raport Donații (DOAR Admin)");
            System.out.println("7. Gestiune Activități (DOAR Admin)");
          //adaugare optiuni pentru voluntari+ generare rapoarte(doar Amin) - Adrian
            System.out.println("0. Ieșire");
            System.out.print("Alegeți o opțiune: ");
            
            int opt = -1;
            try {
                opt = sc.nextInt();
                sc.nextLine(); 
            } catch (Exception e) {
                System.out.println("Eroare: Vă rugăm să introduceți un număr valid!");
                sc.nextLine(); 
                continue;
            }

            switch (opt) {
            case 1:
                System.out.println("\n--- MENIU AUTENTIFICARE ---");
                System.out.println("1. Conectare cont existent");
                System.out.println("2. Mi-am uitat parola (Recuperare)");
                System.out.print("Alegere: ");
                
                int authOpt = -1;
                try {
                    authOpt = sc.nextInt();
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("Opțiune invalidă.");
                    sc.nextLine();
                    break;
                }

                if (authOpt == 1) {
                    System.out.print("Introduceți email: ");
                    String emailLogin = sc.nextLine();
                    System.out.print("Introduceți parola: ");
                    String parolaLogin = sc.nextLine();
                    
                    boolean logareReusita = false;
                    for (Utilizator u : totiUtilizatorii) {
                        if (u.autentificare(emailLogin, parolaLogin)) {
                            userLogat = u;
                            System.out.println(">>> Autentificare reușită! Bine ai venit, " + u.getNume() + " <<<");
                            logareReusita = true;
                            break;
                        }
                    }
                    if (!logareReusita) {
                        System.out.println(">>> EROARE: Email sau parolă incorectă! <<<");
                    }
                } 
                else if (authOpt == 2) {
                    // Implementarea relației <<extend>>: Recuperare parola
                    System.out.print("Introduceți email-ul asociat contului: ");
                    String emailRecup = sc.nextLine();
                    
                    boolean contGasit = false;
                    for (Utilizator u : totiUtilizatorii) {
                        // Folosim u.email (care este protected)
                        if (u.email.equals(emailRecup)) {
                            u.recuperareParola(emailRecup);
                            contGasit = true;
                            break;
                        }
                    }
                    if (!contGasit) {
                        System.out.println("EROARE: Nu există niciun cont asociat cu acest email în sistem.");
                    }
                } else {
                    System.out.println("Opțiune inexistentă.");
                }
                break;

                case 2:
                    System.out.println("\n--- CREARE CONT NOU ---");
                    System.out.println("Selectați tipul contului: 1. Donator | 2. Administrator");
                    System.out.print("Alegere: ");
                    int tipCont = -1;
                    try {
                        tipCont = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("Eroare la alegerea tipului de cont.");
                        sc.nextLine();
                        break;
                    }

                    System.out.print("Nume: ");
                    String numeC = sc.nextLine();
                    System.out.print("Email: ");
                    String emailC = sc.nextLine();
                    System.out.print("Parola: ");
                    String parolaC = sc.nextLine();

                    int idNou = totiUtilizatorii.size() + 100;
                    Utilizator userNou = null;

                    if (tipCont == 1) {
                        userNou = new Donator(idNou, "", "", "", TipDonator.PERSOANA_FIZICA);
                    } else if (tipCont == 2) {
                        userNou = new Administrator(idNou, "", "", "");
                    } else {
                        System.out.println("Tip cont invalid. Anulare.");
                        break;
                    }

                    System.out.println("\nSe procesează datele...");
                    userNou.creareCont(numeC, emailC, parolaC);

                    if (!userNou.email.isEmpty()) {
                        totiUtilizatorii.add(userNou);
                        if (userNou instanceof Donator) {
                            listaDonatori.add((Donator) userNou);
                        }
                        System.out.println(">>> Contul a fost salvat în baza de date! <<<");
                    }
                    break;

                case 3:
                    userLogat.actualizareProfil();
                    break;

                case 4:
                    if (userLogat instanceof Donator) {
                        System.out.print("Introduceți suma de donat (RON): ");
                        try {
                            double suma = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Introduceți metoda de plată (Card/Transfer/PayPal): ");
                            String metodaPlata = sc.nextLine();
                            ((Donator) userLogat).realizeazaDonatie(suma, metodaPlata);
                        } catch (Exception e) {
                            System.out.println("Eroare la introducerea datelor.");
                            sc.nextLine();
                        }
                    } else {
                        System.out.println(">>> EROARE: Administratorii nu pot dona. Autentificați-vă ca Donator! <<<");
                    }
                    break;

                case 5:
                    if (userLogat instanceof Administrator) {
                        ((Administrator) userLogat).gestionareUtilizatori(totiUtilizatorii);
                    } else {
                        System.out.println(">>> ACCES REFUZAT: Nu aveți drepturi de Administrator! <<<");
                    }
                    break;

                case 6:
                    if (userLogat instanceof Administrator) {
                        ((Administrator) userLogat).vizualizareDonatii(listaDonatori);
                    } else {
                        System.out.println(">>> ACCES REFUZAT: Doar Administratorul poate vedea raportul! <<<");
                    }
                    break;

                case 7:
                    // --- SUB-MENIU GESTIUNE ACTIVITĂȚI ---
                    if (userLogat instanceof Administrator) {
                        Administrator adminCurent = (Administrator) userLogat;
                        boolean meniuActivitati = true;
                        
                        while (meniuActivitati) {
                            System.out.println("\n--- GESTIUNE ACTIVITĂȚI ONG ---");
                            System.out.println("1. Afișare toate activitățile");
                            System.out.println("2. Creare activitate nouă");
                            System.out.println("3. Modificare activitate");
                            System.out.println("4. Ștergere activitate");
                            System.out.println("0. Înapoi la meniul principal");
                            System.out.print("Opțiune: ");
                            
                            int optAct = -1;
                            try {
                                optAct = sc.nextInt();
                                sc.nextLine();
                            } catch (Exception e) {
                                System.out.println("Opțiune invalidă.");
                                sc.nextLine();
                                continue;
                            }
                            
                            switch (optAct) {
                                case 1:
                                    adminCurent.afisareActivitati();
                                    break;
                                    
                                case 2:
                                    System.out.println("\n-- Adăugare Activitate --");
                                    try {
                                        System.out.print("ID Activitate (nr. întreg): ");
                                        int idAct = sc.nextInt(); sc.nextLine();
                                        System.out.print("Titlu: ");
                                        String titluAct = sc.nextLine();
                                        System.out.print("Descriere scurtă: ");
                                        String descAct = sc.nextLine();
                                        System.out.print("Locuri disponibile: ");
                                        int locuriAct = sc.nextInt(); sc.nextLine();
                                        
                                        // Folosim date implicite pentru data de început (azi) și tipul activității pentru a simplifica consola
                                        Activitate nouaAct = new Activitate(idAct, titluAct, descAct, new Date(), TipActivitate.TEREN, locuriAct);
                                        adminCurent.creareActivitate(nouaAct);
                                    } catch (Exception e) {
                                        System.out.println("Eroare la introducerea datelor. ID-ul și locurile trebuie să fie numere.");
                                        sc.nextLine();
                                    }
                                    break;
                                    
                                case 3:
                                    System.out.println("\n-- Modificare Activitate --");
                                    try {
                                        System.out.print("Introduceți ID-ul activității de modificat: ");
                                        int idMod = sc.nextInt(); sc.nextLine();
                                        System.out.print("Introduceți noul titlu: ");
                                        String titluMod = sc.nextLine();
                                        System.out.print("Introduceți noul număr de locuri disponibile: ");
                                        int locuriMod = sc.nextInt(); sc.nextLine();
                                        
                                        adminCurent.modificareActivitate(idMod, titluMod, locuriMod);
                                    } catch (Exception e) {
                                        System.out.println("Eroare la introducerea datelor.");
                                        sc.nextLine();
                                    }
                                    break;
                                    
                                case 4:
                                    System.out.print("\nIntroduceți ID-ul activității pentru ștergere: ");
                                    try {
                                        int idSters = sc.nextInt(); sc.nextLine();
                                        adminCurent.stergereActivitate(idSters);
                                    } catch (Exception e) {
                                        System.out.println("ID invalid.");
                                        sc.nextLine();
                                    }
                                    break;
                                    
                                case 0:
                                    meniuActivitati = false;
                                    break;
                                    
                                default:
                                    System.out.println("Opțiune inexistentă.");
                            }
                        }
                    } else {
                        System.out.println(">>> ACCES REFUZAT: Doar Administratorul are acces la gestiunea activităților! <<<");
                    }
                    break;

                case 0:
                    System.out.println("Închidere aplicație... O zi bună!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Opțiune inexistentă. Încercați din nou.");
            }
        }
        sc.close();
    }
}

