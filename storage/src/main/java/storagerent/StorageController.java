package storagerent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class StorageController {
    @Autowired
    StorageRepository storageRepository;
    
    @RequestMapping(value = "/check/chkAndReqReserve",
                    method = RequestMethod.GET,
                    produces = "application/json;charset=UTF-8")

    public boolean chkAndReqReserve(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("##### /check/chkAndReqReserve  called #####");

        // Parameter로 받은 storageID 추출
        long storageId = Long.valueOf(request.getParameter("storageId"));
        System.out.println("######################## chkAndReqReserve storageId : " + storageId);

        // storageID 데이터 조회
        Optional<Storage> res = storageRepository.findById(storageId);
        Storage storage = res.get(); // 조회한 storage 데이터
        System.out.println("######################## chkAndReqReserve storage.getStatus() : " + storage.getStorageStatus());

        // storage의 상태가 available이면 true
        boolean result = false;
        if(storage.getStorageStatus().equals("available")) {
            result = true;
        } 

        System.out.println("######################## chkAndReqReserve Return : " + result);
        return result;
    }
 }
