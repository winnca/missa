package practice_3.task_3_1;

import java.util.ArrayList;
import java.util.List;

public class Result {
    public static void main(String[] args) {
        List<Vehicle> list = new ArrayList<>();

        // Toyota Camry
        Car camry = new Car("Toyota", "Camry", 2022, 0.8);
        camry.setAutomatic(true);
        camry.setFuelType("gasoline");
        camry.setDoors(4);

        // Lada Vesta
        Car vesta = new Car("Lada", "Vesta", 2021, 0.5);
        vesta.setAutomatic(false);
        vesta.setFuelType("gasoline");
        vesta.setDoors(4);

        // Kamaz
        Truck kamaz = new Truck("Kamaz", "5320", 2015, 0.6, 10.0);

        // Tesla Model 3
        ElectricCar tesla = new ElectricCar("Tesla", "Model 3", 2023, 500.0, 0.8);
        tesla.setFuelType("electric");
        tesla.setDoors(4);

        list.add(camry);
        list.add(vesta);
        list.add(kamaz);
        list.add(tesla);

        for (Vehicle v : list) {
            System.out.println(v.toString());
            System.out.println("Type: " + v.getType());
            System.out.printf("Fuel consumption for 500 km: %.2f liters/units\n", v.calculateFuelNeeded(500));
            if (v instanceof Car) { // v is car
                ((Car) v).honk();
            }
            if (v instanceof Electric) {
                System.out.printf("Range: %.2f km\n", ((Electric) v).getRangeKm());
            }
            System.out.println();
        }
    }
}


