package tests;

import models.Activitate;
import models.TipActivitate;
import models.Voluntar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AplicareActivitateTest {

    private Voluntar voluntarTest;
    private Activitate activitateTest;

    @BeforeEach
    public void setUp() {
        //initializam obiectele inainte de fiecare test
        voluntarTest = new Voluntar(999, "Mihai George", "mihai@voluntar.ro", "Parola123!", "0711223344", 10);
        activitateTest = new Activitate(1, "Împădurire", "Plantare copaci în zona montană", new Date(), TipActivitate.TEREN, 5);
    }

    @Test
    //scenariu ideal
    public void testAplicareActivitateCuSucces() {

        int locuriDisponibileInitiale = activitateTest.getLocuri_disponibile();
        boolean rezultatInscriere = voluntarTest.aplicaLaActivitate(activitateTest);

        assertTrue(rezultatInscriere, "Voluntarul ar fi trebuit să fie înscris cu succes la activitate.");
        assertEquals(locuriDisponibileInitiale - 1, activitateTest.getLocuri_disponibile(), 
                "Numărul de locuri disponibile trebuie să scadă cu 1 după o înscriere reușită.");
    }


    /* * Coloanele din CsvSource reprezinta:
     * 1. locuriInitiale 
     * 2. dejaInscris 
     * 3. rezultatAsteptat
     * 4. locuriAsteptateDupa 
     */
    @ParameterizedTest
    @CsvSource({
        "5,  false, true,  4",   // Caz 1: situatie normala
        "1,  false, true,  0",   // Caz 2: limita inferioara(ultimul loc disponibil)
        "0,  false, false, 0",   // Caz 3: fortare exceptie 
        "-2, false, false, -2",  // Caz 4: situatie anormala (locuri negative)
        "5,  true,  false, 5",   // Caz 5: are locuri, dar verificam decizia "Ești deja inscris!"
        "0,  true,  false, 0"    // Caz 6: fara locuri și deja inscris
    })
    
    public void testAplicaLaActivitateParametrizat(int locuriInitiale, boolean dejaInscris, boolean rezultatAsteptat, int locuriAsteptateDupa) {
        
        //setam locurile conform setului curent de date
        activitateTest.setLocuri_disponibile(locuriInitiale);

        // daca scenariul cere testarea situației in care voluntarul a aplicat deja
        if (dejaInscris) {
            activitateTest.setLocuri_disponibile(999); // evitam eroarea de "0 locuri" la prima adaugare
            voluntarTest.aplicaLaActivitate(activitateTest); // il adaugam
            activitateTest.setLocuri_disponibile(locuriInitiale); // resetam locurile la valoarea testata
        }

        boolean rezultatReal = voluntarTest.aplicaLaActivitate(activitateTest);

        assertEquals(rezultatAsteptat, rezultatReal, 
                "Eroare la return! Locuri: " + locuriInitiale + " | Deja înscris: " + dejaInscris);
        
        assertEquals(locuriAsteptateDupa, activitateTest.getLocuri_disponibile(), 
                "Numărul de locuri nu a fost calculat corect!");
    }
}