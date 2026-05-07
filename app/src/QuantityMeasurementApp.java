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

        // Static convert method
        public static double convert(
                double value,
                LengthUnit sourceUnit,
                LengthUnit targetUnit) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Value must be finite");
            }

            if (sourceUnit == null
                    || targetUnit == null) {

                throw new IllegalArgumentException(
                        "Units cannot be null");
            }

            double feetValue =
                    value *
                            sourceUnit.getConversionFactor();

            return feetValue /
                    targetUnit.getConversionFactor();
        }

        // Addition method
        public QuantityLength add(
                QuantityLength other) {

            if (other == null) {
                throw new IllegalArgumentException(
                        "Second operand cannot be null");
            }

            // Convert both to feet
            double totalFeet =
                    this.toFeet()
                            + other.toFeet();

            // Convert back to current object's unit
            double result =
                    totalFeet /
                            this.unit.getConversionFactor();

            return new QuantityLength(
                    result,
                    this.unit);
        }

        // Static add method
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

            double totalFeet =
                    q1.toFeet()
                            + q2.toFeet();

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

            return Double.compare(
                    this.toFeet(),
                    other.toFeet()) == 0;
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

        // Feet + Feet
        QuantityLength q1 =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(
                        2.0,
                        LengthUnit.FEET);

        System.out.println(
                "Input: add(" + q1 + ", " + q2 + ")");

        System.out.println(
                "Output: " + q1.add(q2));

        System.out.println();

        // Feet + Inches
        QuantityLength q3 =
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES);

        System.out.println(
                "Input: add(" + q1 + ", " + q3 + ")");

        System.out.println(
                "Output: " + q1.add(q3));

        System.out.println();

        // Inches + Feet
        System.out.println(
                "Input: add(" + q3 + ", " + q1 + ")");

        System.out.println(
                "Output: " + q3.add(q1));

        System.out.println();

        // Yards + Feet
        QuantityLength yard =
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(
                        3.0,
                        LengthUnit.FEET);

        System.out.println(
                "Input: add(" + yard + ", " + feet + ")");

        System.out.println(
                "Output: " + yard.add(feet));

        System.out.println();

        // CM + Inches
        QuantityLength cm =
                new QuantityLength(
                        2.54,
                        LengthUnit.CENTIMETERS);

        QuantityLength inch =
                new QuantityLength(
                        1.0,
                        LengthUnit.INCHES);

        System.out.println(
                "Input: add(" + cm + ", " + inch + ")");

        System.out.println(
                "Output: " + cm.add(inch));

        System.out.println();

        // Zero value
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
                        ", " + zero + ")");

        System.out.println(
                "Output: " + fiveFeet.add(zero));

        System.out.println();

        // Negative value
        QuantityLength negative =
                new QuantityLength(
                        -2.0,
                        LengthUnit.FEET);

        System.out.println(
                "Input: add(" + fiveFeet +
                        ", " + negative + ")");

        System.out.println(
                "Output: " + fiveFeet.add(negative));
    }
}