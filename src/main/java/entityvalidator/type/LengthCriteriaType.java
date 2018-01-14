package entityvalidator.type;

public enum LengthCriteriaType {
    EQAUL {
        @Override
        public boolean isValid(Number thresholdLength, Number actualLength) {
            return thresholdLength.doubleValue() == actualLength.doubleValue();
        }
    },
    LESS_THAN_OR_EQUAL {
        @Override
        public boolean isValid(Number thresholdLength, Number actualLength) {
            return thresholdLength.doubleValue() >= actualLength.doubleValue();
        }
    },
    LESS_THAN {
        @Override
        public boolean isValid(Number thresholdLength, Number actualLength) {
            return thresholdLength.doubleValue() > actualLength.doubleValue();
        }
    },
    GREATER_THAN_OR_EQUAL {
        @Override
        public boolean isValid(Number thresholdLength, Number actualLength) {
            return thresholdLength.doubleValue() <= actualLength.doubleValue();
        }
    },
    GREATER_THAN {
        @Override
        public boolean isValid(Number thresholdLength, Number actualLength) {
            return thresholdLength.doubleValue() < actualLength.doubleValue();
        }
    };

    /**
     *
     * @param thresholdLength - constant
     * @param actualLength - actualLength
     * @return
     */
    public abstract boolean isValid(Number thresholdLength, Number actualLength);
}
