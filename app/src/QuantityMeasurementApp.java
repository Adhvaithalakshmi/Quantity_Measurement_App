// ==========================
// UC11 MAIN PROGRAM
// ==========================

interface IMeasurable {
    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
}

// ---------------- LENGTH ----------------
enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    public String getUnitName() {
        return name();
    }
}

// ---------------- WEIGHT ----------------
enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(0.001),
    TONNE(1000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
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

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    public String getUnitName() {
        return name();
    }
}

// ---------------- GENERIC QUANTITY ----------------
class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        double baseValue = unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result =
                targetUnit.convertFromBaseUnit(sum);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (this.unit.getClass() != other.unit.getClass())
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 =
                other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(
                unit.convertToBaseUnit(value)
        );
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", "
                + unit.getUnitName() + ")";
    }
}

// ---------------- MAIN APP ----------------
public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2) {

        System.out.println(q1 + " == " + q2
                + " -> " + q1.equals(q2));
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(
            Quantity<U> q,
            U targetUnit) {

        System.out.println(q + " converted to "
                + targetUnit.getUnitName()
                + " -> " + q.convertTo(targetUnit));
    }

    public static <U extends IMeasurable>
    void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        System.out.println(q1 + " + " + q2
                + " -> " + q1.add(q2, targetUnit));
    }

    public static void main(String[] args) {

        // ---------- LENGTH ----------
        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);

        demonstrateConversion(
                length1,
                LengthUnit.INCHES);

        demonstrateAddition(
                length1,
                length2,
                LengthUnit.FEET);

        // ---------- WEIGHT ----------
        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);

        demonstrateConversion(
                weight1,
                WeightUnit.GRAM);

        demonstrateAddition(
                weight1,
                weight2,
                WeightUnit.KILOGRAM);

        // ---------- VOLUME ----------
        Quantity<VolumeUnit> volume1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(1000.0,
                        VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> volume3 =
                new Quantity<>(1.0,
                        VolumeUnit.GALLON);

        demonstrateEquality(volume1, volume2);

        demonstrateConversion(
                volume3,
                VolumeUnit.LITRE);

        demonstrateAddition(
                volume1,
                volume2,
                VolumeUnit.LITRE);

        // ---------- CROSS CATEGORY ----------
        System.out.println(
                length1.equals(weight1)
        );
    }
}