import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double toFeet(double value) {
        return value * factor;
    }

    public double fromFeet(double value) {
        return value / factor;
    }
}

class QuantityLength {

    double value;
    LengthUnit unit;

    public QuantityLength(double value,
                          LengthUnit unit) {

        this.value = value;
        this.unit = unit;
    }

    public QuantityLength add(QuantityLength other,
                              LengthUnit targetUnit) {

        double first =
                this.unit.toFeet(this.value);

        double second =
                other.unit.toFeet(other.value);

        double total = first + second;

        double result =
                targetUnit.fromFeet(total);

        return new QuantityLength(result,
                targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        QuantityLength other =
                (QuantityLength) obj;

        double first =
                this.unit.toFeet(this.value);

        double second =
                other.unit.toFeet(other.value);

        return Math.abs(first - second)
                < 0.0001;
    }
}

public class QuantityMeasurementAppTest {

    @Test
    void testAddition() {

        QuantityLength feet =
                new QuantityLength(1.0,
                        LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0,
                        LengthUnit.INCHES);

        QuantityLength expected =
                new QuantityLength(2.0,
                        LengthUnit.FEET);

        QuantityLength result =
                feet.add(inches,
                        LengthUnit.FEET);

        assertEquals(expected, result);
    }
}