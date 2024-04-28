package P00_RPG_LAB;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AxeTest {
    private static final int AXE_ATTACK_POWER = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int AXE_WITHOUT_DURABILITY = 0;
    private final static int DUMMY_STARTS_HEALTH = 100;
    private final static int DUMMY_EXPERIENCE = 100;

    private Dummy dummy;
    private Axe axe;
    private Axe brokenAxe;

    @Before
    public void setup(){
        dummy = new Dummy(DUMMY_STARTS_HEALTH, DUMMY_EXPERIENCE);
        axe = new Axe(AXE_ATTACK_POWER, AXE_DURABILITY);
        brokenAxe = new Axe(AXE_ATTACK_POWER, AXE_WITHOUT_DURABILITY);
    }

    @Test
    public void testAxeLoseDurabilityAfterAttack() {
        axe.attack(dummy);
        Assert.assertEquals(AXE_ATTACK_POWER - Axe.DURABILITY_LOSS, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackWithBrokenAxeShouldThrowException() {
        brokenAxe.attack(dummy);
    }
}