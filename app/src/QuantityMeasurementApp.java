public class QuantityMeasurementApp {

    // Enum for units
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Generic Quantity Length class
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        // Constructor
        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return value * unit.getConversionFactor();
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) {
                return true;
            }

            // Null or different class
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // Type casting
            QuantityLength other = (QuantityLength) obj;

            // Compare converted values
            return Double.compare(this.toFeet(),
                    other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // Main method
    public static void main(String[] args) {

        // Feet to inches comparison
        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCH);

        // Inch to inch comparison
        QuantityLength q3 =
                new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength q4 =
                new QuantityLength(1.0, LengthUnit.INCH);

        // Results
        System.out.println("Input: " + q1 + " and " + q2);
        System.out.println("Output: Equal (" +
                q1.equals(q2) + ")");

        System.out.println();

        System.out.println("Input: " + q3 + " and " + q4);
        System.out.println("Output: Equal (" +
                q3.equals(q4) + ")");
    }
}