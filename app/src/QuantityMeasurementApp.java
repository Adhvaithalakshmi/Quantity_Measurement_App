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

    // Generic QuantityLength class
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        // Constructor
        public QuantityLength(double value,
                              LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException(
                        "Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        // Convert all values to feet
        private double toFeet() {
            return value * unit.getConversionFactor();
        }

        // Override equals()
        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) {
                return true;
            }

            // Null or different class
            if (obj == null ||
                    getClass() != obj.getClass()) {
                return false;
            }

            // Type casting
            QuantityLength other =
                    (QuantityLength) obj;

            // Compare converted values
            return Double.compare(
                    this.toFeet(),
                    other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value +
                    ", " + unit + ")";
        }
    }

    // Main method
    public static void main(String[] args) {

        // Yard to feet
        QuantityLength q1 =
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS);

        QuantityLength q2 =
                new QuantityLength(
                        3.0,
                        LengthUnit.FEET);

        // Yard to inches
        QuantityLength q3 =
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS);

        QuantityLength q4 =
                new QuantityLength(
                        36.0,
                        LengthUnit.INCHES);

        // Yard to yard
        QuantityLength q5 =
                new QuantityLength(
                        2.0,
                        LengthUnit.YARDS);

        QuantityLength q6 =
                new QuantityLength(
                        2.0,
                        LengthUnit.YARDS);

        // Cm to cm
        QuantityLength q7 =
                new QuantityLength(
                        2.0,
                        LengthUnit.CENTIMETERS);

        QuantityLength q8 =
                new QuantityLength(
                        2.0,
                        LengthUnit.CENTIMETERS);

        // Cm to inches
        QuantityLength q9 =
                new QuantityLength(
                        1.0,
                        LengthUnit.CENTIMETERS);

        QuantityLength q10 =
                new QuantityLength(
                        0.393701,
                        LengthUnit.INCHES);

        // Outputs
        System.out.println(
                "Input: " + q1 + " and " + q2);

        System.out.println(
                "Output: Equal (" +
                        q1.equals(q2) + ")");

        System.out.println();

        System.out.println(
                "Input: " + q3 + " and " + q4);

        System.out.println(
                "Output: Equal (" +
                        q3.equals(q4) + ")");

        System.out.println();

        System.out.println(
                "Input: " + q5 + " and " + q6);

        System.out.println(
                "Output: Equal (" +
                        q5.equals(q6) + ")");

        System.out.println();

        System.out.println(
                "Input: " + q7 + " and " + q8);

        System.out.println(
                "Output: Equal (" +
                        q7.equals(q8) + ")");

        System.out.println();

        System.out.println(
                "Input: " + q9 + " and " + q10);

        System.out.println(
                "Output: Equal (" +
                        q9.equals(q10) + ")");
    }
}