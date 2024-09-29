package entity;
import jakarta.persistence.*;

@Entity
@Table(name="REGISTER")
public class Register {

	@Id
	@Column(name="registerId")	
	private int registerId;
	@Column(name="name")
	private String name;
	
	
	
	public Register() {
		super();
	}

	public Register(int registerId, String name) {
		super();
		this.registerId = registerId;
		this.name = name;
	}
	
	public int getRegisterId() {
		return registerId;
	}
	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Register [registerId=" + registerId + ", name=" + name + "]";
	}
	

}
