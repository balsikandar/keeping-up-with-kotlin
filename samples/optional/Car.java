public class Car {

  private int yearModel;
  private String make;
  private boolean isAutomatic;
  
    public Car(int yearModel, String make) {
        this.yearModel = yearModel;
        this.make = make;
    }

    public int getYearModel() {
        return yearModel;
    }

    public String getMake() {
        return make;
    }

    public int getIsAutomatic() {
        return isAutomatic;
    }

}
