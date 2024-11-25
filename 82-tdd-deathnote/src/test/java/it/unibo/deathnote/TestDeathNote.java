package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {
    DeathNoteImplementation death;

    @BeforeEach
    public void setUp() {
        death = new DeathNoteImplementation();
    }

    @Test
    public void testNonExistingRules() {
        try {
            assertThrows(IllegalArgumentException.class, () -> death.getRule(0));
            death.getRule(0);
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
            assertDoesNotThrow(() -> Objects.requireNonNull(e.getMessage()));
        }
        try {
            assertThrows(IllegalArgumentException.class, () -> death.getRule(-1));
            death.getRule(-1);
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        assertFalse(e.getMessage().isBlank());
        assertDoesNotThrow(() -> Objects.requireNonNull(e.getMessage()));
        assertThrows(IllegalArgumentException.class, () -> death.getRule(2000));
        }
    }

    @Test
    public void testNoEmptyRules() {
        for (String var : death.RULES) {
            assertFalse(var.isBlank());
            assertDoesNotThrow(() -> Objects.requireNonNull(var));
        }
    }
    
    @Test
    public void testKillHuman() {
        String nameHuman = "adam";
        String nameHuman2 = null;
        assertFalse(death.getDeadPeople().contains(nameHuman));
        death.writeName(nameHuman);
        assertTrue(death.getDeadPeople().contains(nameHuman));
        assertFalse(death.getDeadPeople().contains(nameHuman2));
        assertThrows(NullPointerException.class, () -> death.writeName(nameHuman2));
        assertFalse(death.getDeadPeople().contains(nameHuman2));
    }

    @Test
    public void testCauseDeath() throws InterruptedException {
        assertThrows(IllegalStateException.class,() -> death.writeDeathCause("test"));
        death.writeName("testHuman");
        assertEquals(death.getDeathCause("testHuman"), "heart attack");
        death.writeName("testHuman2");
        assertEquals(true, death.writeDeathCause("karting accident"));
        assertEquals(death.getDeathCause("testHuman2"), "karting accident");
        Thread.sleep(100);
        death.writeDeathCause("test");
        assertFalse(death.getDeathCause("testHuman2").equals("test"));
    }

    @Test
    public void testDetailsDeath() throws InterruptedException {
        assertThrows(IllegalStateException.class, () -> death.writeDetails("test"));
        death.writeName("testHuman");
        assertTrue(death.getDeathDetails("testHuman").isEmpty());
        assertEquals(true, death.writeDetails("ran for too long"));
        assertEquals(death.getDeathDetails("testHuman"), "ran for too long");
        death.writeName("testHuman2");
        Thread.sleep(6100);
        death.writeDetails("test");
        assertFalse(death.getDeathDetails("testHuman2").equals("test"));
    }
}