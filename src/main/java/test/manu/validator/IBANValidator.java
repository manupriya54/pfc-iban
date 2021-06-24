package test.manu.validator;

import test.manu.exception.ValidationException;
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
        try {
            validateFormat(ibanNumber);
            String countryCode = ibanNumber.substring(0, 2);
            validateCountry(countryCode);
            validateLength(ibanNumber, Integer.parseInt((String) countryIbanLength.get(countryCode)));
            validateCheckDigit(ibanNumber);
            return new IBANValidationResponse();
        } catch (ValidationException validationException) {
            return new IBANValidationResponse(new ErrorResponse(validationException.getMessage()));
        }
    }

    private void validateFormat(String iban) throws ValidationException {
        if (iban.length() < 2 )
            throw new ValidationException("IBAN Length incorrect");
        if(!iban.matches("[A-Za-z0-9]*"))
            throw new ValidationException("IBAN cannot contain special characters");
    }

    private void validateCountry(String countryCode) throws ValidationException {
        if (!countryIbanLength.containsKey(countryCode))
            throw new ValidationException("Country Code not valid");
    }

    private void validateLength(String iban, int expectedLength) throws ValidationException {
        if (iban.length() != expectedLength)
            throw new ValidationException("IBAN Length incorrect");
    }

    private void validateCheckDigit(String iban) throws ValidationException {
        iban = iban.substring(4) + iban.substring(0, 4);
        String expandedIBAN = getExpandedIBAN(iban);
        if (modulo97(expandedIBAN) != 1) {
            throw new ValidationException("IBAN failed check digit test");
        }
    }

    private int modulo97(String expandedIBAN) {
        int i = 0, mod = 0;
        while(i < expandedIBAN.length()) {
            int j = Math.min(expandedIBAN.length(), i == 0 ? i+9 : i+7);
            mod = Integer.parseInt(mod +expandedIBAN.substring(i, j)) % 97;
            i = j;
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
