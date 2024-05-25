package nongsansach.Entity.Strategies;

public class TenPercentDiscount implements IStrategyDiscount {
    @Override
    public double applyDiscount(double price) {
        return price * 0.9;
    }
}
