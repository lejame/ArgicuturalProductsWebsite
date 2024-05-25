package nongsansach.Entity.State;

public class ProductService {
    ProductState productState;

    public void setState(ProductState state) {
        this.productState = state;
    }
    public String handleRequest(){
        String state = "";
        switch (productState){
            case STOCK:
                state =  "STOCK";
                break;
            default:
                state = "SOLDOUT";
                break;
        }
        return state;
    }
}
