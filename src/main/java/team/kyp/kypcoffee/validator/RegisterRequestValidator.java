package team.kyp.kypcoffee.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.kyp.kypcoffee.domain.RegisterRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterRequestValidator implements Validator {

    // 이메일 검증을 위한 정규식
    private static final String emailExp =
            "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

    private Pattern pattern;

    public RegisterRequestValidator() {
        pattern = Pattern.compile(emailExp);
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return RegisterRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RegisterRequest regReq = (RegisterRequest)target;

        if(regReq.getEmail()==null || regReq.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "required");
        }else {
            Matcher matcher = pattern.matcher(regReq.getEmail());
            if(!matcher.matches()) { //matches() 일치한다면 true, 일치하지 않는다면 false
                errors.rejectValue("email", "bad");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmpty(errors, "pw", "required");
        ValidationUtils.rejectIfEmpty(errors, "confirmPw", "required");

        if(!regReq.getPw().isEmpty()) {
            if(!regReq.isPasswordEqualToConfirmPassword()) {
                errors.rejectValue("confirmPw", "nomatch");
            }
        }



    }
}
