package validations;

import exceptions.BadFormatException;
import exceptions.BadMonthException;
import exceptions.BadSumException;
import exceptions.BadYearException;

public class UIValidations {
    public static void validateClient(String name, String address)throws BadFormatException {
        if(!name.equals("") && !address.equals("")){
            // maximum 256 characters for name
            if (name.length()>256){
                String message = "Maximum 256 characters for name";
                throw new BadFormatException(message);
            }
            // permitted characters for name: space( separator), letters
            for(int i=0;i<name.length();i++) {
                if ((!Character.isUpperCase(name.charAt(i))) && (!Character.isLowerCase(name.charAt(i))) && (!Character.isSpaceChar(name.charAt(i)))) {
                    String message = "Invalid character: " + name.charAt(i);
                    throw new BadFormatException(message);
                }
            }
            // permitted characters for address: space( separator), letters, digits
            for(int i=0;i<address.length();i++){
                if((!Character.isDigit(address.charAt(i))) && (!Character.isUpperCase(address.charAt(i))) && (!Character.isLowerCase(address.charAt(i))) && (!Character.isSpaceChar(address.charAt(i)))){
                    String message = "Invalid character: " + address.charAt(i);
                    throw new BadFormatException(message);
                }
            }
        }else{
             throw new BadFormatException("Name or address cannot be empty!");
        }
    }

    public static void isValidYear(int year) throws BadYearException {
        if(year < 0) {
            throw new BadYearException("Year can't be 0 or less!");
        }
    }

    public static void isValidMonth(int month) throws BadMonthException {
        if (month < 0){
            throw new BadMonthException("Month can't be 0 or less!");
        }
    }

    public static void isValidSum(float sum) throws BadSumException {
        if (sum < 0){
            throw new BadSumException("Money to pay can't be less than 0!");
        }
    }
}
