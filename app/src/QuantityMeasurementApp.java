import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

/* =========================
   INTERFACE
   ========================= */
interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
}

/* =========================
   LENGTH UNIT
   Base Unit = FEET
   ========================= */
enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12.0);

    private final double toBaseFactor;

    LengthUnit(double toBaseFactor) {
        this.toBaseFactor = toBaseFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * toBaseFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toBaseFactor;
    }
}

/* =========================
   WEIGHT UNIT
   Base Unit = KILOGRAM
   ========================= */
enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001);

    private final double toBaseFactor;

    WeightUnit(double toBaseFactor) {
        this.toBaseFactor = toBaseFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * toBaseFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toBaseFactor;
    }
}

/* =========================
   VOLUME UNIT
   Base Unit = LITRE
   ========================= */
enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001);

    private final double toBaseFactor;

    VolumeUnit(double toBaseFactor) {
        this.toBaseFactor = toBaseFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * toBaseFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toBaseFactor;
    }
}

/* =========================
   QUANTITY CLASS
   ========================= */
class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
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

    /* =========================
       ARITHMETIC OPERATION ENUM
       ========================= */
    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    /* =========================
       VALIDATION HELPER
       ========================= */
    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetRequired) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        if (other.unit == null) {
            throw new IllegalArgumentException("Other quantity unit cannot be null");
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException(
                    "Cross-category arithmetic not allowed");
        }

        if (!Double.isFinite(value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException(
                    "Values must be finite");
        }

        if (targetRequired && targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null");
        }
    }

    /* =========================
       CENTRALIZED ARITHMETIC
       ========================= */
    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double thisBase =
                unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return operation.compute(thisBase, otherBase);
    }

    /* =========================
       ROUNDING
       ========================= */
    private double roundToTwoDecimals(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    /* =========================
       ADD METHODS
       ========================= */
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other,
                        ArithmeticOperation.ADD);

        double converted =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(
                roundToTwoDecimals(converted),
                targetUnit);
    }

    /* =========================
       SUBTRACT METHODS
       ========================= */
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other,
                                U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other,
                        ArithmeticOperation.SUBTRACT);

        double converted =
                targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(
                roundToTwoDecimals(converted),
                targetUnit);
    }

    /* =========================
       DIVIDE METHOD
       ========================= */
    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(
                other,
                ArithmeticOperation.DIVIDE);
    }

    /* =========================
       EQUALS
       ========================= */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Quantity<?> other)) {
            return false;
        }

        double thisBase =
                unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < 0.01;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                roundToTwoDecimals(
                        unit.convertToBaseUnit(value)));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}

/* =========================
   MAIN APPLICATION
   ========================= */
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("===== ADDITION =====");

        Quantity<LengthUnit> addLength =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(new Quantity<>(12.0,
                                LengthUnit.INCHES));

        System.out.println(addLength);

        System.out.println("\n===== SUBTRACTION =====");

        Quantity<LengthUnit> subtractLength =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0,
                                LengthUnit.INCHES));

        System.out.println(subtractLength);

        Quantity<WeightUnit> subtractWeight =
                new Quantity<>(10.0,
                        WeightUnit.KILOGRAM)
                        .subtract(new Quantity<>(5000.0,
                                WeightUnit.GRAM));

        System.out.println(subtractWeight);

        Quantity<VolumeUnit> subtractVolume =
                new Quantity<>(5.0,
                        VolumeUnit.LITRE)
                        .subtract(new Quantity<>(2.0,
                                        VolumeUnit.LITRE),
                                VolumeUnit.MILLILITRE);

        System.out.println(subtractVolume);

        System.out.println("\n===== DIVISION =====");

        double ratio1 =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0,
                                LengthUnit.FEET));

        System.out.println("10 FEET / 2 FEET = " + ratio1);

        double ratio2 =
                new Quantity<>(24.0,
                        LengthUnit.INCHES)
                        .divide(new Quantity<>(2.0,
                                LengthUnit.FEET));

        System.out.println("24 INCHES / 2 FEET = " + ratio2);

        double ratio3 =
                new Quantity<>(2000.0,
                        WeightUnit.GRAM)
                        .divide(new Quantity<>(1.0,
                                WeightUnit.KILOGRAM));

        System.out.println("2000 GRAM / 1 KG = " + ratio3);

        System.out.println("\n===== CHAIN OPERATIONS =====");

        Quantity<LengthUnit> chain =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .add(new Quantity<>(2.0,
                                LengthUnit.FEET))
                        .subtract(new Quantity<>(1.0,
                                LengthUnit.FEET));

        System.out.println(chain);
    }
}