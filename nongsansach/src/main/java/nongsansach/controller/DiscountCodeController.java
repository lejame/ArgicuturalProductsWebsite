package nongsansach.controller;

import nongsansach.Entity.DiscountCodesEntity;
import nongsansach.Entity.SimpleFactory.DiscountFactory;
import nongsansach.Entity.Strategies.IStrategyDiscount;
import nongsansach.dto.DiscountCodeDTO;
import nongsansach.payload.request.DiscountCodeRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.service.Imp.DiscountCodeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
@CrossOrigin("*")
public class DiscountCodeController {

    @Autowired
    private DiscountCodeServiceImp discountCodeServiceImp;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("")
    public ResponseEntity<?> add_entity(DiscountCodeRequest discountCodeRequest){
        BaseResponse baseResponse = new BaseResponse();
        try{
            DiscountCodesEntity discountCodesEntityCheck = discountCodeServiceImp.get_discount_by_name(discountCodeRequest.getName());
            if(discountCodesEntityCheck.isStatus()){
                DiscountCodesEntity discountCodesEntity = new DiscountCodesEntity();

                discountCodesEntity.setName(discountCodeRequest.getName());
                discountCodesEntity.setLow_price(discountCodeRequest.getLow_price());
                discountCodesEntity.setExpiration_date(discountCodeRequest.getExpiration_date());
                discountCodeServiceImp.save_entity(discountCodesEntity);
                baseResponse.setData(discountCodesEntity);
                baseResponse.setMessage("Lưu thành công");
            }
            else {
                baseResponse.setMessage("Mã đã tồn tại");
            }

        }catch (Exception e){
            baseResponse.setMessage("Issue:"+e.getMessage());
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/{name}/{price}")
    public ResponseEntity<?> get_discountCode_id(@PathVariable String name,@PathVariable double price){
        BaseResponse baseResponse = new BaseResponse();
        try {
            DiscountCodesEntity discountCodesEntity = discountCodeServiceImp.get_discount_by_name(name);
            if(discountCodesEntity != null){
                DiscountFactory factory  = new DiscountFactory();
                IStrategyDiscount iStrategyDiscount = factory.createDiscount(discountCodesEntity.getLow_price());
                double total_price_discount = iStrategyDiscount.applyDiscount(price);
                baseResponse.setData(total_price_discount);
                baseResponse.setMessage("Giảm thành công");
                baseResponse.setStatusCode(200);
            }
            else {
                baseResponse.setMessage("Mã giảm không tồn tại");
                baseResponse.setStatusCode(200);
            }

        }catch (Exception e){
            baseResponse.setMessage("Issue:"+e.getMessage());
            baseResponse.setStatusCode(400);
        }
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
