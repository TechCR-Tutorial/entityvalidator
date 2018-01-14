package entityvalidator.entity;

import java.util.List;
import java.util.Set;

import entityvalidator.type.LengthCriteriaType;
import entityvalidator.type.NumberEqualValidator;
import entityvalidator.type.notation.ArrayType;
import entityvalidator.type.notation.CollectionType;
import entityvalidator.type.notation.ConditionRule;
import entityvalidator.type.notation.ConditionValidation;
import entityvalidator.type.notation.Constant;
import entityvalidator.type.notation.CustomCollectionType;
import entityvalidator.type.notation.EntityField;
import entityvalidator.type.notation.Length;
import entityvalidator.type.notation.LengthBetween;
import entityvalidator.type.notation.Mandatory;
import entityvalidator.type.notation.NumberFormat;
import entityvalidator.type.notation.Range;
import entityvalidator.type.notation.Regex;
import entityvalidator.type.notation.ValidatorFieldDescription;

@ConditionValidation(
        rules = {@ConditionRule(rule = "conditionValues != null", errorDesc = "Condition value Should not be empty")},
        errorDesc = "Condition Value failed"
)
public class FieldTest {

    @ValidatorFieldDescription(fieldDesc = "Name")
    @Mandatory()
    private String name;
    @Mandatory(errorDesc = "Address Mandatory")
    private String address;

    @ValidatorFieldDescription(fieldDesc = "Equal Prop")
    @Length(length = 2)
    private String equalProperty;
    @Length(length = 2)
    private long longProperty;
    @Length(length = 4, lengthCriteriaType = LengthCriteriaType.GREATER_THAN_OR_EQUAL)
    private String gtoqProperty;
    @Length(length = 4, lengthCriteriaType = LengthCriteriaType.GREATER_THAN)
    private String gtProperty;
    @Length(length = 4, lengthCriteriaType = LengthCriteriaType.LESS_THAN_OR_EQUAL)
    private String ltoqProperty;
    @Length(length = 4, lengthCriteriaType = LengthCriteriaType.LESS_THAN)
    private String ltProperty;
    @Length(length = 6)
    private Double doubleValiateProperty;

    @ValidatorFieldDescription(fieldDesc = "Average")
    @NumberFormat(numberFormat = "##.###", lengthCriteria = LengthCriteriaType.EQAUL)
    private Number averageScore;

    @ValidatorFieldDescription(fieldDesc = "Between length")
    @LengthBetween(minLength = 5, maxLength = 10, minLengthCriteria = LengthCriteriaType.GREATER_THAN_OR_EQUAL,
            maxLengthCriteria = LengthCriteriaType.LESS_THAN_OR_EQUAL)
    private String lenthPropery;
    @LengthBetween(minLength = 5, maxLength = 10, minLengthCriteria = LengthCriteriaType.GREATER_THAN_OR_EQUAL,
            maxLengthCriteria = LengthCriteriaType.LESS_THAN_OR_EQUAL)
    private Long numberLengthProperty;

    @ValidatorFieldDescription(fieldDesc = "Number Constant")
    @Constant(value = "3.14", errorDesc = "")
    private String doubleNumber;
    @Constant(value = "123", equalValidator = NumberEqualValidator.class)
    private Long longNumber;

    @ValidatorFieldDescription(fieldDesc = "Range")
    @Range(minValue = "10", maxValue = "100")
    private Long longRangeValue;
    @Range(minValue = "3.25", maxValue = "10.50")
    private Double doubleRangeValue;

    @ValidatorFieldDescription(fieldDesc = "Numeric")
    private String numericValue;

    @EntityField
    private User user;

    @ValidatorFieldDescription(fieldDesc = "Regex")
    @Regex(regex = "^[0-9]*$")
    private Long posNumberRegex;
    @Regex(regex = "^-[0-9]*$")
    private Long negNumberRegex;
    @Regex(regex = "^[1-9][0-9]*$")
    private Long nonZeroPosNumberRegex;
    @Regex(regex = "[a-zA-Z]+")
    private String stringRegex;
    @Regex(regex = ".+")
    private String anyStringRegex;

    @ValidatorFieldDescription(fieldDesc = "Condition")
    private String conditionValues;
    //@ConditionValidation(rules = "GY04=true,GY05=true,GY06=false,GY07=false")
    //@ConditionValidation(rules = "GY04 && GY05 && !GY06 && !GY07")

    @ConditionValidation(
            rules = {@ConditionRule(rule = "conditionDepenField != null && conditionValues == null", errorDesc = "Condition 1 ")},
            errorDesc = "Condition 1 "
    )
    private String conditionValues2;
    private String conditionDepenField;
    @ConditionValidation(
            rules = {@ConditionRule(rule = "if (conditionValueCheck == 1 ) {conditionValues == null;} else { true; }", errorDesc = "Condition Equal Check 1 ")
                    ,@ConditionRule(rule = "if (conditionValueCheck == 2 ) {conditionDepenField != null && conditionValues == null;} else {true;}",
                    errorDesc = "Condition Equal Check 2 ")
            },
            objectAlias = "t",
            errorDesc = "Condition 1 "
    )
    private int conditionValueCheck;

    @ArrayType(minLength = 0, maxLength = 1)
    private User[] usersArrayWithoutInternalValidation;
    @ArrayType(minLength = 0, maxLength = 1, validateInnerEntity = true)
    private User[] usersArrayWithInternalValidation;

    @CollectionType(minLength = 0, maxLength = 1)
    private List<User> usersListWithoutInternalValidation;
    @CollectionType(minLength = 0, maxLength = 1, validateInnerEntity = true)
    private List<User> usersListWithInternalValidation;

    @CollectionType(minLength = 0, maxLength = 1)
    private Set<User> usersSetWithoutInternalValidation;
    @CollectionType(minLength = 0, maxLength = 1, validateInnerEntity = true)
    private Set<User> usersSetWithInternalValidation;

    @CustomCollectionType(minLength = 0, maxLength = 1, customCollectionClass = UserList.class)
    private UserList customListWithoutInternalValidation;
    @CustomCollectionType(minLength = 0, maxLength = 1, customCollectionClass = UserList.class, validateInnerEntity = true)
    private UserList customListWithInternalValidation;



    //Getters Setters

    public UserList getCustomListWithoutInternalValidation() {
        return customListWithoutInternalValidation;
    }

    public void setCustomListWithoutInternalValidation(UserList customListWithoutInternalValidation) {
        this.customListWithoutInternalValidation = customListWithoutInternalValidation;
    }

    public UserList getCustomListWithInternalValidation() {
        return customListWithInternalValidation;
    }

    public void setCustomListWithInternalValidation(UserList customListWithInternalValidation) {
        this.customListWithInternalValidation = customListWithInternalValidation;
    }

    public Set<User> getUsersSetWithoutInternalValidation() {
        return usersSetWithoutInternalValidation;
    }

    public void setUsersSetWithoutInternalValidation(Set<User> usersSetWithoutInternalValidation) {
        this.usersSetWithoutInternalValidation = usersSetWithoutInternalValidation;
    }

    public Set<User> getUsersSetWithInternalValidation() {
        return usersSetWithInternalValidation;
    }

    public void setUsersSetWithInternalValidation(Set<User> usersSetWithInternalValidation) {
        this.usersSetWithInternalValidation = usersSetWithInternalValidation;
    }

    public List<User> getUsersListWithoutInternalValidation() {
        return usersListWithoutInternalValidation;
    }

    public void setUsersListWithoutInternalValidation(List<User> usersListWithoutInternalValidation) {
        this.usersListWithoutInternalValidation = usersListWithoutInternalValidation;
    }

    public List<User> getUsersListWithInternalValidation() {
        return usersListWithInternalValidation;
    }

    public void setUsersListWithInternalValidation(List<User> usersListWithInternalValidation) {
        this.usersListWithInternalValidation = usersListWithInternalValidation;
    }

    public User[] getUsersArrayWithoutInternalValidation() {
        return usersArrayWithoutInternalValidation;
    }

    public void setUsersArrayWithoutInternalValidation(User[] usersArrayWithoutInternalValidation) {
        this.usersArrayWithoutInternalValidation = usersArrayWithoutInternalValidation;
    }

    public User[] getUsersArrayWithInternalValidation() {
        return usersArrayWithInternalValidation;
    }

    public void setUsersArrayWithInternalValidation(User[] usersArrayWithInternalValidation) {
        this.usersArrayWithInternalValidation = usersArrayWithInternalValidation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEqualProperty() {
        return equalProperty;
    }

    public void setEqualProperty(String equalProperty) {
        this.equalProperty = equalProperty;
    }

    public long getLongProperty() {
        return longProperty;
    }

    public void setLongProperty(long longProperty) {
        this.longProperty = longProperty;
    }

    public String getGtoqProperty() {
        return gtoqProperty;
    }

    public void setGtoqProperty(String gtoqProperty) {
        this.gtoqProperty = gtoqProperty;
    }

    public String getGtProperty() {
        return gtProperty;
    }

    public void setGtProperty(String gtProperty) {
        this.gtProperty = gtProperty;
    }

    public String getLtoqProperty() {
        return ltoqProperty;
    }

    public void setLtoqProperty(String ltoqProperty) {
        this.ltoqProperty = ltoqProperty;
    }

    public String getLtProperty() {
        return ltProperty;
    }

    public void setLtProperty(String ltProperty) {
        this.ltProperty = ltProperty;
    }

    public Double getDoubleValiateProperty() {
        return doubleValiateProperty;
    }

    public void setDoubleValiateProperty(Double doubleValiateProperty) {
        this.doubleValiateProperty = doubleValiateProperty;
    }

    public Number getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Number averageScore) {
        this.averageScore = averageScore;
    }

    public String getLenthPropery() {
        return lenthPropery;
    }

    public void setLenthPropery(String lenthPropery) {
        this.lenthPropery = lenthPropery;
    }

    public Long getNumberLengthProperty() {
        return numberLengthProperty;
    }

    public void setNumberLengthProperty(Long numberLengthProperty) {
        this.numberLengthProperty = numberLengthProperty;
    }

    public String getDoubleNumber() {
        return doubleNumber;
    }

    public void setDoubleNumber(String doubleNumber) {
        this.doubleNumber = doubleNumber;
    }

    public Long getLongNumber() {
        return longNumber;
    }

    public void setLongNumber(Long longNumber) {
        this.longNumber = longNumber;
    }

    public Long getLongRangeValue() {
        return longRangeValue;
    }

    public void setLongRangeValue(Long longRangeValue) {
        this.longRangeValue = longRangeValue;
    }

    public Double getDoubleRangeValue() {
        return doubleRangeValue;
    }

    public void setDoubleRangeValue(Double doubleRangeValue) {
        this.doubleRangeValue = doubleRangeValue;
    }

    public String getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(String numericValue) {
        this.numericValue = numericValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPosNumberRegex() {
        return posNumberRegex;
    }

    public void setPosNumberRegex(Long posNumberRegex) {
        this.posNumberRegex = posNumberRegex;
    }

    public Long getNegNumberRegex() {
        return negNumberRegex;
    }

    public void setNegNumberRegex(Long negNumberRegex) {
        this.negNumberRegex = negNumberRegex;
    }

    public Long getNonZeroPosNumberRegex() {
        return nonZeroPosNumberRegex;
    }

    public void setNonZeroPosNumberRegex(Long nonZeroPosNumberRegex) {
        this.nonZeroPosNumberRegex = nonZeroPosNumberRegex;
    }

    public String getStringRegex() {
        return stringRegex;
    }

    public void setStringRegex(String stringRegex) {
        this.stringRegex = stringRegex;
    }

    public String getAnyStringRegex() {
        return anyStringRegex;
    }

    public void setAnyStringRegex(String anyStringRegex) {
        this.anyStringRegex = anyStringRegex;
    }

    public String getConditionValues() {
        return conditionValues;
    }

    public void setConditionValues(String conditionValues) {
        this.conditionValues = conditionValues;
    }

    public String getConditionValues2() {
        return conditionValues2;
    }

    public void setConditionValues2(String conditionValues2) {
        this.conditionValues2 = conditionValues2;
    }

    public String getConditionDepenField() {
        return conditionDepenField;
    }

    public void setConditionDepenField(String conditionDepenField) {
        this.conditionDepenField = conditionDepenField;
    }

    public int getConditionValueCheck() {
        return conditionValueCheck;
    }

    public void setConditionValueCheck(int conditionValueCheck) {
        this.conditionValueCheck = conditionValueCheck;
    }
}
