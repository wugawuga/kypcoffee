package team.kyp.kypcoffee.exception;

import lombok.Getter;
import team.kyp.kypcoffee.enums.ExceptionEnum;

@Getter
public class ApiException extends RuntimeException{

    private ExceptionEnum error;

    public ApiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}