
public class DS8_TerrainCost {
    private int cost;
    private char type;

    public DS8_TerrainCost(char type, int cost)
    {
        this.type = type;
        this.cost = cost;
    }

    public int getCost()
    {
        return cost;
    }

    public char getType()
    {
        return type;
    }

    public String toString()
    {
        return type+""+cost;
    }
}
