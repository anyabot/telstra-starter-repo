package au.com.telstra.simcardactivator.repositories;

import org.springframework.data.repository.CrudRepository;

import au.com.telstra.simcardactivator.models.SimCardRecord;

public interface SimCardRepository extends CrudRepository<SimCardRecord, Long> {
  
}
