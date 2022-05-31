package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Email;

public class Validation {
	
	public static boolean validateEmail(String email){
        if(email == null || email.length() == 0){
                return false;
        }
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@([_A-Za-z0-9-\\\\+]+)+(.(\\w){2,3}){1,4}$");
        Matcher matcher = pattern.matcher(email);
        
        if(matcher.matches()){
            return true;
        }
        return false;
    }

	public static boolean validateEmailInformation(Email email) {
		boolean recipientIsValid = validateEmail(email.getRecipient());
		boolean subjectIsValid = !(email.getSubject()==null);
		boolean contentIsValid = !(email.getContent()==null);
		return recipientIsValid && subjectIsValid && contentIsValid;
	}
	
}
