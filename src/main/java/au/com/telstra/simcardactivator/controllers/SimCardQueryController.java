package au.com.telstra.simcardactivator.controllers;

import org.springframework.web.bind.annotation.RestController;

import au.com.telstra.simcardactivator.dto.SimCardQueryResponse;
import au.com.telstra.simcardactivator.models.SimCardRecord;
import au.com.telstra.simcardactivator.services.SimCardRepositoryService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class SimCardQueryController {
  private final SimCardRepositoryService simCardRepositoryService;

  public SimCardQueryController(SimCardRepositoryService simCardRepositoryService) {
    this.simCardRepositoryService = simCardRepositoryService;
  }

  @GetMapping(value = "/query-sim", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public SimCardQueryResponse handleSimCardQuery(Long simCardId) {
    SimCardRecord simCardRecord = simCardRepositoryService.querySimCardRecord(simCardId);
    if (simCardRecord == null) {
      return null;
    }
    SimCardQueryResponse simCardQueryResponse = new SimCardQueryResponse(simCardRecord);
    return simCardQueryResponse;
  }
}
