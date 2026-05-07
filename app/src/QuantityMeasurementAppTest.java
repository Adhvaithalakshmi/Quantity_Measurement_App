public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        double epsilon = 1e-6;

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

        // FEET target
        QuantityMeasurementApp.QuantityLength result1 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                foot,
                                inches,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET);

        System.out.println(
                "Feet Target: "
                        + result1.equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                2.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET)));

        // INCHES target
        QuantityMeasurementApp.QuantityLength result2 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                foot,
                                inches,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES);

        System.out.println(
                "Inches Target: "
                        + result2.equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                24.0,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES)));

        // YARDS target
        QuantityMeasurementApp.QuantityLength result3 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                foot,
                                inches,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS);

        System.out.println(
                "Yards Target: "
                        + (Math.abs(
                        result3.getValue()
                                - 0.6666667)
                        < epsilon));

        // CENTIMETERS target
        QuantityMeasurementApp.QuantityLength inch1 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        1.0,
                        QuantityMeasurementApp
                                .LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result4 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                inch1,
                                inch1,
                                QuantityMeasurementApp
                                        .LengthUnit.CENTIMETERS);

        System.out.println(
                "Centimeters Target: "
                        + (Math.abs(
                        result4.getValue()
                                - 5.08)
                        < 0.01));

        // SAME AS FIRST OPERAND
        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp
                        .QuantityLength(
                        2.0,
                        QuantityMeasurementApp
                                .LengthUnit.YARDS);

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp
                        .QuantityLength(
                        3.0,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result5 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                yard,
                                feet,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS);

        System.out.println(
                "Same As First Operand: "
                        + result5.equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                3.0,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS)));

        // SAME AS SECOND OPERAND
        QuantityMeasurementApp.QuantityLength result6 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                yard,
                                feet,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET);

        System.out.println(
                "Same As Second Operand: "
                        + result6.equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                9.0,
                                QuantityMeasurementApp
                                        .LengthUnit.FEET)));

        // COMMUTATIVITY
        QuantityMeasurementApp.QuantityLength sum1 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                foot,
                                inches,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS);

        QuantityMeasurementApp.QuantityLength sum2 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                inches,
                                foot,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS);

        System.out.println(
                "Commutativity: "
                        + sum1.equals(sum2));

        // ZERO VALUE
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

        QuantityMeasurementApp.QuantityLength result7 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                fiveFeet,
                                zero,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS);

        System.out.println(
                "Zero Value: "
                        + (Math.abs(
                        result7.getValue()
                                - 1.6666667)
                        < epsilon));

        // NEGATIVE VALUES
        QuantityMeasurementApp.QuantityLength negative =
                new QuantityMeasurementApp
                        .QuantityLength(
                        -2.0,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result8 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                fiveFeet,
                                negative,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES);

        System.out.println(
                "Negative Values: "
                        + result8.equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                36.0,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES)));

        // NULL TARGET UNIT
        try {

            QuantityMeasurementApp
                    .QuantityLength.add(
                            foot,
                            inches,
                            null);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Null Target Unit Exception: "
                            + e.getMessage());
        }

        // LARGE VALUES
        QuantityMeasurementApp.QuantityLength large1 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        1000.0,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength large2 =
                new QuantityMeasurementApp
                        .QuantityLength(
                        500.0,
                        QuantityMeasurementApp
                                .LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result9 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                large1,
                                large2,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES);

        System.out.println(
                "Large To Small Scale: "
                        + result9.equals(
                        new QuantityMeasurementApp
                                .QuantityLength(
                                18000.0,
                                QuantityMeasurementApp
                                        .LengthUnit.INCHES)));

        // SMALL TO LARGE SCALE
        QuantityMeasurementApp.QuantityLength inch12a =
                new QuantityMeasurementApp
                        .QuantityLength(
                        12.0,
                        QuantityMeasurementApp
                                .LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength inch12b =
                new QuantityMeasurementApp
                        .QuantityLength(
                        12.0,
                        QuantityMeasurementApp
                                .LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result10 =
                QuantityMeasurementApp
                        .QuantityLength.add(
                                inch12a,
                                inch12b,
                                QuantityMeasurementApp
                                        .LengthUnit.YARDS);

        System.out.println(
                "Small To Large Scale: "
                        + (Math.abs(
                        result10.getValue()
                                - 0.6666667)
                        < epsilon));
    }
}