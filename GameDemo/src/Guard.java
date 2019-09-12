public class Guard extends block{
	private boolean alive;
	
	public Guard(boolean alive, int x, int y) {
	this.setAlive(alive);
	super.setX(x);
	super.setY(y);
	}

	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}