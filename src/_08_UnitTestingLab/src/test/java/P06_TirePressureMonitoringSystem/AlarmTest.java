package P06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class AlarmTest {
    private static final double LOW_PRESSURE = 14.0;
    private static final double HIGH_PRESSURE = 25.0;
    private static final double NORMAL_PRESSURE = 20.0;
    private Sensor sensor;
    private Alarm alarm;

    @Before
    public void prepare() {
        sensor = Mockito.mock(Sensor.class);
        alarm = new Alarm(sensor);
    }

    @Test
    public void testAlarmShouldBeOnBecauseLowPressure() {
        when(sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmShouldBeOnBecauseHighPressure() {
        when(sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmShouldBeOff() {
        when(sensor.popNextPressurePsiValue()).thenReturn(NORMAL_PRESSURE);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }

}