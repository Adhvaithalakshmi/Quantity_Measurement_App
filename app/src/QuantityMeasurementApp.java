public class QuantityMeasurementApp {

    // Feet class
    static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

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
            Feet feet = (Feet) obj;

            // Compare values
            return Double.compare(feet.value, value) == 0;
        }
    }

    // Inches class
    static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

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
            Inches inches = (Inches) obj;

            // Compare values
            return Double.compare(inches.value, value) == 0;
        }
    }

    // Method for Feet equality
    public static boolean checkFeetEquality(double value1, double value2) {

        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);

        return feet1.equals(feet2);
    }

    // Method for Inches equality
    public static boolean checkInchesEquality(double value1, double value2) {

        Inches inch1 = new Inches(value1);
        Inches inch2 = new Inches(value2);

        return inch1.equals(inch2);
    }

    // Main method
    public static void main(String[] args) {

        // Feet comparison
        boolean feetResult = checkFeetEquality(1.0, 1.0);

        // Inches comparison
        boolean inchResult = checkInchesEquality(1.0, 1.0);

        // Output
        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + inchResult + ")");

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + feetResult + ")");
    }
}