public class QuantityMeasurementApp {

    // Inner class to represent Feet measurement
    static class Feet {

        // Private final field for immutability
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Overriding equals() method
        @Override
        public boolean equals(Object obj) {

            // Check same reference
            if (this == obj) {
                return true;
            }

            // Check null or different class
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // Type casting
            Feet feet = (Feet) obj;

            // Compare double values
            return Double.compare(feet.value, value) == 0;
        }
    }

    // Main method
    public static void main(String[] args) {

        // Creating Feet objects
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        // Comparing objects
        boolean result = feet1.equals(feet2);

        // Printing result
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + result + ")");
    }
}