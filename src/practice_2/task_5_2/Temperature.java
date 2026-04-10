package practice_2.task_5_2;

public record Temperature(double value, Unit unit) {

    public enum Unit {
        CELSIUS, FAHRENHEIT, KELVIN
    }

    // Компактный конструктор с проверкой абсолютного нуля
    public Temperature {
        // Переводим в Кельвины для проверки
        double kelvinValue = switch (unit) {
            case CELSIUS -> value + 273.15;
            case FAHRENHEIT -> (value - 32) * 5.0 / 9.0 + 273.15;
            case KELVIN -> value;
        };

        if (kelvinValue < 0) {
            throw new IllegalArgumentException(
                    "Температура не может быть ниже абсолютного нуля (0K)"
            );
        }
    }

    // Метод конвертации в другую единицу измерения
    public Temperature convertTo(Unit targetUnit) {
        if (this.unit == targetUnit) {
            return this;
        }

        // Сначала переводим в Кельвины
        double kelvin = switch (this.unit) {
            case CELSIUS -> this.value + 273.15;
            case FAHRENHEIT -> (this.value - 32) * 5.0 / 9.0 + 273.15;
            case KELVIN -> this.value;
        };

        // Затем из Кельвинов в целевую единицу
        double convertedValue = switch (targetUnit) {
            case CELSIUS -> kelvin - 273.15;
            case FAHRENHEIT -> (kelvin - 273.15) * 9.0 / 5.0 + 32;
            case KELVIN -> kelvin;
        };

        return new Temperature(convertedValue, targetUnit);
    }

    // Переопределённый toString()
    @Override
    public String toString() {
        String symbol = switch (unit) {
            case CELSIUS -> "°C";
            case FAHRENHEIT -> "°F";
            case KELVIN -> "K";
        };
        return String.format("%.2f %s", value, symbol);
    }
}
