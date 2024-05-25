package nongsansach.controller;

import nongsansach.Entity.TransactionHistoryEntity;
import nongsansach.dto.TransactionDTO;
import nongsansach.payload.response.BaseResponse;
import nongsansach.service.Imp.TransactionHistoryServiceImp;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/admin/transaction")
@CrossOrigin("*")
public class TransactionController {
    @Autowired
    TransactionHistoryServiceImp transactionHistoryServiceImp;

    @GetMapping()
    public ResponseEntity<?> get_all_transaction() {
        List<TransactionHistoryEntity> transactionHistoryEntities = transactionHistoryServiceImp.get_all();
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (TransactionHistoryEntity tran : transactionHistoryEntities) {
            if (tran.isStatus()) {
                TransactionDTO newTran = getTransactionDTO(tran);
                transactionDTOS.add(newTran);
            }
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Lấy dũ liệu thành công!");
        baseResponse.setData(transactionDTOS);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @NotNull
    private static TransactionDTO getTransactionDTO(TransactionHistoryEntity tran) {
        TransactionDTO newTran = new TransactionDTO();
        newTran.setStatus(tran.isStatus());
        newTran.setTown(tran.getTown());
        newTran.setPhone(tran.getPhone());
        newTran.setAddress(tran.getAddress());
        newTran.setPayMethod(tran.getPayMethod());
        newTran.setEmail(tran.getEmail());
        newTran.setOrderId(tran.getOrder().getId());
        newTran.setLastName(tran.getLastName());
        newTran.setFirstName(tran.getFirstName());
        newTran.setPayDate(tran.getPayDate());
        return newTran;
    }
}
