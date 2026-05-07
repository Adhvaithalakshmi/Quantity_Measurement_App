// QuantityTest.java
// Plain Java test cases without JUnit

public class QuantityMeasurementAppTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        testAddition();
        testSubtraction();
        testDivision();
        testCrossUnitOperations();
        testNegativeValues();
        testZeroResult();
        testDivisionByZero();
        testNullOperand();
        testCrossCategory();
        testImmutability();

        System.out.println("\n==============================");
        System.out.println("Tests Passed : " + passed);
        System.out.println("Tests Failed : " + failed);
        System.out.println("==============================");
    }

    // ---------------- ASSERT METHODS ----------------

    private static void assertEquals(double expected, double actual, double epsilon, String testName) {

        if (Math.abs(expected - actual) <= epsilon) {
            System.out.println("PASS : " + testName);
            passed++;
        } else {
            System.out.println("FAIL : " + testName);
            System.out.println("Expected : " + expected);
            System.out.println("Actual   : " + actual);
            failed++;
        }
    }

    private static void assertTrue(boolean condition, String testName) {

        if (condition) {
            System.out.println("PASS : " + testName);
            passed++;
        } else {
            System.out.println("FAIL : " + testName);
            failed++;
        }
    }

    // ---------------- TEST CASES ----------------

    private static void testAddition() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(12.0, result.getValue(), 0.01,
                "Addition Same Unit");
    }

    private static void testSubtraction() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(5.0, result.getValue(), 0.01,
                "Subtraction Same Unit");
    }

    private static void testDivision() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(5.0, result, 0.01,
                "Division Same Unit");
    }

    private static void testCrossUnitOperations() {

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.subtract(inches);

        assertEquals(9.5, result.getValue(), 0.01,
                "Cross Unit Subtraction");
    }

    private static void testNegativeValues() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(-5.0, result.getValue(), 0.01,
                "Negative Result Subtraction");
    }

    private static void testZeroResult() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(120.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(0.0, result.getValue(), 0.01,
                "Zero Result Subtraction");
    }

    private static void testDivisionByZero() {

        try {

            Quantity<LengthUnit> q1 =
                    new Quantity<>(10.0, LengthUnit.FEET);

            Quantity<LengthUnit> q2 =
                    new Quantity<>(0.0, LengthUnit.FEET);

            q1.divide(q2);

            System.out.println("FAIL : Division By Zero");
            failed++;

        } catch (ArithmeticException e) {

            System.out.println("PASS : Division By Zero");
            passed++;
        }
    }

    private static void testNullOperand() {

        try {

            Quantity<LengthUnit> q1 =
                    new Quantity<>(10.0, LengthUnit.FEET);

            q1.subtract(null);

            System.out.println("FAIL : Null Operand");
            failed++;

        } catch (IllegalArgumentException e) {

            System.out.println("PASS : Null Operand");
            passed++;
        }
    }

    private static void testCrossCategory() {

        try {

            Quantity rawLength =
                    new Quantity<>(10.0, LengthUnit.FEET);

            Quantity rawWeight =
                    new Quantity<>(5.0, WeightUnit.KILOGRAM);

            rawLength.subtract(rawWeight);

            System.out.println("FAIL : Cross Category");
            failed++;

        } catch (IllegalArgumentException e) {

            System.out.println("PASS : Cross Category");
            passed++;
        }
    }

    private static void testImmutability() {

        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> other =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                original.subtract(other);

        boolean unchanged =
                original.getValue() == 10.0;

        assertTrue(unchanged,
                "Immutability Test");
    }
}