package P00_RPG_LAB;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class HeroTest {

    private static final int TARGET_EXP = 10;

    @Test
    public void testHeroGainsXPWhenTargetDies(){

    Weapon weaponMock = Mockito.mock(Weapon.class);
    Target targetMock = Mockito.mock(Target.class);

    Mockito.when(targetMock.isDead()).thenReturn(true);
    Mockito.when(targetMock.giveExperience()).thenReturn(TARGET_EXP);

    Hero hero = new Hero("Pesho", weaponMock);

    hero.attack(targetMock);

    Assert.assertEquals(TARGET_EXP, hero.getExperience());
    }

    @Test
    public void testHeroGetWeapon(){
        Weapon weapon = new Axe(10,10);
        Hero hero = new Hero("Pesho", weapon);

        Weapon getWeapon = hero.getWeapon();

        Assert.assertEquals(getWeapon, hero.getWeapon());

    }
}