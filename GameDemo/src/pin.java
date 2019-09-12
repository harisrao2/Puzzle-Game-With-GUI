public class pin extends block{
	private boolean have;

	public pin (boolean have, int x, int y) {
		
		this.setHave(have);
		super.setX(x);
		super.setY(y);
	}

	public boolean getHave() {
		return have;
	}

	public void setHave(boolean have) {
		this.have = have;
	}
	
	public String toString() {
		return "You picked up a pin\n";
	}
}
