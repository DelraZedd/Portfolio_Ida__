package tp04.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActionSimpleTest {

    @Test
    void testVisualiserCoursSimple() {
        Jour jInf = new Jour(2023, 1);
        Jour jSup = new Jour(2025, 1);

        Jour jInter = new Jour(2024, 1);

        ActionSimple axa = new ActionSimple("AXA");

        axa.enrgCours(jInf, 100);
        axa.enrgCours(jInter, 150);
        axa.enrgCours(jSup, 200);

        final String expectedToString = "Jour : 2023 1 Cours : 100.0\nJour : 2024 1 Cours : 150.0\nJour : 2025 1 Cours : 200.0\n";
        final String currentToString = axa.visualiserCours(jInf, jSup);

        Assertions.assertEquals(expectedToString, currentToString, "Méthode visualiserCours ne fonctionne pas");
    }
}
