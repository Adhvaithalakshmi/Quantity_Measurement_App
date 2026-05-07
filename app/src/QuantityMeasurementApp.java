enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double toFeet(double value) {
        return value * factor;
    }

    public double fromFeet(double value) {
        return value / factor;
    }
}

class QuantityLength {

    double value;
    LengthUnit unit;

    public QuantityLength(double value,
                          LengthUnit unit) {

        this.value = value;
        this.unit = unit;
    }

    public QuantityLength add(QuantityLength other,
                              LengthUnit targetUnit) {

        double first =
                this.unit.toFeet(this.value);

        double second =
                other.unit.toFeet(other.value);

        double total = first + second;

        double result =
                targetUnit.fromFeet(total);

        return new QuantityLength(result,
                targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        QuantityLength other =
                (QuantityLength) obj;

        double first =
                this.unit.toFeet(this.value);

        double second =
                other.unit.toFeet(other.value);

        return Math.abs(first - second)
                < 0.0001;
    }

    @Override
    public String toString() {

        return value + " " + unit;
    }
}

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength feet =
                new QuantityLength(1.0,
                        LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0,
                        LengthUnit.INCHES);

        // Equality
        System.out.println(
                "Equality Result : "
                        + feet.equals(inches)
        );

        // Addition
        QuantityLength result =
                feet.add(inches,
                        LengthUnit.FEET);

        System.out.println(
                "Addition Result : "
                        + result
        );
    }
}