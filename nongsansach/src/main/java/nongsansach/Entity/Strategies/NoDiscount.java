package nongsansach.Entity.Strategies;

public class NoDiscount implements IStrategyDiscount{
    @Override
    public double applyDiscount(double price) {
        return price;
    }
}
