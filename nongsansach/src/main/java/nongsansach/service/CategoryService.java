package nongsansach.service;

import nongsansach.Entity.CategoryEntity;
import nongsansach.dto.CategoryDTO;
import nongsansach.dto.ProductDTO;
import nongsansach.repository.CategoryRepository;
import nongsansach.service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public CategoryEntity find_by_name(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public boolean check_category(String name) {
        return categoryRepository.findByName(name)==null?true:false;
    }

    @Override
    public void save_category(CategoryEntity categoryEntity) {
        categoryRepository.save(categoryEntity);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryEntity> list = categoryRepository.findAll();
        List<CategoryDTO> productDTOS = new ArrayList<>();
        for (CategoryEntity categoryEntity: list){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId_category(categoryEntity.getId());
            categoryDTO.setName(categoryEntity.getName());
            productDTOS.add(categoryDTO);
        }
        return productDTOS;
    }


}
