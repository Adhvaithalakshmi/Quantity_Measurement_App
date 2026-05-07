// ==========================
// SIMPLE TEST CASE PROGRAM
// WITHOUT JUNIT
// ==========================

public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        // LENGTH TEST
        Quantity<LengthUnit> l1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Length Equality Test: "
                + l1.equals(l2));

        // WEIGHT TEST
        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality Test: "
                + w1.equals(w2));

        // VOLUME TEST
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0,
                        VolumeUnit.MILLILITRE);

        System.out.println("Volume Equality Test: "
                + v1.equals(v2));

        // CONVERSION TEST
        System.out.println(
                "1 Gallon to Litre: "
                        + new Quantity<>(1.0,
                        VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE)
        );

        // ADDITION TEST
        System.out.println(
                "Addition Test: "
                        + v1.add(v2, VolumeUnit.LITRE)
        );

        // CROSS CATEGORY TEST
        System.out.println(
                "Cross Category Test: "
                        + l1.equals(w1)
        );

        // NULL UNIT TEST
        try {
            new Quantity<>(1.0, null);
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Null Unit Test Passed"
            );
        }

        // INVALID VALUE TEST
        try {
            new Quantity<>(Double.NaN,
                    VolumeUnit.LITRE);
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Invalid Value Test Passed"
            );
        }
    }
}