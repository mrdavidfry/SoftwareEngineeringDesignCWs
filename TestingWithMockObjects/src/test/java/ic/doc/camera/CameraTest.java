package ic.doc.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {

  private final byte[] data = new byte[0];

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  private Sensor sensor = context.mock(Sensor.class);
  private MemoryCard memoryCard = context.mock(MemoryCard.class);
  private Camera camera = new Camera(sensor, memoryCard);

  @Test
  public void switchingTheCameraOnPowersUpTheSensor() {

    context.checking(new Expectations() {{
        exactly(1).of(sensor).powerUp();
    }});

    camera.powerOn();
  }

  @Test
  public void switchingTheCameraOffPowersDownTheSensor() {

    context.checking(new Expectations() {{
      exactly(1).of(sensor).powerDown();
    }});

    camera.powerOff();
  }

  @Test
  public void pressingTheShutterWhenPowerOffDoesNothing() {

    context.checking(new Expectations() {{
      never(memoryCard);
      exactly(1).of(sensor).powerDown();
      never(sensor).powerUp();
      never(sensor).readData();
    }});

    camera.powerOff();
    camera.pressShutter();
  }

  @Test
  public void pressingTheShutterWhenPowerOnCopiesData() {
    context.checking(new Expectations() {{
      exactly(1).of(sensor).powerUp();
      exactly(1).of(sensor).readData();
      will(returnValue(data));
      exactly(1).of(memoryCard).write(data);
    }});

    camera.powerOn();
    camera.pressShutter();
  }

  @Test
  public void whenDataBeingWrittenCameraOffDoesNotPowerDownSensor() {
    context.checking(new Expectations() {{
      exactly(1).of(sensor).powerUp();
      exactly(1).of(sensor).readData();
      will(returnValue(data));
      exactly(1).of(memoryCard).write(data);
      never(sensor).powerDown();
    }});

    camera.powerOn();
    camera.pressShutter();
    camera.powerOff();
  }

  @Test
  public void onceDataWrittenCameraPowersDownSensor() {
    context.checking(new Expectations() {{
      exactly(1).of(sensor).powerUp();
      exactly(1).of(sensor).readData();
      will(returnValue(data));
      exactly(1).of(memoryCard).write(data);
      exactly(1).of(sensor).powerDown();
    }});

    camera.powerOn();
    camera.pressShutter();
    camera.writeComplete();
    camera.powerOff();
  }

}
