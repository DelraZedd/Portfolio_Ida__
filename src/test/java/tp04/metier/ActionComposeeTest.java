/*
 * Copyright 2024 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class ActionComposeeTest {

    public ActionComposeeTest() {
    }

    @Test
    public void testSomeMethod() {
    }

    @Test
    void testToString() {
        // Créer 2 Action Simple
        ActionSimple bnp, axa;
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        // Créer une action composée
        ActionComposee bqAss;
        bqAss = new ActionComposee("Banque-Assurance");
        // Associer les action simple à l'action composée
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);

        final String expectedToString = "BNP : 0.7, AXA : 0.3";
        final String currentToString = bqAss.toString();

        // assertEquals sur le résultat de toString()
        Assertions.assertEquals(expectedToString, currentToString, "Méthode toString ne fonctionne pas");
    }

    @Test
    void visualiserCours() {
        Jour jInf = new Jour(2023, 1);
        Jour jSup = new Jour(2025, 1);

        Jour jInter = new Jour(2024, 1);

        ActionSimple axa = new ActionSimple("AXA");
        ActionSimple bnp = new ActionSimple("BNP");

        ActionComposee bqAss = new ActionComposee("Banque-Assurance");

        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);

        axa.enrgCours(jInf, 100);
        axa.enrgCours(jInter, 150);
        axa.enrgCours(jSup, 200);

        bnp.enrgCours(jInf, 120);
        bnp.enrgCours(jInter, 170);
        bnp.enrgCours(jSup, 220);

        final String expectedToString = "Action BNP Jour : 2023 1 Cours : 120.0\nAction AXA Jour : 2023 1 Cours : 100.0\nAction BNP Jour : 2024 1 Cours : 170.0\nAction AXA Jour : 2024 1 Cours : 150.0\nAction BNP Jour : 2025 1 Cours : 220.0\nAction AXA Jour : 2025 1 Cours : 200.0\n";
        final String currentToString = bqAss.visualiserCours(jInf, jSup);

        Assertions.assertEquals(expectedToString, currentToString, "Méthode visualiserCours ne fonctionne pas");

    }
}
