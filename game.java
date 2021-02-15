//importing libraries for swing

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class game implements MouseListener {//implementing  interface for click events
	JFrame frame;//created frame
	button[][] board = new button[10][10];// 10x10 multi-dimensional array (button type)
	int openButton;//number of buttons that user opened it

	public game() {//constructor of game class
		openButton = 0;
		frame = new JFrame("MINESWEEPER(GOOD LUCK!)");//frame tittled
		frame.setSize(600, 600);//size of frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//type of closing frame
		frame.setLayout(new GridLayout(10, 10));//divided frame by adding grids

		for (int row = 0; row < board.length; row++) { //adding buttons on frame and array
			for (int col = 0; col < board[0].length; col++) {
				button T = new button(row, col);
		
				frame.add(T);
				T.addMouseListener(this);
				board[row][col] = T;
			}
		}

		productMine();
		newCount();
		frame.setVisible(true);
	}

	public void productMine() { //product mine for  random locations on frame 
		int i = 0;
		while (i < 10) {
			int randomRow = (int) (Math.random() * board.length);
			int randomCol = (int) (Math.random() * board[0].length);

			while (board[randomRow][randomCol].isMine()) {// if there is a mine product again
				randomRow = (int) (Math.random() * board.length);
				randomCol = (int) (Math.random() * board[0].length);
			}
			board[randomRow][randomCol].setMine(true);
			i++;
		}
	}

	public void print() { // to show all mines
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon("mine.png"));
				} else {
					board[row][col].setText(board[row][col].getCount() + ""); //writes the count
					board[row][col].setEnabled(false);
				}
			}
		}
	}
	
	public void printMine() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon("mine.png"));
				}
			}
		}
	}

	public void newCount() { //update count of button if it is neighour of a mine
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].isMine()) {
					counter(row, col);
				}
			}
		}
	}

	public void counter(int row, int col) { //counts
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				try {           //to avoid arrayindexoutofboundsexception if I have a such number
					
					int value = board[i][j].getCount();
					board[i][j].setCount(++value);
				} catch (Exception e) {

				}
			}
		}
	}

	public void open(int r, int c) {//opens multiple button
		if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c].getText().length() > 0
				|| board[r][c].isEnabled() == false) {
			return;
		} else if (board[r][c].getCount() != 0) {
			board[r][c].setText(board[r][c].getCount() + "");
			board[r][c].setEnabled(false);
			openButton++;
		} else {
			openButton++;
			board[r][c].setEnabled(false);
			open(r - 1, c);
			open(r + 1, c);
			open(r, c - 1);
			open(r, c + 1);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		button T = (button) e.getComponent();//the button that user clicked
		if (e.getButton() == 1) {//if user use left click
		
			if (T.isMine()) {
				
				print();// user stepped on a mine
			} else {
				open(T.getRow(), T.getCol());
				if (openButton == (board.length * board[0].length) - 10) {// if all buttons are opened
					JOptionPane.showMessageDialog(frame, "YOU DÝD ÝT !");
					print();
				}
			}
		} else if (e.getButton() == 3) {//if user use right click
			
			if (!T.isFlag()) {
				T.setIcon(new ImageIcon("flag.png"));//add flag

				T.setFlag(true);
			} else {
				T.setIcon(null);//remove flag
				T.setFlag(false);
			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
