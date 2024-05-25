package nongsansach.Entity.Strategies;

public class DiscountStrategy {
    private IStrategyDiscount strategyDiscount;
    public DiscountStrategy(){
        this.strategyDiscount = new NoDiscount();
    }
    public void setDiscount(IStrategyDiscount strategyDiscount){
        this.strategyDiscount = strategyDiscount;
    }
    public double doDiscount(double price){
        return this.strategyDiscount.applyDiscount(price);
    }
}
