package nongsansach.service.Imp;

import nongsansach.Entity.ProductEntity;
import nongsansach.payload.request.InsertProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServiceImp {
    void save_product(ProductEntity productEntity);
    ProductEntity insertProduct(MultipartFile[] files, InsertProductRequest insertProductRequest);

    List<ProductEntity> get_all_product();

    ProductEntity find_product_name(String name_product);

    ProductEntity find_product_id(int id);

    boolean delete_by_id(int id);
}
