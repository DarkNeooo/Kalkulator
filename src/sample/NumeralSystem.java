package sample;

public class NumeralSystem {

    private String name;
    private String pattern;
    private int base;

    public NumeralSystem(String name, String pattern, int base) {
        this.name = name;
        this.pattern = pattern;
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public String getPattern() {
        return pattern;
    }

    public int getBase() {
        return base;
    }
}
