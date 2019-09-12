public class plank extends block{
	private int num;
	
	
	public plank(int num, int x, int y){
		this.setNum(num);
		super.setX(x);
		super.setY(y);
		
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String toString() {
	
			return "You found a plank.\n";
		
	}
	
}
