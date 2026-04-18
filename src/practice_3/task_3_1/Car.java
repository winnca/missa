package practice_3.task_3_1;

public class Car extends Vehicle {
    private String fuelType;
    private int doors;
    private boolean automatic;

    public Car(String brand, String model, int year, double fuelLevel) {
        super(brand, model, year, fuelLevel);
    }

    public Car(String brand, String model, int year) {
        super(brand, model, year);
    }

    public Car(String brand, String model, int year, String fuelType, int doors, boolean automatic) {
        super(brand, model, year);
        setFuelType(fuelType);
        setDoors(doors);
        this.automatic = automatic;
    }

    @Override
    public double getFuelConsumption() {
        if (automatic) {
            return 9.5;
        } else {
            return 8.0;
        }
    }

    public void setFuelType(String fuelType) {
        if ("gasoline".equals(fuelType) || "diesel".equals(fuelType) || "gas".equals(fuelType)) {
            this.fuelType = fuelType;
        } else {
            System.out.println("Invalid fuel type: " + fuelType);
        }
    }

    @Override
    public String getType() {
        return fuelType;
    }

    public void honk() {
        System.out.println("Beep-beep-beep");
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        if (doors > 0) {
            this.doors = doors;
        } else {
            System.out.println("Invalid number of doors");
        }
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }
}
