
public class Inventory {
	private int planks;
	private boolean pin;
	
	public Inventory(boolean pin, int planks) {
		this.setPlanks(planks);
		this.setPin(pin);	
	}
	
	
	public int getPlanks() {
		return planks;
	}
	public void setPlanks(int planks) {
		this.planks = planks;
	}
	public boolean getPin() {
		return pin;
	}
	public void setPin(boolean pin) {
		this.pin = pin;
	}
	
	
}
