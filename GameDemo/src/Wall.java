public class Wall extends block{
	private boolean solid;
	
	public Wall(boolean solid, int x, int y) {
		this.setSolid(solid);
		super.setX(x);
		super.setY(y);
	}

	public boolean getSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	public String toString() {
		return "You are running into a Wall"; // just for dubugging perposes otherwise everyblock will be assigned something
	}
}