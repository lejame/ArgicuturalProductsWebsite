package nongsansach.Entity.SimpleFactory;

import nongsansach.Entity.Strategies.*;

public class DiscountFactory {
    public IStrategyDiscount createDiscount(int code){
        IStrategyDiscount iStrategyDiscount = null;
        if(code == 4){
            iStrategyDiscount = new HalfPriceDiscount();
        } else if(code == 1){
            iStrategyDiscount = new TenPercentDiscount();
        } else if (code == 2) {
            iStrategyDiscount = new TwentyPercentDiscount();
        } else if (code == 3) {
            iStrategyDiscount = new ThirtyPercentDiscount();
        } else{
            iStrategyDiscount = new NoDiscount();
        }
        return iStrategyDiscount;
    }
}
