package nongsansach.Entity.Singleton;

import nongsansach.Entity.*;
import nongsansach.repository.BlogRepository;
import nongsansach.repository.OrderRepository;
import nongsansach.repository.UserRepository;
import nongsansach.service.Imp.OrderProductServiceImp;
import nongsansach.service.Imp.TransactionHistoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class RevenueManager {
    private volatile static RevenueManager instance;

    private RevenueManager() {
    }

    public static RevenueManager getInstance() {
        if (instance == null) {
            synchronized (RevenueManager.class) {
                if (instance == null)
                    instance = new RevenueManager();
            }
        }
        return instance;
    }

    public int getTotalUser(UserRepository usersRepository) {
        return usersRepository.findAll().size();
    }

    public double getRevenueByMonth(TransactionHistoryServiceImp transactionHistoryServiceImp) {

        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);
        List<TransactionHistoryEntity> transactionHistoryEntities = transactionHistoryServiceImp.get_all();
        double priceMonth = 0;
        for (TransactionHistoryEntity ts : transactionHistoryEntities) {
            Calendar payDateCalendar = Calendar.getInstance();
            payDateCalendar.setTime(ts.getPayDate());
            int orderMonth = payDateCalendar.get(Calendar.MONTH) + 1;
            int orderYear = payDateCalendar.get(Calendar.YEAR);
            if (orderMonth == currentMonth && orderYear == currentYear && ts.isStatus()) {
                priceMonth += ts.getOrder().getPrice();
            }
        }
        return priceMonth;
    }

    public int getTotalProduct(TransactionHistoryServiceImp transactionHistoryServiceImp, OrderProductServiceImp orderProductServiceImp) {
        int result = 0;

        List<TransactionHistoryEntity> transactionHistoryEntities = transactionHistoryServiceImp.get_all();
        for (TransactionHistoryEntity ts : transactionHistoryEntities) {

            if (ts.isStatus()) {
                OrderEntity productEntities =ts.getOrder();
                List<OrderProductEntity> orderProductEntities = orderProductServiceImp.get_all();
                for( OrderProductEntity ope:orderProductEntities){
                    if(ope.getOrder().equals(productEntities)){
                        result+=ope.getQuantity();
                    }
                }
            }
        }
        return result;
    }

    public int getTotalBlog(BlogRepository blogRepository) {
        return blogRepository.findAll().size();
    }
}
