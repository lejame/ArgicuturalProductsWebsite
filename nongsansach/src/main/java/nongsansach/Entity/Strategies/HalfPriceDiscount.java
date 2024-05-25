package nongsansach.Entity.Strategies;

public class HalfPriceDiscount implements IStrategyDiscount {
    @Override
    public double applyDiscount(double price) {
        return price * 0.5;
    }
}
