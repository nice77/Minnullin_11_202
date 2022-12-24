package factoryMethod;

public abstract class Transport {
    private final String typeName;
    private final int cost;
    public Transport(String typeName, int cost) {
        this.typeName = typeName;
        this.cost = cost;
    }
    public String getType() {
        return this.typeName;
    }
    public int getCost() {
        return this.cost;
    }
    public String toString() {
        return this.typeName + " " + this.cost;
    }
}
