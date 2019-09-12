public class Door extends block{
	private boolean exit;
	private boolean open;
	
	public Door (boolean exit, boolean open, int x,int y) {
		this.setExit(exit);
		super.setX(x);
		super.setY(y);
		
	}

	public boolean getExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	public String toString() {
		return "there is a door near you\n";
	}
}
