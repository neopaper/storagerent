
package storagerent.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="storage", url="${prop.storage.url}")
public interface StorageService {

    @RequestMapping(method= RequestMethod.GET, path="/storages")
    public boolean chkAndReqReserve(@RequestBody Storage storage);

}