package Enums;

public enum Checks {

    No_Empty_Null_Value("No empty/null values"),
    DUPLICATE_CHECK("Duplicate Check"),
    STARTS_WITH("Starts with"),
    ENDS_WITH("Ends with"),
    CONTAINS("Contains"),
    EQUALS("Equals"),
    EXISTS_IN_LIST("Exists in list"),
    ALL_UNIQUE_VALUES("All unique values"),
    LESS_THAN("Less than"),
    LESS_THAN_EQUALTO("Less than equal to"),
    GREATER_THAN("Greater than"),
    GREATER_THAN_EQUAL_TO("Greater than equal to"),
    MIN_LENGTH_VAL("Min length of value"),
    MAX_LENGTH_VAL("Max length of value"),
    CUSTOM_PATTERN("Custom Pattern"),
    INTEGER("Integer"),
    DECIMAL("Decimal"),
    TEXT("Text"),
    EMAIL("Email"),
    DATE("Date"),
    DATE_TIME("DateTime"),
    BOOLEAN("Boolean (True | False)"),
    Rulename("Rule-1");

    private final String check;

    Checks(String check) {
        this.check = check;
    }



    public  String getCheck() {
        return check;
    }
}
