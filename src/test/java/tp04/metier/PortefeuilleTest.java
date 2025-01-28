package tp04.metier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MathH
 */
public class PortefeuilleTest {

    @Test
    public void testAcheterNouvelleAction() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action = new ActionSimple("Action1");

        // Act
        portefeuille.acheter(action, 10);

        // Assert
        assertTrue(portefeuille.mapLignes.containsKey(action), "L'action doit être ajoutée au portefeuille.");
        assertEquals(10, portefeuille.getQuantiteAction(action), "La quantité de l'action doit être 10.");
    }

    @Test
    public void testAcheterActionExistante() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action = new ActionSimple("Action1");
        portefeuille.acheter(action, 10);

        // Act
        portefeuille.acheter(action, 5);

        // Assert
        assertTrue(portefeuille.mapLignes.containsKey(action), "L'action doit être présente dans le portefeuille.");
        assertEquals(15, portefeuille.getQuantiteAction(action), "La quantité totale de l'action doit être 15.");
    }

    @Test
    public void testAcheterQuantiteZero() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action = new ActionSimple("Action1");

        // Act
        portefeuille.acheter(action, 0);

        // Assert
        assertFalse(portefeuille.mapLignes.containsKey(action),
                "Aucune action ne doit être ajoutée pour une quantité de 0.");
    }

    @Test
    public void testAcheterQuantiteInvalide() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action = new ActionSimple("Action1");

        // Act
        portefeuille.acheter(action, -5);

        // Assert
        assertFalse(portefeuille.mapLignes.containsKey(action),
                "Aucune action ne doit être ajoutée pour une quantité de 0.");
    }

    @Test
    public void testVendreActionPartiellement() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action = new ActionSimple("Action1");
        portefeuille.acheter(action, 10);

        // Act
        portefeuille.vendre(action, 5);

        // Assert
        assertTrue(portefeuille.mapLignes.containsKey(action), "L'action doit rester dans le portefeuille.");
        assertEquals(5, portefeuille.getQuantiteAction(action), "La quantité restante de l'action doit être 5.");
    }

    @Test
    public void testVendreActionEntierement() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action = new ActionSimple("Action1");
        portefeuille.acheter(action, 10);

        // Act
        portefeuille.vendre(action, 10);

        // Assert
        assertFalse(portefeuille.mapLignes.containsKey(action), "L'action doit être retirée du portefeuille.");
    }

    @Test
    public void testVendreActionInexistante() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action = new ActionSimple("Action1");

        // Act
        portefeuille.vendre(action, 5);

        // Assert
        assertFalse(portefeuille.mapLignes.containsKey(action),
                "Aucune action ne doit être présente dans le portefeuille.");
    }

    @Test
    public void testValeurPortefeuille() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action1 = new ActionSimple("Action1");
        ActionSimple action2 = new ActionSimple("Action2");

        Jour jour = new Jour(2025, 27);
        action1.enrgCours(jour, 100);
        action2.enrgCours(jour, 200);

        portefeuille.acheter(action1, 2); // Valeur : 2 * 100 = 200
        portefeuille.acheter(action2, 3); // Valeur : 3 * 200 = 600

        // Act
        float valeurTotale = portefeuille.valeur(jour);

        // Assert
        assertEquals(800, valeurTotale, 0.001, "La valeur totale du portefeuille doit être 800.");
    }

    public void testValeurPortefeuille(float resultatAttendu, Portefeuille portefeuille, Jour jourATester) {

        // Exécution
        float result = portefeuille.valeur(jourATester);

        // Assertion
        assertEquals(resultatAttendu, result, "La valeur retournée par la méthode est incorrecte");
    }

    @Test
    void testValeurAvecExemple() {
        // Création de jours pour les tests
        Jour j1 = new Jour(2014, 1);
        Jour j2 = new Jour(2014, 2);

        // Création d'actions simples
        ActionSimple bnp = new ActionSimple("BNP");
        ActionSimple axa = new ActionSimple("AXA");

        // Enregistrement des cours pour chaque action
        axa.enrgCours(j1, 200);
        axa.enrgCours(j2, 250);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);

        // Création de portefeuilles
        Portefeuille p1 = new Portefeuille();
        Portefeuille p2 = new Portefeuille();
        Portefeuille p3 = new Portefeuille();

        // Remplissage des portefeuilles
        p2.acheter(axa, 10); // Portefeuille p2 achète 10 actions AXA
        p3.acheter(axa, 10); // Portefeuille p3 achète 10 actions AXA

        // Tester la valeur pour un portefeuille vide
        testValeurPortefeuille(0.0f, p1, j1);

        // Test de la valeur pour p2 au jour j1
        testValeurPortefeuille(2000.0f, p2, j1);

        // Modification du portefeuille p2
        p2.vendre(axa, 5);

        // Vérification après vente
        testValeurPortefeuille(1000.0f, p2, j1); // Attente : 5 actions AXA à 200 chacune
    }

    public void testVendreAction(int resultatAttendu, Portefeuille portefeuille, Action action) {

        // Exécution
        int result = portefeuille.getQuantiteAction(action);

        // Assertion
        assertEquals(resultatAttendu, result, "La valeur retournée par la méthode est incorrecte");
    }

    @Test
    void testVendreAvecExemple() {
        // Création de jours pour les tests
        Jour j1 = new Jour(2014, 1);

        // Création d'actions simples
        ActionSimple bnp = new ActionSimple("BNP");
        ActionSimple axa = new ActionSimple("AXA");

        // Enregistrement des cours pour chaque action
        axa.enrgCours(j1, 200);
        bnp.enrgCours(j1, 100);

        // Création de portefeuilles
        Portefeuille p1 = new Portefeuille();// vends normal
        Portefeuille p2 = new Portefeuille();// vends 0
        Portefeuille p3 = new Portefeuille();// vends trop
        Portefeuille p4 = new Portefeuille();// vends non existant

        // Remplissage des portefeuilles
        p1.acheter(axa, 10); // Portefeuille p2 achète 10 actions AXA
        p2.acheter(axa, 10); // Portefeuille p2 achète 10 actions AXA
        p3.acheter(axa, 10); // Portefeuille p3 achète 10 actions AXA
        p4.acheter(axa, 10); // Portefeuille p4 achète 10 actions AXA

        p1.vendre(axa, 5);
        testVendreAction(5, p1, axa);
        p2.vendre(axa, 0);
        testVendreAction(10, p2, axa);
        // Cas 3 : Vente d'une quantité supérieure au stock disponible
        Exception exceptionP3 = assertThrows(IllegalArgumentException.class, () -> {
            p3.vendre(axa, 12);
        });
        assertEquals(
                "La quantité d'actions à vendre doit être inférieure ou égale à la quantité de cette action dans le portefeuille",
                exceptionP3.getMessage());
        testVendreAction(10, p3, axa); // La quantité reste inchangée

        // Cas 4 : Vente d'une action non présente dans le portefeuille
        Exception exceptionP4 = assertThrows(IllegalArgumentException.class, () -> {
            p4.vendre(bnp, 5);
        });
        assertEquals("L'action doit être présente dans le portefeuille", exceptionP4.getMessage());
    }
}
