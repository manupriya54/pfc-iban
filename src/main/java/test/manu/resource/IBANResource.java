package test.manu.resource;

import com.google.inject.Inject;
import test.manu.model.IBANValidationResponse;
import test.manu.validator.IBANValidator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pfc/iban")
@Produces(MediaType.APPLICATION_JSON)
public class IBANResource {

    private final IBANValidator validator;

    @Inject
    public IBANResource(IBANValidator validator) {
        this.validator = validator;
    }

    @GET
    @Path("/{ibanNumber}/validate")
    public IBANValidationResponse sayHello(@PathParam("ibanNumber") String ibanNumber) {
        return validator.validate(ibanNumber);
    }
}
