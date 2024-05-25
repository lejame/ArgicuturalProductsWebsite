package nongsansach.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import nongsansach.Entity.Builder.UserEntiryBuilder;
import nongsansach.Entity.Facade.Data.Update;
import nongsansach.Entity.Facade.UserFacade;
import nongsansach.Entity.OrderEntity;
import nongsansach.Entity.OrderProductEntity;
import nongsansach.Entity.TransactionHistoryEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.dto.AccountOrderDTO;
import nongsansach.dto.OrderDTO;
import nongsansach.dto.ProductDTO;
import nongsansach.dto.ProductOrderDTO;
import nongsansach.payload.request.OrderRequest;
import nongsansach.payload.request.UserRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.repository.OrderRepository;
import nongsansach.service.Imp.OrderProductServiceImp;
import nongsansach.service.Imp.RoleServiceImp;
import nongsansach.service.Imp.TransactionHistoryServiceImp;
import nongsansach.service.Imp.UserServiceImp;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleServiceImp roleServiceImp;

    @Autowired
    OrderProductServiceImp orderProductServiceImp;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TransactionHistoryServiceImp transactionHistoryServiceImp;

    @GetMapping()
    public ResponseEntity<?> getProfileUser(Authentication authentication) {
        BaseResponse baseResponse = new BaseResponse();

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    @PostMapping("/updateB")
    public ResponseEntity<?> updateUserAccount(@RequestPart("data") UserRequest userRequest) {
        System.out.print(userRequest.toString());
        UsersEntity usersEntity = userServiceImp.find_user_email(userRequest.getEmail());

        UsersEntity userEntityBuilder = new UserEntiryBuilder().buildAddress(userRequest.getAddress()).buildPhone(userRequest.getPhone()).buildPassword(userRequest.getPassword()).buildFullName(userRequest.getName()).build();
        usersEntity.setFullname(userEntityBuilder.getFullname());
        usersEntity.setAddress(userEntityBuilder.getAddress());
        usersEntity.setPhone(userEntityBuilder.getPhone());
        usersEntity.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        userServiceImp.saveUserEntity(usersEntity);
        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    @PostMapping("/updateF")
    public ResponseEntity<?> updateUser(@Valid UserRequest userRequest) {
        UserFacade userFacade = new UserFacade();
        Update update = new Update();
        update.setEmail(userRequest.getEmail());
        update.setUsername(userRequest.getName());
        update.setAddress(userRequest.getAddress());
        update.setPhone(userRequest.getPhone());
        update.setPassword(userRequest.getPassword());
        userFacade.UserFacadeActionUpdate(userServiceImp, passwordEncoder, update);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userFacade);
        baseResponse.setMessage("Thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/order/{email}")
    public ResponseEntity<?> get_order_of_user(@PathVariable String email) {
        UsersEntity usersEntity = userServiceImp.find_user_email(email);
        List<OrderEntity> orderEntity = orderRepository.findByUsersOrderEntity(usersEntity);
        List<AccountOrderDTO> accountOrderDTOS = new ArrayList<>();
        for (OrderEntity listUserOrder : orderEntity) {
            AccountOrderDTO accountOrderDTO = new AccountOrderDTO();
            TransactionHistoryEntity transactionHistoryEntity = transactionHistoryServiceImp.get_by_orderId(listUserOrder);
            accountOrderDTO.setTotal(String.valueOf(listUserOrder.getPrice()));
            accountOrderDTO.setOrderId(listUserOrder.getId());
            accountOrderDTO.setDate(transactionHistoryEntity.getPayDate().toString().split("T")[0]);
            accountOrderDTO.setStatus(transactionHistoryEntity.isStatus());
            accountOrderDTOS.add(accountOrderDTO);
        }

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(accountOrderDTOS);
        baseResponse.setMessage("Lấy dữ liệu thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/orderId/{id}")
    public ResponseEntity<?> get_order_by_id(@PathVariable("id") int id) {
        OrderEntity order = orderRepository.findById(id);
        List<ProductOrderDTO> need = new ArrayList<>();
        List<OrderProductEntity> orderProductEntities = orderProductServiceImp.get_all();
        for (OrderProductEntity orderProduct : orderProductEntities) {
            if (orderProduct.getOrder() == order) {
                ProductOrderDTO productDTO = new ProductOrderDTO();
                productDTO.setImages(orderProduct.getProduct().getImages());
                productDTO.setDescription(orderProduct.getProduct().getDescription());
                productDTO.setName(orderProduct.getProduct().getName());
                productDTO.setRate(orderProduct.getProduct().getRate());
                productDTO.setCategory(orderProduct.getProduct().getCategoryEntity().getName());
                productDTO.setPrice(orderProduct.getProduct().getPrice());
                productDTO.setQuantity(orderProduct.getProduct().getQuantity());
                productDTO.setOld_price(orderProduct.getProduct().getOld_price());
                productDTO.setQuantity_stock(orderProduct.getQuantity());
                need.add(productDTO);
            }
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(need);
        baseResponse.setMessage("Lấy thành công");
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
