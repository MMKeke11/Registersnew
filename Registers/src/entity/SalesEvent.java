package entity;
import jakarta.persistence.*;

@Entity
@Table(name="SALESEVENT")
public class SalesEvent {

	@Id
	@Column(name="eventId")
	private int eventId;
	
	@ManyToOne
	@JoinColumn(name="register")
	private Register register;
	@Column(name="amount")
	private double amount;
	
	
	
	
	public SalesEvent() {
		super();
	}
	public SalesEvent(int eventId, Register register, double amount) {
		super();
		this.eventId = eventId;
		this.register = register;
		this.amount = amount;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public Register getRegister() {
		return register;
	}
	public void setRegister(Register register) {
		this.register = register;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "SalesEvent [eventId=" + eventId + ", register=" + register + ", amount=" + amount + "]";
	}
	


}
