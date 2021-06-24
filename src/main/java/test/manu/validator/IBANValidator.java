package test.manu.validator;

import test.manu.model.ErrorResponse;
import test.manu.model.IBANValidationResponse;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Properties;

public class IBANValidator {

    private final Properties countryIbanLength;

    @Inject
    public IBANValidator(@Named("countryIbanLength") Properties countryIbanLength) {
        this.countryIbanLength = countryIbanLength;
    }

    public IBANValidationResponse validate(String ibanNumber) {
        if (ibanNumber.length() > 2 && countryIbanLength.containsKey(ibanNumber.substring(0, 2))) {
            int length = Integer.parseInt((String)countryIbanLength.get(ibanNumber.substring(0, 2)));
            if (ibanNumber.length() == length) {
                if (ibanNumber.matches("[A-Za-z0-9]*")) {
                    ibanNumber = ibanNumber.substring(4) + ibanNumber.substring(0, 4);
                    String expandedIBAN = getExpandedIBAN(ibanNumber);
                    if (mod97(expandedIBAN) == 1) {
                        return new IBANValidationResponse();
                    }
                    return new IBANValidationResponse(new ErrorResponse("IBAN failed check digit test"));
                }
                return new IBANValidationResponse(new ErrorResponse("IBAN cannot contain special characters"));
            }
            return new IBANValidationResponse(new ErrorResponse("IBAN Length incorrect"));
        }
        return new IBANValidationResponse(new ErrorResponse("Country Code not valid"));
    }

    private int mod97(String expandedIBAN) {
        int mod = 0;
        for (char ch : expandedIBAN.toCharArray()) {
            mod = (mod * 10 + (int) ch - '0') % 97;
        }
        return mod;
    }

    private String getExpandedIBAN(String ibanNumber) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ibanNumber.length(); i++) {
            char ch = ibanNumber.charAt(i);
            if (Character.isLetter(ch)) {
                sb.append((10 + Character.toUpperCase(ch) - 'A'));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
