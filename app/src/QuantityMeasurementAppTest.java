public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        // =====================================================
        // EQUALITY TESTS
        // =====================================================

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        System.out.println("Litre == Millilitre : "
                + litre.equals(ml));

        // =====================================================
        // CONVERSION TESTS
        // =====================================================

        System.out.println("\nConvert Litre to Millilitre");
        System.out.println(
                litre.convertTo(VolumeUnit.MILLILITRE));

        System.out.println("\nConvert Gallon to Litre");

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println(
                gallon.convertTo(VolumeUnit.LITRE));

        // =====================================================
        // ADDITION TESTS
        // =====================================================

        System.out.println("\nAddition");

        System.out.println(
                litre.add(ml));

        System.out.println(
                litre.add(ml, VolumeUnit.MILLILITRE));

        // =====================================================
        // SUBTRACTION TESTS
        // =====================================================

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("\nSubtraction");

        System.out.println(
                feet.subtract(inches));

        System.out.println(
                feet.subtract(inches, LengthUnit.INCHES));

        // =====================================================
        // NEGATIVE RESULT
        // =====================================================

        Quantity<VolumeUnit> v1 =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        System.out.println("\nNegative Result");
        System.out.println(v1.subtract(v2));

        // =====================================================
        // ZERO RESULT
        // =====================================================

        Quantity<LengthUnit> f1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> i1 =
                new Quantity<>(120.0, LengthUnit.INCHES);

        System.out.println("\nZero Result");
        System.out.println(f1.subtract(i1));

        // =====================================================
        // DIVISION TESTS
        // =====================================================

        System.out.println("\nDivision");

        Quantity<WeightUnit> kg1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> kg2 =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        System.out.println(kg1.divide(kg2));

        // =====================================================
        // DIVISION CROSS UNIT
        // =====================================================

        Quantity<LengthUnit> i2 =
                new Quantity<>(24.0, LengthUnit.INCHES);

        Quantity<LengthUnit> f2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        System.out.println("\nCross Unit Division");

        System.out.println(i2.divide(f2));

        // =====================================================
        // LARGE VALUE TEST
        // =====================================================

        Quantity<WeightUnit> big1 =
                new Quantity<>(1e6, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> big2 =
                new Quantity<>(5e5, WeightUnit.KILOGRAM);

        System.out.println("\nLarge Value Test");

        System.out.println(big1.subtract(big2));

        // =====================================================
        // SMALL VALUE TEST
        // =====================================================

        Quantity<LengthUnit> small1 =
                new Quantity<>(0.001, LengthUnit.FEET);

        Quantity<LengthUnit> small2 =
                new Quantity<>(0.0005, LengthUnit.FEET);

        System.out.println("\nSmall Value Test");

        System.out.println(small1.subtract(small2));

        // =====================================================
        // CHAINED OPERATIONS
        // =====================================================

        System.out.println("\nChained Operations");

        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                        .subtract(new Quantity<>(1.0, LengthUnit.FEET));

        System.out.println(result);

        // =====================================================
        // DIVISION BY ZERO
        // =====================================================

        try {

            System.out.println("\nDivision By Zero");

            System.out.println(
                    feet.divide(
                            new Quantity<>(0.0, LengthUnit.FEET)
                    )
            );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        System.out.println("\nALL TEST CASES EXECUTED SUCCESSFULLY");
    }
}