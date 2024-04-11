package nongsansach.service;

import nongsansach.Entity.DiscountCodesEntity;
import nongsansach.repository.DiscountCodeRepository;
import nongsansach.service.Imp.DiscountCodeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountCodeService implements DiscountCodeServiceImp {

    @Autowired
    DiscountCodeRepository discountCodeRepository;


    @Override
    public DiscountCodesEntity save_entity(DiscountCodesEntity discountCodesEntity) {
        return discountCodeRepository.save(discountCodesEntity);
    }

    @Override
    public DiscountCodesEntity get_discountCode_by_id(int id) {
        return discountCodeRepository.findById(id);
    }

    @Override
    public DiscountCodesEntity get_discount_by_name(String name) {
        return discountCodeRepository.findByName(name);
    }
}
