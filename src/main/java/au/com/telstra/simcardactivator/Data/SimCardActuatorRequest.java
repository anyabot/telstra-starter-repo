package au.com.telstra.simcardactivator.Data;

public class SimCardActuatorRequest {
  private String iccid;

  public SimCardActuatorRequest(String iccid) {
    this.iccid = iccid;
  }

  public String getIccid() {
    return this.iccid;
  }
}
