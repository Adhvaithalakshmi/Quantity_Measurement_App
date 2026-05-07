public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        // Feet objects
        QuantityMeasurementApp.Feet feet1 =
                new QuantityMeasurementApp.Feet(1.0);

        QuantityMeasurementApp.Feet feet2 =
                new QuantityMeasurementApp.Feet(1.0);

        QuantityMeasurementApp.Feet feet3 =
                new QuantityMeasurementApp.Feet(2.0);

        // Inches objects
        QuantityMeasurementApp.Inches inch1 =
                new QuantityMeasurementApp.Inches(1.0);

        QuantityMeasurementApp.Inches inch2 =
                new QuantityMeasurementApp.Inches(1.0);

        QuantityMeasurementApp.Inches inch3 =
                new QuantityMeasurementApp.Inches(2.0);

        // Feet Test Cases
        System.out.println("Feet Same Value: "
                + feet1.equals(feet2)); // true

        System.out.println("Feet Different Value: "
                + feet1.equals(feet3)); // false

        System.out.println("Feet Null Comparison: "
                + feet1.equals(null)); // false

        System.out.println("Feet Same Reference: "
                + feet1.equals(feet1)); // true

        System.out.println("Feet Different Type: "
                + feet1.equals("1.0")); // false

        // Inches Test Cases
        System.out.println("Inches Same Value: "
                + inch1.equals(inch2)); // true

        System.out.println("Inches Different Value: "
                + inch1.equals(inch3)); // false

        System.out.println("Inches Null Comparison: "
                + inch1.equals(null)); // false

        System.out.println("Inches Same Reference: "
                + inch1.equals(inch1)); // true

        System.out.println("Inches Different Type: "
                + inch1.equals("1.0")); // false
    }
}