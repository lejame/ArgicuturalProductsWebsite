package nongsansach.service;

import nongsansach.Entity.CategoryEntity;
import nongsansach.Entity.ProductEntity;
import nongsansach.payload.request.InsertProductRequest;
import nongsansach.repository.ProductRepository;
import nongsansach.service.Imp.CategoryServiceImp;
import nongsansach.service.Imp.FileServiceImp;
import nongsansach.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryServiceImp cateroryServiceImp;
    @Autowired
    FileServiceImp fileServiceImp;

    @Override
    public void save_product(ProductEntity productEntity) {

        productRepository.save(productEntity);
    }

    @Override
    public ProductEntity insertProduct(MultipartFile[] files,InsertProductRequest insertProductRequest) {
        ProductEntity productEntity = new ProductEntity();
        try {
            List<Map> result = fileServiceImp.save_file(files);

            List<String> list_file = new ArrayList<>();
            result.forEach((uploadedResult) -> {
                list_file.add(uploadedResult.get("secure_url").toString());
            });

            productEntity.setName(insertProductRequest.getProductName());
            productEntity.setPrice(insertProductRequest.getPrice());
            productEntity.setDescription(insertProductRequest.getDescription());

            CategoryEntity categoryEntity = cateroryServiceImp.find_by_name(insertProductRequest.getName_category());
            productEntity.setCategoryEntity(categoryEntity);

            productEntity.setImages(list_file.get(0) + "," + list_file.get(1));
            productEntity.setRate(insertProductRequest.getRate());
            productEntity.setQuantity(insertProductRequest.getQuantity());
            productEntity.setOld_price(insertProductRequest.getOld_price()!=0?insertProductRequest.getOld_price():null);

            save_product(productEntity);

        } catch (Exception e) {
            System.out.print("check issue: " + e.getMessage());
        }
        return productEntity;
    }

    @Override
    public List<ProductEntity> get_all_product() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity find_product_name(String name_product) {
        return productRepository.findByName(name_product);
    }

    @Override
    public ProductEntity find_product_id(int id) {

        return productRepository.findById(id);
    }

}
