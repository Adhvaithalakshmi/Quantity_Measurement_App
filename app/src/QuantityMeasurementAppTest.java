// QuantityMeasurementTest.java

public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        System.out.println("===== LENGTH TESTS =====");

        Quantity<LengthUnit> l1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> l3 =
                new Quantity<>(1.0, LengthUnit.YARDS);

        // Equality Test
        System.out.println(
                "1 FEET == 12 INCHES : "
                        + l1.equals(l2)
        );

        // Conversion Test
        System.out.println(
                "1 FEET to INCHES : "
                        + l1.convertTo(LengthUnit.INCHES)
        );

        // Addition Test
        System.out.println(
                "1 FEET + 12 INCHES in FEET : "
                        + l1.add(l2, LengthUnit.FEET)
        );

        // Cross Unit Test
        System.out.println(
                "1 YARD == 3 FEET : "
                        + l3.equals(
                        new Quantity<>(3.0, LengthUnit.FEET))
        );

        // Negative Value Test
        System.out.println(
                "Negative Addition : "
                        + new Quantity<>(5.0, LengthUnit.FEET)
                        .add(
                                new Quantity<>(-2.0,
                                        LengthUnit.FEET),
                                LengthUnit.FEET)
        );

        // Zero Test
        System.out.println(
                "Zero Addition : "
                        + new Quantity<>(5.0, LengthUnit.FEET)
                        .add(
                                new Quantity<>(0.0,
                                        LengthUnit.INCHES),
                                LengthUnit.FEET)
        );

        System.out.println("\n===== WEIGHT TESTS =====");

        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> w3 =
                new Quantity<>(2.20462, WeightUnit.POUND);

        // Equality Test
        System.out.println(
                "1 KG == 1000 GRAM : "
                        + w1.equals(w2)
        );

        // Pound Equality Test
        System.out.println(
                "1 KG == 2.20462 POUND : "
                        + w1.equals(w3)
        );

        // Conversion Test
        System.out.println(
                "1 KG to GRAM : "
                        + w1.convertTo(WeightUnit.GRAM)
        );

        // Addition Test
        System.out.println(
                "1 KG + 1000 GRAM : "
                        + w1.add(w2, WeightUnit.KILOGRAM)
        );

        // Explicit Target Unit
        System.out.println(
                "1 KG + 1000 GRAM in GRAM : "
                        + w1.add(w2, WeightUnit.GRAM)
        );

        // Negative Weight Test
        System.out.println(
                "Negative Weight Addition : "
                        + new Quantity<>(5.0,
                        WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<>(-2.0,
                                        WeightUnit.KILOGRAM),
                                WeightUnit.KILOGRAM)
        );

        // Zero Weight Test
        System.out.println(
                "Zero Weight Addition : "
                        + new Quantity<>(5.0,
                        WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<>(0.0,
                                        WeightUnit.GRAM),
                                WeightUnit.KILOGRAM)
        );

        System.out.println("\n===== CROSS CATEGORY TEST =====");

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        System.out.println(
                "Length equals Weight : "
                        + length.equals(weight)
        );

        System.out.println("\n===== ALL TESTS COMPLETED =====");
    }
}