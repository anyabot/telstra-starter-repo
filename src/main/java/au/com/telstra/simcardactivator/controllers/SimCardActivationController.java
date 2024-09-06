package au.com.telstra.simcardactivator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import au.com.telstra.simcardactivator.dto.SimCardActuatorRequest;
import au.com.telstra.simcardactivator.dto.SimCardActuatorResponse;
import au.com.telstra.simcardactivator.models.SimCard;
import au.com.telstra.simcardactivator.services.SimCardRepositoryService;

@RestController
public class SimCardActivationController {
  private static final String ACTUATOR_ENDPOINT = "http://localhost:8444/actuate";
  private final SimCardRepositoryService simCardRepositoryService;

  public SimCardActivationController(SimCardRepositoryService simCardRepositoryService) {
    this.simCardRepositoryService = simCardRepositoryService;
  }

  @PostMapping("/activate-sim")
  public Long handleActivationRequest(@RequestBody SimCard simCard) {
    RestTemplate restTemplate = new RestTemplate();
    SimCardActuatorResponse response = restTemplate.postForObject(ACTUATOR_ENDPOINT, new SimCardActuatorRequest(simCard.getIccid()), SimCardActuatorResponse.class);
    return simCardRepositoryService.saveSimCardRecord(simCard, response);
  }
}
