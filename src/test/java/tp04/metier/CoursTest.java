package tp04.metier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MathH
 */
class CoursTest {

    @Test
    void testGetPrix() {
        final Cours cours1 = new Cours(new Jour(2025, 1), 45);
        assertEquals(45, cours1.getValeur());
    }

    @Test
    public void testAcheterNouvelleAction() {

        // Arrange
        ActionSimple bnp = new ActionSimple("BNP");
        Jour j1, j2, j3, j4, j5;

        // init des objets métiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 2);
        j3 = new Jour(2014, 3);
        j4 = new Jour(2014, 4);
        j5 = new Jour(2014, 5);

        bnp.enrgCours(j1, 200);
        bnp.enrgCours(j2, 250);
        bnp.enrgCours(j3, 421);
        bnp.enrgCours(j4, 312);
        bnp.enrgCours(j5, 201);

        // Act & Assert
        assertEquals(-49, bnp.simulation(j2, j5, 1), "");
        assertEquals(221, bnp.simulation(j1, j3, 1), "");

        assertEquals(-333, bnp.simulation(j4, j5, 3), "");
        assertEquals(855, bnp.simulation(j2, j3, 5), "");

        assertEquals(0, bnp.simulation(j3, j4, 0), "");
        assertEquals(0, bnp.simulation(j4, j5, -3), "");

        // Utilisation correcte de assertThrows
        assertThrows(IllegalArgumentException.class, () -> bnp.simulation(j5, j2, 1), 
            "Une exception aurait dû être levée pour une période invalide.");
        assertThrows(IllegalArgumentException.class, () -> bnp.simulation(j2, j2, 1), 
            "Une exception aurait dû être levée pour une période invalide.");
    }
}
