public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        // Same feet value
        QuantityMeasurementApp.QuantityLength feet1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength feet2 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        System.out.println(
                "Feet to Feet Same Value: "
                        + feet1.equals(feet2)); // true

        // Same inch value
        QuantityMeasurementApp.QuantityLength inch1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength inch2 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        System.out.println(
                "Inch to Inch Same Value: "
                        + inch1.equals(inch2)); // true

        // Feet to inch equivalent
        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        System.out.println(
                "Feet to Inch Equivalent: "
                        + feet.equals(inches)); // true

        // Inch to feet equivalent
        System.out.println(
                "Inch to Feet Equivalent: "
                        + inches.equals(feet)); // true

        // Different feet values
        QuantityMeasurementApp.QuantityLength feet3 =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        System.out.println(
                "Feet Different Value: "
                        + feet1.equals(feet3)); // false

        // Different inch values
        QuantityMeasurementApp.QuantityLength inch3 =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        System.out.println(
                "Inch Different Value: "
                        + inch1.equals(inch3)); // false

        // Same reference
        System.out.println(
                "Same Reference: "
                        + feet1.equals(feet1)); // true

        // Null comparison
        System.out.println(
                "Null Comparison: "
                        + feet1.equals(null)); // false

        // Different type
        System.out.println(
                "Different Type: "
                        + feet1.equals("1.0")); // false

        // Null unit test
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
    }
}