package nongsansach.service.Imp;

import nongsansach.Entity.DiscountCodesEntity;

public interface DiscountCodeServiceImp {
    DiscountCodesEntity save_entity(DiscountCodesEntity discountCodesEntity);
    DiscountCodesEntity get_discountCode_by_id(int id);

    DiscountCodesEntity get_discount_by_name(String name);
}
