package validations;

import exceptions.BadFormatException;
import exceptions.BadMonthException;
import exceptions.BadSumException;
import exceptions.BadYearException;

public class UIValidations {
    public static void validateClient(String name, String address)throws BadFormatException {
        if(!name.equals("") && !address.equals("") && !name.equals(" ")){
            for(int i=0;i<name.length();i++){
                if((!Character.isUpperCase(name.charAt(i))) && (!Character.isLowerCase(name.charAt(i))) && (!Character.isSpaceChar(name.charAt(i)))){
                    String message = "Invalid character: " + name.charAt(i);
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
