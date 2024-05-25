package nongsansach.controller;

import nongsansach.Entity.Singleton.RevenueManager;
import nongsansach.dto.RevenueValueDTO;
import nongsansach.payload.response.BaseResponse;
import nongsansach.repository.BlogRepository;
import nongsansach.repository.OrderRepository;
import nongsansach.repository.UserRepository;
import nongsansach.service.Imp.OrderProductServiceImp;
import nongsansach.service.Imp.TransactionHistoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class DashbroadController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    TransactionHistoryServiceImp transactionHistoryServiceImp;

    @Autowired
    OrderProductServiceImp orderProductServiceImp;
    @GetMapping()
    public ResponseEntity<?> getDashBroad() {
        BaseResponse baseResponse = new BaseResponse();
        RevenueManager revenueManager = RevenueManager.getInstance();

        RevenueValueDTO revenueValueDTO = new RevenueValueDTO();
        revenueValueDTO.setTotalUser(revenueManager.getTotalUser(userRepository));
        revenueValueDTO.setRevenueByMonth(revenueManager.getRevenueByMonth(transactionHistoryServiceImp));
        revenueValueDTO.setTotalProduct(revenueManager.getTotalProduct(transactionHistoryServiceImp,orderProductServiceImp));
        revenueValueDTO.setTotalBlog(revenueManager.getTotalBlog(blogRepository));
        baseResponse.setData(revenueValueDTO);
        baseResponse.setMessage("Lay du lieu thanh cong");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
