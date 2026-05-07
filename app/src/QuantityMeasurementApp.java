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

        // Convert current object to target unit
        public QuantityLength convertTo(
                LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null");
            }

            // Convert to feet
            double feetValue = toFeet();

            // Convert feet to target unit
            double convertedValue =
                    feetValue /
                            targetUnit.getConversionFactor();

            return new QuantityLength(
                    convertedValue,
                    targetUnit);
        }

        // Static conversion API
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

            // Convert source to feet
            double feetValue =
                    value *
                            sourceUnit.getConversionFactor();

            // Convert feet to target
            return feetValue /
                    targetUnit.getConversionFactor();
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

    // Method Overloading Example 1
    public static void demonstrateLengthConversion(
            double value,
            LengthUnit fromUnit,
            LengthUnit toUnit) {

        double result =
                QuantityLength.convert(
                        value,
                        fromUnit,
                        toUnit);

        System.out.println(
                "Input: convert(" +
                        value + ", " +
                        fromUnit + ", " +
                        toUnit + ")");

        System.out.println(
                "Output: " + result);

        System.out.println();
    }

    // Method Overloading Example 2
    public static void demonstrateLengthConversion(
            QuantityLength quantity,
            LengthUnit toUnit) {

        QuantityLength converted =
                quantity.convertTo(toUnit);

        System.out.println(
                "Input: " + quantity);

        System.out.println(
                "Converted To: " + converted);

        System.out.println();
    }

    // Equality demonstration
    public static void demonstrateLengthEquality(
            QuantityLength q1,
            QuantityLength q2) {

        System.out.println(
                q1 + " equals " + q2 +
                        " -> " +
                        q1.equals(q2));
    }

    // Main method
    public static void main(String[] args) {

        // Static conversion examples
        demonstrateLengthConversion(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        demonstrateLengthConversion(
                3.0,
                LengthUnit.YARDS,
                LengthUnit.FEET);

        demonstrateLengthConversion(
                36.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS);

        demonstrateLengthConversion(
                1.0,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES);

        demonstrateLengthConversion(
                0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        // Instance conversion example
        QuantityLength yard =
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS);

        demonstrateLengthConversion(
                yard,
                LengthUnit.INCHES);

        // Equality example
        QuantityLength feet =
                new QuantityLength(
                        3.0,
                        LengthUnit.FEET);

        demonstrateLengthEquality(
                yard,
                feet);
    }
}