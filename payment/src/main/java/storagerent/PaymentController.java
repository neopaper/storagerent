package storagerent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class PaymentController {
    @Autowired 
    PaymentRepository paymentRepository;

    @RequestMapping(value = "/check/approvePayment",
                    method = RequestMethod.POST,
                    produces = "application/json;charset=UTF-8")
    public long approvePayment(HttpServletRequest request, HttpServletResponse response){
         Payment payment = new Payment();
         payment.setStorageId(Long.parseLong(request.getParameter("storageId")));
         payment.setReservationId(Long.parseLong(request.getParameter("reservationId")));
         payment.setAmount(Float.parseFloat(request.getParameter("amount")));
         payment.setPaymentStatus(request.getParameter("paymentStatus"));
         
         long retval = -1;
         
         try{
             retval = paymentRepository.save(payment).getPaymentId();
         }catch(Exception e){
         }

         return retval;
    }
    
}
