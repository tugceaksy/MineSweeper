import javax.swing.JButton;

public class button extends JButton{
	private int row,col; //to keep locations of buttons
	private int count;   //to keep neighbours of mines
	private boolean mine,flag;   //is there ant flag or mine?
	
	public button(int row, int col) {   //constructor of button class
		this.row = row;
		this.col = col;
		this.count = 0;//default
		this.mine = false;//default
		this.flag = false;//default
	}
    //encapsulate them for use
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
	
	
}