package test.manu.validator;

import org.junit.Before;
import org.junit.Test;
import test.manu.model.IBANValidationResponse;

import java.util.Properties;

import static org.junit.Assert.*;

public class IBANValidatorTest {

    Properties mockData = new Properties();
    IBANValidator validator = new IBANValidator(mockData);

    @Before
    public void setUp() {
        mockData.put("SE","24");
        mockData.put("DE","22");
        mockData.put("GB","22");
    }

    @Test
    public void testValidIBAN() {
        assertTrue(validator.validate("GB33BUKB20201555555555").isValid());
    }

    @Test
    public void testUnlistedCountryIBAN() {
        IBANValidationResponse response = validator.validate("MK07200002785123453");
        assertFalse(response.isValid());
        assertEquals(response.getErrorResponse().getErrorMessage(), "Country Code not valid");
    }

    @Test
    public void testWrongLengthIBAN() {
        IBANValidationResponse response = validator.validate("SE72800008103400097832");
        assertFalse(response.isValid());
        assertEquals(response.getErrorResponse().getErrorMessage(), "IBAN Length incorrect");
    }

    @Test
    public void testEmptyIBAN() {
        IBANValidationResponse response = validator.validate("");
        assertFalse(response.isValid());
        assertEquals(response.getErrorResponse().getErrorMessage(), "IBAN Length incorrect");
    }

    @Test
    public void testIBANWithSpecialCharacters() {
        IBANValidationResponse response = validator.validate("DE755121080012451$%199");
        assertFalse(response.isValid());
        assertEquals(response.getErrorResponse().getErrorMessage(), "IBAN cannot contain special characters");
    }

    @Test
    public void testWrongCheckDigitIBAN() {
        IBANValidationResponse response = validator.validate("SE7280000810340009783345");
        assertFalse(response.isValid());
        assertEquals(response.getErrorResponse().getErrorMessage(), "IBAN failed check digit test");
    }

    @Test(expected = NullPointerException.class)
    public void testNullIBAN() {
        validator.validate(null);
    }
}