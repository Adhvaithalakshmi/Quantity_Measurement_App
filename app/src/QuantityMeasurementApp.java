public class QuantityMeasurementApp {

    // Enum for supported units
    enum LengthUnit {

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
    }

    // QuantityLength class
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        // Constructor
        public QuantityLength(double value,
                              LengthUnit unit) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Value must be finite");
            }

            if (unit == null) {
                throw new IllegalArgumentException(
                        "Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return value *
                    unit.getConversionFactor();
        }

        // Convert to another unit
        public QuantityLength convertTo(
                LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null");
            }

            double feetValue = toFeet();

            double convertedValue =
                    feetValue /
                            targetUnit.getConversionFactor();

            return new QuantityLength(
                    convertedValue,
                    targetUnit);
        }

        // UC6 Add method
        public QuantityLength add(
                QuantityLength other) {

            if (other == null) {
                throw new IllegalArgumentException(
                        "Second operand cannot be null");
            }

            return add(this,
                    other,
                    this.unit);
        }

        // UC7 Add method with explicit target unit
        public static QuantityLength add(
                QuantityLength q1,
                QuantityLength q2,
                LengthUnit targetUnit) {

            if (q1 == null || q2 == null) {
                throw new IllegalArgumentException(
                        "Operands cannot be null");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null");
            }

            // Convert both to feet
            double totalFeet =
                    q1.toFeet()
                            + q2.toFeet();

            // Convert result to target unit
            double result =
                    totalFeet /
                            targetUnit.getConversionFactor();

            return new QuantityLength(
                    result,
                    targetUnit);
        }

        // Equals method
        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null
                    || getClass() != obj.getClass()) {

                return false;
            }

            QuantityLength other =
                    (QuantityLength) obj;

            return Math.abs(
                    this.toFeet()
                            - other.toFeet())
                    < 1e-6;
        }

        // toString method
        @Override
        public String toString() {

            return "Quantity(" +
                    value +
                    ", " +
                    unit +
                    ")";
        }
    }

    // Main method
    public static void main(String[] args) {

        QuantityLength foot =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES);

        // FEET target
        System.out.println(
                "Input: add(" + foot +
                        ", " + inches +
                        ", FEET)");

        System.out.println(
                "Output: "
                        + QuantityLength.add(
                        foot,
                        inches,
                        LengthUnit.FEET));

        System.out.println();

        // INCHES target
        System.out.println(
                "Input: add(" + foot +
                        ", " + inches +
                        ", INCHES)");

        System.out.println(
                "Output: "
                        + QuantityLength.add(
                        foot,
                        inches,
                        LengthUnit.INCHES));

        System.out.println();

        // YARDS target
        System.out.println(
                "Input: add(" + foot +
                        ", " + inches +
                        ", YARDS)");

        System.out.println(
                "Output: "
                        + QuantityLength.add(
                        foot,
                        inches,
                        LengthUnit.YARDS));

        System.out.println();

        // YARDS + FEET
        QuantityLength yard =
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(
                        3.0,
                        LengthUnit.FEET);

        System.out.println(
                "Input: add(" + yard +
                        ", " + feet +
                        ", YARDS)");

        System.out.println(
                "Output: "
                        + QuantityLength.add(
                        yard,
                        feet,
                        LengthUnit.YARDS));

        System.out.println();

        // INCHES + YARDS -> FEET
        QuantityLength inch36 =
                new QuantityLength(
                        36.0,
                        LengthUnit.INCHES);

        System.out.println(
                "Input: add(" + inch36 +
                        ", " + yard +
                        ", FEET)");

        System.out.println(
                "Output: "
                        + QuantityLength.add(
                        inch36,
                        yard,
                        LengthUnit.FEET));

        System.out.println();

        // CM + INCHES
        QuantityLength cm =
                new QuantityLength(
                        2.54,
                        LengthUnit.CENTIMETERS);

        QuantityLength inch1 =
                new QuantityLength(
                        1.0,
                        LengthUnit.INCHES);

        System.out.println(
                "Input: add(" + cm +
                        ", " + inch1 +
                        ", CENTIMETERS)");

        System.out.println(
                "Output: "
                        + QuantityLength.add(
                        cm,
                        inch1,
                        LengthUnit.CENTIMETERS));

        System.out.println();

        // ZERO value
        QuantityLength zero =
                new QuantityLength(
                        0.0,
                        LengthUnit.INCHES);

        QuantityLength fiveFeet =
                new QuantityLength(
                        5.0,
                        LengthUnit.FEET);

        System.out.println(
                "Input: add(" + fiveFeet +
                        ", " + zero +
                        ", YARDS)");

        System.out.println(
                "Output: "
                        + QuantityLength.add(
                        fiveFeet,
                        zero,
                        LengthUnit.YARDS));

        System.out.println();

        // NEGATIVE values
        QuantityLength negative =
                new QuantityLength(
                        -2.0,
                        LengthUnit.FEET);

        System.out.println(
                "Input: add(" + fiveFeet +
                        ", " + negative +
                        ", INCHES)");

        System.out.println(
                "Output: "
                        + QuantityLength.add(
                        fiveFeet,
                        negative,
                        LengthUnit.INCHES));
    }
}