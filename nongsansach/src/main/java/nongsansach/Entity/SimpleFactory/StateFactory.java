package nongsansach.Entity.SimpleFactory;

import nongsansach.Entity.State.ProductService;
import nongsansach.Entity.State.ProductState;
import nongsansach.Entity.Strategies.*;

public class StateFactory {
    public ProductService createState(int quantity){
        ProductService productService = new ProductService();
        if(quantity == 0){
            productService.setState(ProductState.SOLDOUT);
        } else {
            productService.setState(ProductState.STOCK);
        }
        return productService;
    }
}
