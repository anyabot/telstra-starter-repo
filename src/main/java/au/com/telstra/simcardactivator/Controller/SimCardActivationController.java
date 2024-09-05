package au.com.telstra.simcardactivator.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import au.com.telstra.simcardactivator.Data.SimCard;
import au.com.telstra.simcardactivator.Data.SimCardActuatorRequest;
import au.com.telstra.simcardactivator.Data.SimCardActuatorResponse;

@RestController
public class SimCardActivationController {
private static final String ACTUATOR_ENDPOINT = "http://localhost:8444/actuate";

  @PostMapping("/activate-sim")
  public void handleActivationRequest(@RequestBody SimCard simCard) {
    RestTemplate restTemplate = new RestTemplate();
    SimCardActuatorResponse response = restTemplate.postForObject(ACTUATOR_ENDPOINT, new SimCardActuatorRequest(simCard.getIccid()), SimCardActuatorResponse.class);
    if (response.getSuccess()) {
      System.out.println("Activation of SIM card with ICCID: " + simCard.getIccid() + " was successful.");
    } else {
      System.out.println("Activation of SIM card with ICCID: " + simCard.getIccid() + " failed.");
    }
  }
}
