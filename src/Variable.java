public class Variable {
    enum Type {
        INT,
        STR //unused at the moment
        //might want to add more later
    }

    private Type type;

    private int intValue;

    private String strValue;

    public Variable() {

    }

    public Variable(int intValue) {
        this.type = Type.INT;
        this.intValue = intValue;

        //unused str
        this.strValue = "NOT_A_STR";
    }

    public Variable(String strValue) {
        this.type = Type.STR;
        this.strValue = strValue;

        //unused int
        this.intValue = -2147483648;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public String getValue() {
        if (this.type == Type.INT) {
            return String.valueOf(getIntValue());
        } else if (this.type == Type.STR) {
            return getStrValue();
        } else {
            return null;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "type=" + type +
                ", intValue=" + intValue +
                ", strValue='" + strValue + '\'' +
                '}';
    }
}
