package nongsansach.service.Imp;

import nongsansach.Entity.CategoryEntity;
import nongsansach.dto.CategoryDTO;

import java.util.List;

public interface CategoryServiceImp {
    CategoryEntity find_by_name(String name);
    boolean check_category(String name);

    void save_category(CategoryEntity categoryEntity);

    List<CategoryDTO> getAllCategory();
}
