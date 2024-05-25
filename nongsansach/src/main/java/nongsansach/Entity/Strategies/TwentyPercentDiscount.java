package nongsansach.Entity.Strategies;

public class TwentyPercentDiscount implements IStrategyDiscount {
    @Override
    public double applyDiscount(double price) {
        return price * 0.8;
    }
}
