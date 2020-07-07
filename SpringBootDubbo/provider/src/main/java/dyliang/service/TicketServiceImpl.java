package dyliang.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service
public class TicketServiceImpl implements TicketService {

    @Override
    public String getTicket() {
        return "Harry Porter";
    }
}
