
public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        QuantityWeight w1 =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        if (w1.equals(w2)) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }
    }
}