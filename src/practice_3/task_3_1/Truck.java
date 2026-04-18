package practice_3.task_3_1;

public class Truck extends Vehicle{
    private double cargoCapacityTons;

    public Truck(String brand, String model, int year, double fueLevel, double cargoCapacityTons){
        super(brand, model, year, fueLevel);
        this.cargoCapacityTons=cargoCapacityTons;
    }

    @Override
    public double getFuelConsumption() {
        return 20 + cargoCapacityTons * 3;
    }

    @Override
    public String getType() {
        return "Truck";
    }
    public double getCargoCapacityTons() {
        return cargoCapacityTons;
    }

    public void setCargoCapacityTons(float cargoCapacityTons) {
        this.cargoCapacityTons = cargoCapacityTons;
    }
}
