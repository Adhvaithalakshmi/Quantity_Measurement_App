public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        double epsilon = 1e-6;

        // Feet to inches
        double result1 =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                1.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES);

        System.out.println(
                "Feet to Inches: "
                        + (result1 == 12.0));

        // Inches to feet
        double result2 =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                24.0,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET);

        System.out.println(
                "Inches to Feet: "
                        + (result2 == 2.0));

        // Yards to inches
        double result3 =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                1.0,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES);

        System.out.println(
                "Yards to Inches: "
                        + (result3 == 36.0));

        // Inches to yards
        double result4 =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                72.0,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS);

        System.out.println(
                "Inches to Yards: "
                        + (result4 == 2.0));

        // CM to inches
        double result5 =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                2.54,
                                QuantityMeasurementApp
                                        .LengthUnit.CENTIMETERS,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES);

        System.out.println(
                "CM to Inches: "
                        + (Math.abs(result5 - 1.0)
                        < epsilon));

        // Feet to yards
        double result6 =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                6.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS);

        System.out.println(
                "Feet to Yards: "
                        + (result6 == 2.0));

        // Round trip conversion
        double value = 5.0;

        double converted =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                value,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES);

        double back =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                converted,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET);

        System.out.println(
                "Round Trip: "
                        + (Math.abs(back - value)
                        < epsilon));

        // Zero conversion
        double zero =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                0.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES);

        System.out.println(
                "Zero Conversion: "
                        + (zero == 0.0));

        // Negative conversion
        double negative =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                -1.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES);

        System.out.println(
                "Negative Conversion: "
                        + (negative == -12.0));

        // Same unit conversion
        double same =
                QuantityMeasurementApp
                        .QuantityLength.convert(
                                5.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET);

        System.out.println(
                "Same Unit Conversion: "
                        + (same == 5.0));

        // Null unit exception
        try {

            QuantityMeasurementApp
                    .QuantityLength.convert(
                            1.0,
                            null,
                            QuantityMeasurementApp
                                    .LengthUnit.FEET);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Null Unit Exception: "
                            + e.getMessage());
        }

        // NaN exception
        try {

            QuantityMeasurementApp
                    .QuantityLength.convert(
                            Double.NaN,
                            QuantityMeasurementApp
                                    .LengthUnit.FEET,
                            QuantityMeasurementApp
                                    .LengthUnit.INCHES);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "NaN Exception: "
                            + e.getMessage());
        }

        // Infinity exception
        try {

            QuantityMeasurementApp
                    .QuantityLength.convert(
                            Double.POSITIVE_INFINITY,
                            QuantityMeasurementApp
                                    .LengthUnit.FEET,
                            QuantityMeasurementApp
                                    .LengthUnit.INCHES);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Infinity Exception: "
                            + e.getMessage());
        }
    }
}