package au.com.telstra.simcardactivator.dto;

import au.com.telstra.simcardactivator.models.SimCardRecord;

public class SimCardQueryResponse {
  
  private String iccid;
  private String customerEmail;
  private Boolean active;

  public SimCardQueryResponse(SimCardRecord simCardRecord) {
    this.iccid = simCardRecord.getIccid();
    this.customerEmail = simCardRecord.getCustomerEmail();
    this.active = simCardRecord.getActive();
  }

  public String getIccid() {
    return this.iccid;
  }

  public String getCustomerEmail() {
    return this.customerEmail;
  }

  public Boolean getActive() {
    return this.active;
  }
}
