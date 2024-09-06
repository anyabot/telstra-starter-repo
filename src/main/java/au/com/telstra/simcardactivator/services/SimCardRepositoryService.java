package au.com.telstra.simcardactivator.services;

import org.springframework.stereotype.Component;

import au.com.telstra.simcardactivator.dto.SimCardActuatorResponse;
import au.com.telstra.simcardactivator.models.SimCard;
import au.com.telstra.simcardactivator.models.SimCardRecord;
import au.com.telstra.simcardactivator.repositories.SimCardRepository;

@Component
public class SimCardRepositoryService {
  private final SimCardRepository simCardRepository;

  public SimCardRepositoryService(SimCardRepository simCardRepository) {
    this.simCardRepository = simCardRepository;
  }

  public Long saveSimCardRecord(SimCard simcard, SimCardActuatorResponse response) {
    SimCardRecord simCardRecord = new SimCardRecord(simcard, response);
    simCardRepository.save(simCardRecord);
    return simCardRecord.getId();
  }

  public SimCardRecord querySimCardRecord(Long simCardId) {
    return simCardRepository.findById(simCardId).orElse(null);
  }
}
