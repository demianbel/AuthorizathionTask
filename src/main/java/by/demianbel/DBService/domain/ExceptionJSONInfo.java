package by.demianbel.DBService.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

public class ExceptionJSONInfo {
    public ExceptionJSONInfo(String errorCode, String name, String message) {
        this.errorCode = errorCode;
        this.name = name;
        this.message = message;
    }
    String errorCode;
    String name;
    String message;
}
