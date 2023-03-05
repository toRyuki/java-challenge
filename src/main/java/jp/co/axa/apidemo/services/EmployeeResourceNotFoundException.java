package jp.co.axa.apidemo.services;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeResourceNotFoundException extends RuntimeException{
    public EmployeeResourceNotFoundException(Long employeeId){
        super("Employee is not found (id = "+ employeeId+")");
    }
}
