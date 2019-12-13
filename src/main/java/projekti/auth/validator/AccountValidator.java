package projekti.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import projekti.auth.model.Account;
import projekti.auth.service.AccountService;

@Component
public class AccountValidator implements Validator {
    
    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> classType) {
        return Account.class.equals(classType);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Account user = (Account) o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        
        if (accountService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "NotEmpty");
        if (user.getFullName().length() < 5 || user.getFullName().length() > 32) {
            errors.rejectValue("fullName", "Size.userForm.username");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "signature", "NotEmpty");
        if (user.getSignature().replaceAll("\\s", "").length() != 8) {
            errors.rejectValue("signature", "Size.userForm.signature");
        }
        
        if (accountService.findBySignature(user.getSignature()) != null) {
            errors.rejectValue("signature", "Duplicate.userForm.signature");
        }
    }
    
    
    
}
