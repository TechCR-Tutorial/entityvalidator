package entityvalidator.type;

import org.junit.Assert;
import org.junit.Test;

public class LengthCriteriaTypeTest {

    @Test
    public void isValid() throws Exception {

        int thresholdLength = 2;

        LengthCriteriaType criteriaType = LengthCriteriaType.EQAUL;
        boolean isValid = criteriaType.isValid(thresholdLength, 2);
        Assert.assertTrue(isValid);
        isValid = criteriaType.isValid(thresholdLength, 1);
        Assert.assertFalse(isValid);

        criteriaType = LengthCriteriaType.LESS_THAN_OR_EQUAL;
        isValid = criteriaType.isValid(thresholdLength, 2);
        Assert.assertTrue(isValid);
        isValid = criteriaType.isValid(thresholdLength, 1);
        Assert.assertTrue(isValid);
        isValid = criteriaType.isValid(thresholdLength, 3);
        Assert.assertFalse(isValid);

        criteriaType = LengthCriteriaType.LESS_THAN;
        isValid = criteriaType.isValid(thresholdLength, 2);
        Assert.assertFalse(isValid);
        isValid = criteriaType.isValid(thresholdLength, 1);
        Assert.assertTrue(isValid);

        criteriaType = LengthCriteriaType.GREATER_THAN_OR_EQUAL;
        isValid = criteriaType.isValid(thresholdLength, 2);
        Assert.assertTrue(isValid);
        isValid = criteriaType.isValid(thresholdLength, 3);
        Assert.assertTrue(isValid);
        isValid = criteriaType.isValid(thresholdLength, 1);
        Assert.assertFalse(isValid);


        criteriaType = LengthCriteriaType.GREATER_THAN;
        isValid = criteriaType.isValid(thresholdLength, 2);
        Assert.assertFalse(isValid);
        isValid = criteriaType.isValid(thresholdLength, 3);
        Assert.assertTrue(isValid);

    }
}