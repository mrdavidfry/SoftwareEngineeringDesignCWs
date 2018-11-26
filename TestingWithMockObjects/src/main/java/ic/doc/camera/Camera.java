package ic.doc.camera;

public class Camera implements WriteListener {

  private final Sensor sensor;
  private boolean isOn;
  private boolean isWritingData;
  private MemoryCard memoryCard;

  public Camera(Sensor sensor, MemoryCard memoryCard) {
    this.sensor = sensor;
    this.memoryCard = memoryCard;
    this.isOn = false;
    this.isWritingData = false;
  }

  public void pressShutter() {
    if (isOn) {
      byte[] data = sensor.readData();
      isWritingData = true;
      memoryCard.write(data);
    }
  }

  public void powerOn() {
    sensor.powerUp();
    isOn = true;
  }

  public void powerOff() {
    if (!isWritingData) {
      sensor.powerDown();
    }
  }

  @Override
  public void writeComplete() {
    isWritingData = false;
  }
}

