package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import models.Administrator;
import models.Activitate;
import models.TipActivitate;

public class CreareActivitateTest {

    private Administrator admin;

    @BeforeEach
    public void setUp() {
        admin = new Administrator(1, "Admin Cosmin", "admin@ong.ro", "parola123");
    }
    
    @Test
    public void testCreareActivitateSucces() {
    		// Scenariul ideal: date valide
        Activitate a = new Activitate(100, "Ecologizare", "Descriere", new Date(), TipActivitate.TEREN, 10);
        admin.creareActivitate(a);
        
        assertEquals(1, admin.getActivitati().size(), "Activitatea trebuia sa fie adaugata.");
    }

    @Test
    public void testeValidareCreareActivitate() {
        
        // 1. Locuri 0
        Activitate a1 = new Activitate(101, "Activitate Locuri Zero", "Descriere", new Date(), TipActivitate.LOGISTICA, 0);
        admin.creareActivitate(a1);
        assertEquals(0, admin.getActivitati().size(), "Nu trebuia sa adauge activitatea cu 0 locuri.");

        // 2. Locuri negative
        Activitate a2 = new Activitate(102, "Activitate Negativa", "Descriere", new Date(), TipActivitate.EDUCATIONAL, -5);
        admin.creareActivitate(a2);
        assertEquals(0, admin.getActivitati().size(), "Nu trebuia sa adauge activitatea cu locuri negative.");

        // 3. Verificare unicitate ID 
        Activitate a3 = new Activitate(103, "Titlu Unic 1", "Descriere", new Date(), TipActivitate.TEREN, 5);
        admin.creareActivitate(a3);
        
        Activitate a3_duplicatID = new Activitate(103, "Alt Titlu", "Descriere", new Date(), TipActivitate.TEREN, 5);
        admin.creareActivitate(a3_duplicatID);
        
        assertEquals(1, admin.getActivitati().size(), "Nu trebuia sa adauge doua activitati cu acelasi ID.");

        // 4. Verificare unicitate Titlu 
        Activitate a4_duplicatTitlu = new Activitate(104, "Titlu Unic 1", "Descriere", new Date(), TipActivitate.TEREN, 5);
        admin.creareActivitate(a4_duplicatTitlu);
        
        assertEquals(1, admin.getActivitati().size(), "Nu trebuia sa adauge doua activitati cu acelasi titlu.");

        // 5.  Obiect null
        admin.creareActivitate(null);
        assertEquals(1, admin.getActivitati().size(), "Lista trebuia sa ramana neschimbata dupa adaugarea unui obiect null.");
    }

    @Test
    public void testAdaugareMultiplaSiBucla() {

        for (int i = 0; i < 5; i++) {
            admin.creareActivitate(new Activitate(200 + i, "Activitate " + i, "Descriere", new Date(), TipActivitate.FUNDRAISING, 10));
        }
        assertEquals(5, admin.getActivitati().size(), "Ar fi trebuit sa avem 5 activitati in lista.");
    }
}