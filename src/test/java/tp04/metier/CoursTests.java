package tp04.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoursTest {

    @Test
    void testGetValeur() {
        final Cours cours1 = new Cours(new Jour(2025, 1), 45);
        Assertions.assertEquals(45, cours1.getValeur());
    }
}