package tp04.metier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertFalse(portefeuille.mapLignes.containsKey(action), "Aucune action ne doit être ajoutée pour une quantité de 0.");
    }


    @Test
    public void testAcheterQuantiteInvalide() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action = new ActionSimple("Action1");

        // Act
        portefeuille.acheter(action, -5);

        // Assert
        assertFalse(portefeuille.mapLignes.containsKey(action), "Aucune action ne doit être ajoutée pour une quantité de 0.");
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
        assertFalse(portefeuille.mapLignes.containsKey(action), "Aucune action ne doit être présente dans le portefeuille.");
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
}
