package nongsansach.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import nongsansach.Entity.Data.ProductData;
import nongsansach.Entity.OrderEntity;
import nongsansach.Entity.OrderProductEntity;
import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.TransactionHistoryEntity;
import nongsansach.dto.OrderDTO;
import nongsansach.payload.request.OrderRequest;
import nongsansach.service.Imp.*;
import nongsansach.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/paypal")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PaypalController {

    @Autowired
    PaypalService service;

    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    OrderServiceImp orderServiceImp;

    @Autowired
    OrderProductServiceImp orderProductServiceImp;

    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    TransactionHistoryServiceImp transactionHistoryServiceImp;

    @PostMapping("")
    public ResponseEntity<?> payment(@RequestBody OrderRequest order) {
        try {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUsersOrderEntity(userServiceImp.find_user_email(order.getEmail()));
            orderEntity.setPrice(order.getPrice());
            OrderDTO orderDTO = orderServiceImp.save_order(orderEntity);

            for (ProductData product : order.getProduct()) {
                OrderProductEntity orderProductEntity = new OrderProductEntity();
                orderProductEntity.setOrder(orderServiceImp.get_order_by_Id(orderDTO.getId()));
                orderProductEntity.setProduct(productServiceImp.find_product_id(product.getId()));
                orderProductEntity.setQuantity(product.getSoluong());
                orderProductServiceImp.save(orderProductEntity);
            }

            Date currentDate = new Date();
            TransactionHistoryEntity transactionHistoryEntity = new TransactionHistoryEntity();
            transactionHistoryEntity.setOrder(orderEntity);
            transactionHistoryEntity.setPayDate(currentDate);
            transactionHistoryEntity.setPayMethod("paypal");
            transactionHistoryEntity.setEmail(order.getEmail());
            transactionHistoryEntity.setAddress(order.getAddress());
            transactionHistoryEntity.setTown(order.getTown());
            transactionHistoryEntity.setFirstName(order.getFirstName());
            transactionHistoryEntity.setLastName(order.getLastName());
            transactionHistoryEntity.setPhone(order.getPhone());

            TransactionHistoryEntity transaction = transactionHistoryServiceImp.save_entity(transactionHistoryEntity);
            System.out.print(transaction.getId());
            Payment payment = service.createPayment(order.getPrice(), transaction.getId());
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return new ResponseEntity<>(link.getHref(), HttpStatus.OK);
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return new ResponseEntity<>("Lỗi", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/payment/success")
    public ResponseEntity successUrl(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerID, @RequestParam("idTransaction") int id) {
        try {
            TransactionHistoryEntity transactionHistoryEntity = transactionHistoryServiceImp.get_by_id(id);
            transactionHistoryEntity.setStatus(true);
            transactionHistoryServiceImp.save_entity(transactionHistoryEntity);

            Payment payment = service.executePayment(paymentId, payerID);

            if (payment.getState().equals("approved")) {
                return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header("Location", "http://127.0.0.1:5500/checkout.html").build();

            } else {
                return ResponseEntity.ok("Payment not approved");
            }
        } catch (PayPalRESTException e) {
            System.err.println("Error during PayPal execution: " + e.getMessage());
            return ResponseEntity.ok("Error processing payment: " + e.getMessage());
        }
    }

    @GetMapping("/payment/cancel")
    public ResponseEntity cancelUrl(@RequestParam("idTransaction") int id) {
        TransactionHistoryEntity transactionHistoryEntity = transactionHistoryServiceImp.get_by_id(id);
        transactionHistoryServiceImp.delete_by_id(transactionHistoryEntity.getId());
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header("Location", "http://127.0.0.1:5500/checkout.html").build();
    }
}
