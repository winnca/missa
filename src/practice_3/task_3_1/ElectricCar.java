package practice_3.task_3_1;

public class ElectricCar extends Car implements Electric{
    private double maxRangeKm;
    private double batteryLevel;
    public ElectricCar(String brand, String model, int year, double rangeKm, double batteryLevel) {
        super(brand, model, year);
        this.maxRangeKm=maxRangeKm;
        setBatteryLevel(batteryLevel);
    }

    @Override
    public double getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public double getRangeKm() {
        return maxRangeKm;
    }

    public void setBatteryLevel(double batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 1) this.batteryLevel = batteryLevel;
        else System.out.println("Invalid battery level. Must be between 0 and 1.");
    }

    @Override
    public void charge(double hours) {
        this.batteryLevel += 0.2 * hours;
        if (this.batteryLevel > 1.0) this.batteryLevel = 1.0;
        System.out.println("Charging complete. Current charge: " + (batteryLevel * 100) + "%");
    }

    @Override
    public double getFuelConsumption() {
        return 0;
    }
}
