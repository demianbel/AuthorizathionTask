package by.demianbel.DBService.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class StatusResponse {
    private long id;
    private String oldStatus;
    private String newStatus;
}
