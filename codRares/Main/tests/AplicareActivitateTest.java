package tests;

import models.Activitate;
import models.TipActivitate;
import models.Voluntar;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class AplicareActivitateTest {

	//verifica scenariul ideal
    @Test
    public void testAplicareActivitateCuSucces() {
        Voluntar voluntar = new Voluntar(999, "Mihai George", "mihai@voluntar.ro", "Parola123!", "0711223344", 10);
        Activitate activitate = new Activitate(1, "Impadurire", "Plantare copaci", new Date(), TipActivitate.TEREN, 5);

        int locuriDisponibileInitiale = activitate.getLocuri_disponibile();
        boolean rezultatInscriere = voluntar.aplicaLaActivitate(activitate);

        assertTrue(rezultatInscriere, "Voluntarul ar fi trebuit să fie inscris cu succes la activitate.");
        assertEquals(locuriDisponibileInitiale - 1, activitate.getLocuri_disponibile(), 
                "Numarul de locuri disponibile trebuie să scada cu 1.");
    }

    @Test
    public void testeAplicaLaActivitate() {
        
        // 1: Situație normala (mijlocul intervalului, 5 locuri)
        Activitate a1 = new Activitate(101, "Activitate 1", "Descriere activitate 1", new Date(), TipActivitate.TEREN, 5);
        Voluntar v1 = new Voluntar(1, "V1", "v1@test.ro", "Pass1!", "0710547180", 11);
        
        assertTrue(v1.aplicaLaActivitate(a1)); //a reusit voluntarul sa se inscrie la activitate?
        assertEquals(4, a1.getLocuri_disponibile()); //s-a scazut locul dupa ce s-a inscris la activitate?


        // 2: Limita inferioara (1 singur loc disponibil)
        Activitate a2 = new Activitate(102, "Activitate 2", "Descriere activitate 2", new Date(), TipActivitate.TEREN, 1);
        Voluntar v2 = new Voluntar(2, "V2", "v2@test.ro", "Pass2!", "0725990887", 12);
        
        assertTrue(v2.aplicaLaActivitate(a2));
        assertEquals(0, a2.getLocuri_disponibile());


        // 3: Forțare exceptie - limita 0 locuri
        Activitate a3 = new Activitate(103, "Activitate 3", "Descriere activitate 3", new Date(), TipActivitate.TEREN, 0);
        Voluntar v3 = new Voluntar(3, "V3", "v3@test.ro", "Pass3!", "0739833744", 13);
        
        assertFalse(v3.aplicaLaActivitate(a3));
        assertEquals(0, a3.getLocuri_disponibile());


        // 4: Situație anormala (locuri negative) ---
        Activitate a4 = new Activitate(104, "Activitate 4", "Descriere activitate 4", new Date(), TipActivitate.TEREN, -2);
        Voluntar v4 = new Voluntar(4, "V4", "v4@test.ro", "Pass4!", "0741177441", 14);
        
        assertFalse(v4.aplicaLaActivitate(a4));
        assertEquals(-2, a4.getLocuri_disponibile());


        // 5: Verificare ramura decizionala (Utilizator deja inscris)
        Activitate a5 = new Activitate(105, "Activitate 5", "Descriere activitate 5", new Date(), TipActivitate.TEREN, 5);
        Voluntar v5 = new Voluntar(5, "V5", "v5@test.ro", "Pass5!", "0750040021", 15);
        
        v5.aplicaLaActivitate(a5); // il înscriem prima data cu succes (locurile devin 4)
        
        assertFalse(v5.aplicaLaActivitate(a5)); // incercăm să il inscriem din nou
        assertEquals(4, a5.getLocuri_disponibile()); // verificam că a fost blocat și locurile au ramas 4
    }
}