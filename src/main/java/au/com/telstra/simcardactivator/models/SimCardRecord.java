package au.com.telstra.simcardactivator.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import au.com.telstra.simcardactivator.dto.SimCardActuatorResponse;

@Entity
public class SimCardRecord {
  @Id
  @GeneratedValue()
  private Long id;

  @Column(nullable = false)
  private String iccid;

  @Column(nullable = false)
  private String customerEmail;

  @Column(nullable = false)
  private Boolean active;

  public SimCardRecord() {}

  public SimCardRecord(SimCard simCard, SimCardActuatorResponse response) {
    this.iccid = simCard.getIccid();
    this.customerEmail = simCard.getCustomerEmail();
    this.active = response.getSuccess();
  }

  public Long getId() {
    return this.id;
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

  @Override
  public String toString() {
    return "{\n" +
      "id: " + this.id + "\n" +
      "iccid: " + this.iccid + "\n" +
      "customerEmail: " + this.customerEmail + "\n" +
      "active: " + active + "\n" +
      "}";
  }
}
