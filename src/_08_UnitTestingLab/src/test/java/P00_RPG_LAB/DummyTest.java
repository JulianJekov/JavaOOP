package P00_RPG_LAB;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyTest {

    private final static int DUMMY_STARTS_HEALTH = 100;
    private final static int DUMMY_EXPERIENCE = 100;
    private final static int DEAD_DUMMY_HEALTH = 0;
    private final static int ATTACK_POINTS = 20;

    private Dummy dummy;
    private Dummy deadDummy;

    @Before
    public void setup() {
        dummy = new Dummy(DUMMY_STARTS_HEALTH, DUMMY_EXPERIENCE);
        deadDummy = new Dummy(DEAD_DUMMY_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void testDummyLoseHealthWhenAttacked() {
        dummy.takeAttack(ATTACK_POINTS);
        Assert.assertEquals(DUMMY_STARTS_HEALTH - ATTACK_POINTS, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testDeadDummyThrowsExceptionIfAttacked() {
        deadDummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void testDeadDummyGivesXP() {
        int exp = deadDummy.giveExperience();
        Assert.assertEquals(DUMMY_EXPERIENCE, exp);
    }

    @Test(expected = IllegalStateException.class)
    public void testAliveDummyCantGiveXP() {
        dummy.giveExperience();
    }
}