package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.dto.SimCardQueryResponse;
import au.com.telstra.simcardactivator.models.SimCard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    private static final String ACTIVATION_ENDPOINT = "http://localhost:8080/activate-sim";
    private static final String QUERY_ENDPOINT = "http://localhost:8080/query-sim?simCardId={simCardId}";
    private SimCard simCard;

    @Given("a valid iccid")
    public void aValidIccid() {
        simCard = new SimCard("1255789453849037777", "abc@xyz.mnq");
    }

    @Given("an invalid iccid")
    public void anInvalidIccid() {
        simCard = new SimCard("8944500102198304826", "abc@xyz.mnq");
    }

    @When("request sim activation")
    public void requestSimActivation() {
        restTemplate.postForObject(ACTIVATION_ENDPOINT, simCard, Long.class);
    }

    @Then("the activation is succesful and recorded to database")
    public void theActivationIsSuccesfulAndRecordedToDatabase() {
        SimCardQueryResponse response = restTemplate.getForObject(QUERY_ENDPOINT, SimCardQueryResponse.class, 1);
        assertTrue(response.getActive());
    }
    
    @Then("the activation fails and recorded to database")
    public void theActivationFailsAndRecordedToDatabase() {
        SimCardQueryResponse response = restTemplate.getForObject(QUERY_ENDPOINT, SimCardQueryResponse.class, 2);
        assertFalse(response.getActive());
    }
}