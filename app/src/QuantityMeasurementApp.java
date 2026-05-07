interface IMeasurable {

    double getConversionFactor();

    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }

    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }

    String getUnitName();
}

// ---------------- LENGTH ----------------
enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12.0);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return name();
    }
}

// ---------------- WEIGHT ----------------
enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return name();
    }
}

// ---------------- VOLUME ----------------
enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return name();
    }
}

// ---------------- QUANTITY ----------------
class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 0.0001;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // EQUALITY
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity<?>))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    // CONVERSION
    public Quantity<U> convertTo(U targetUnit) {

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ADDITION
    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        double resultBase =
                unit.convertToBaseUnit(value)
                        + other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    // SUBTRACTION
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        double resultBase =
                unit.convertToBaseUnit(value)
                        - other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    // DIVISION
    public double divide(Quantity<U> other) {

        double divisor =
                other.unit.convertToBaseUnit(other.value);

        if (divisor == 0)
            throw new ArithmeticException("Division by zero");

        double dividend =
                unit.convertToBaseUnit(value);

        return dividend / divisor;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}

// ---------------- MAIN APP ----------------
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("===== EQUALITY =====");
        System.out.println(litre.equals(ml));

        System.out.println("\n===== CONVERSION =====");
        System.out.println(litre.convertTo(VolumeUnit.MILLILITRE));

        System.out.println("\n===== ADDITION =====");
        System.out.println(litre.add(ml));

        System.out.println("\n===== SUBTRACTION =====");
        System.out.println(feet.subtract(inches));

        System.out.println("\n===== DIVISION =====");
        System.out.println(feet.divide(new Quantity<>(2.0, LengthUnit.FEET)));
    }
}