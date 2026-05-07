public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        double epsilon = 1e-6;

        // Same unit feet + feet
        QuantityMeasurementApp.QuantityLength f1 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        1.0,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength f2 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        2.0,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        System.out.println(
                "Feet + Feet: "
                        + f1.add(f2).equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                3.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET)));

        // Inches + Inches
        QuantityMeasurementApp.QuantityLength i1 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        6.0,
                        QuantityMeasurementApp
                                .LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength i2 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        6.0,
                        QuantityMeasurementApp
                                .LengthUnit.INCHES);

        System.out.println(
                "Inches + Inches: "
                        + i1.add(i2).equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                12.0,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES)));

        // Feet + Inches
        QuantityMeasurementApp.QuantityLength foot =
                new QuantityMeasurementApp
                        .QuantityLength(
                        1.0,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp
                        .QuantityLength(
                        12.0,
                        QuantityMeasurementApp
                                .LengthUnit.INCHES);

        System.out.println(
                "Feet + Inches: "
                        + foot.add(inches).equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                2.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET)));

        // Inches + Feet
        System.out.println(
                "Inches + Feet: "
                        + inches.add(foot).equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                24.0,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES)));

        // Yard + Feet
        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp
                        .QuantityLength(
                        1.0,
                        QuantityMeasurementApp
                                .LengthUnit.YARDS);

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp
                        .QuantityLength(
                        3.0,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        System.out.println(
                "Yard + Feet: "
                        + yard.add(feet).equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                2.0,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS)));

        // CM + Inch
        QuantityMeasurementApp.QuantityLength cm =
                new QuantityMeasurementApp
                        .QuantityLength(
                        2.54,
                        QuantityMeasurementApp
                                .LengthUnit.CENTIMETERS);

        QuantityMeasurementApp.QuantityLength inch =
                new QuantityMeasurementApp
                        .QuantityLength(
                        1.0,
                        QuantityMeasurementApp
                                .LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result =
                cm.add(inch);

        System.out.println(
                "CM + Inch: "
                        + (Math.abs(
                        result.convertTo(
                                        QuantityMeasurementApp
                                                .LengthUnit.CENTIMETERS)
                                .convertTo(
                                        QuantityMeasurementApp
                                                .LengthUnit.CENTIMETERS)
                                .add(
                                        new QuantityMeasurementApp
                                                .QuantityLength(
                                                0.0,
                                                QuantityMeasurementApp
                                                        .LengthUnit.CENTIMETERS))
                                .convertTo(
                                        QuantityMeasurementApp
                                                .LengthUnit.CENTIMETERS)
                                .equals(result)
                                ? 0 : 1) < epsilon));

        // Commutativity
        QuantityMeasurementApp.QuantityLength sum1 =
                foot.add(inches);

        QuantityMeasurementApp.QuantityLength sum2 =
                inches.add(foot)
                        .convertTo(
                                QuantityMeasurementApp
                                        .LengthUnit.FEET);

        System.out.println(
                "Commutativity: "
                        + sum1.equals(sum2));

        // Adding zero
        QuantityMeasurementApp.QuantityLength zero =
                new QuantityMeasurementApp
                        .QuantityLength(
                        0.0,
                        QuantityMeasurementApp
                                .LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength fiveFeet =
                new QuantityMeasurementApp
                        .QuantityLength(
                        5.0,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        System.out.println(
                "Identity Element: "
                        + fiveFeet.add(zero).equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                5.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET)));

        // Negative values
        QuantityMeasurementApp.QuantityLength negative =
                new QuantityMeasurementApp
                        .QuantityLength(
                        -2.0,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        System.out.println(
                "Negative Values: "
                        + fiveFeet.add(negative).equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                3.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET)));

        // Null operand
        try {

            foot.add(null);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Null Operand Exception: "
                            + e.getMessage());
        }

        // Large values
        QuantityMeasurementApp.QuantityLength large1 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        1e6,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength large2 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        1e6,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        System.out.println(
                "Large Values: "
                        + large1.add(large2).equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                2e6,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET)));

        // Small values
        QuantityMeasurementApp.QuantityLength small1 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        0.001,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength small2 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        0.002,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength smallResult =
                small1.add(small2);

        System.out.println(
                "Small Values: "
                        + (Math.abs(
                        QuantityMeasurementApp
                                .QuantityLength.convert(
                                        0.003,
                                        QuantityMeasurementApp
                                                .LengthUnit.FEET,
                                        QuantityMeasurementApp
                                                .LengthUnit.FEET)
                                - 0.003)
                        < epsilon));
    }
}