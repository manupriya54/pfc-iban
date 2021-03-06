# Task for PFC: IBAN Validator

### IBAN Validation Rules

- Rules picked up from the [Wikipedia article](https://en.wikipedia.org/wiki/International_Bank_Account_Number#Validating_the_IBAN)
- Country codes and corresponding IBAN length in accordance to [IBAN website](https://www.iban.com/structure)

### Steps to run the server locally
- #### Prerequisites:
    - git
    - docker
- #### Steps:
    - Clone the git repo `git clone https://github.com/manupriya54/pfc-iban.git`
    - Go to project directory
    - Run `docker-compose build`
    - Run `docker-compose up`
    - The service is now running on your `localhost` at port `8080`
    
### Check IBAN Validity
- From a REST client or Curl, make a `GET` request to 
`http://localhost:8080/pfc/iban/{ibanNumber}/validate`

### Parsing the Response
- Response model - `test.manu.model.IBANValidationResponse`
- If IBAN is valid, expected response : `{"errorResponse":null,"valid":true}`
- If IBAN is invalid, expected response : `{"errorResponse":{"errorMessage":"{Error Message}","exception":{Exception, if any}},"valid":false}`

### Code Structure
- Application start - `test.manu.PFC`
- Resource - `test.manu.resource.IBANResource`
- Validation Logic - `test.manu.validator.IBANValidator`

