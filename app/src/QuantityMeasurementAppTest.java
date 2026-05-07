public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        // Yard to yard same value
        QuantityMeasurementApp.QuantityLength y1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.QuantityLength y2 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        System.out.println(
                "Yard Same Value: "
                        + y1.equals(y2)); // true

        // Yard to yard different value
        QuantityMeasurementApp.QuantityLength y3 =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        System.out.println(
                "Yard Different Value: "
                        + y1.equals(y3)); // false

        // Yard to feet
        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        3.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        System.out.println(
                "Yard to Feet: "
                        + y1.equals(feet)); // true

        // Feet to yard
        System.out.println(
                "Feet to Yard: "
                        + feet.equals(y1)); // true

        // Yard to inches
        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        36.0,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        System.out.println(
                "Yard to Inches: "
                        + y1.equals(inches)); // true

        // Inches to yard
        System.out.println(
                "Inches to Yard: "
                        + inches.equals(y1)); // true

        // Non-equivalent comparison
        QuantityMeasurementApp.QuantityLength feet2 =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        System.out.println(
                "Yard to Feet Non Equivalent: "
                        + y1.equals(feet2)); // false

        // Centimeters to inches
        QuantityMeasurementApp.QuantityLength cm1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS);

        QuantityMeasurementApp.QuantityLength inch1 =
                new QuantityMeasurementApp.QuantityLength(
                        0.393701,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        System.out.println(
                "CM to Inches: "
                        + cm1.equals(inch1)); // true

        // Centimeters to feet non-equivalent
        QuantityMeasurementApp.QuantityLength feet3 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        System.out.println(
                "CM to Feet Non Equivalent: "
                        + cm1.equals(feet3)); // false

        // Transitive property
        System.out.println(
                "Transitive Property: "
                        + (y1.equals(feet)
                        && feet.equals(inches)
                        && y1.equals(inches))); // true

        // Same reference
        System.out.println(
                "Same Reference: "
                        + y1.equals(y1)); // true

        // Null comparison
        System.out.println(
                "Null Comparison: "
                        + y1.equals(null)); // false

        // Different type
        System.out.println(
                "Different Type: "
                        + y1.equals("1.0")); // false

        // Null unit handling
        try {

            QuantityMeasurementApp.QuantityLength invalid =
                    new QuantityMeasurementApp.QuantityLength(
                            1.0,
                            null);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Null Unit Exception: "
                            + e.getMessage());
        }

        // Complex scenario
        QuantityMeasurementApp.QuantityLength yard2 =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.QuantityLength feet6 =
                new QuantityMeasurementApp.QuantityLength(
                        6.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength inch72 =
                new QuantityMeasurementApp.QuantityLength(
                        72.0,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        System.out.println(
                "Complex Scenario: "
                        + (yard2.equals(feet6)
                        && feet6.equals(inch72)
                        && yard2.equals(inch72))); // true
    }
}