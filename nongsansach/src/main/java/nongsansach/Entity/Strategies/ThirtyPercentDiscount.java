package nongsansach.Entity.Strategies;

public class ThirtyPercentDiscount implements IStrategyDiscount {
    @Override
    public double applyDiscount(double price) {
        return price * 0.7;
    }
}
