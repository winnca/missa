package practice_3.task_3_1;

public abstract class Vehicle {
    private String brand;
    private String model;
    private int year;
    private double fueLevel;

    public Vehicle(String brand, String model, int year, double fueLevel) {
        this.brand=brand;
        this.model=model;
        this.year=year;
        setFueLevel(fueLevel);
    }
    public Vehicle(String brand, String model, int year) {
        this.brand=brand;
        this.model=model;
        this.year=year;
    }
    public String getBrand() {
        return brand;
    }

    public String getModel() { return model; }

    public int getYear() { return year; }
    public double getFueLevel() { return fueLevel;}
    public void setFueLevel(double fueLevel) {
        if (fueLevel >= 0 && fueLevel <= 1) this.fueLevel = fueLevel;
        else System.out.println("Invalid fuel level. Must be between 0 and 1.");
    }
    public double calculateFuelNeeded(double distanceKm){
        return getFuelConsumption()*(distanceKm/100);
    }
    public void canTravel(double distanceKm, double tankCapacityLiters){
        if (distanceKm*getFuelConsumption()<=tankCapacityLiters) System.out.println("Can travel");
        else System.out.println("Can't travel");
    }
    public abstract double getFuelConsumption();
    public abstract String getType();

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", fueLevel=" + fueLevel +
                '}';
    }
}
