package sudoku;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;

public class MainScreen {

	private JFrame frame;
	public JTextField cell00;
	public JTextField cell01;
	public JTextField cell02;
	public JTextField cell10;
	public JTextField cell11;
	public JTextField cell12;
	public JTextField cell20;
	public JTextField cell21;
	public JTextField cell22;
	public JTextField cell03;
	public JTextField cell04;
	public JTextField cell05;
	public JTextField cell13;
	public JTextField cell14;
	public JTextField cell15;
	public JTextField cell23;
	public JTextField cell24;
	public JTextField cell25;
	public JTextField cell06;
	public JTextField cell07;
	public JTextField cell08;
	public JTextField cell16;
	public JTextField cell17;
	public JTextField cell18;
	public JTextField cell26;
	public JTextField cell27;
	public JTextField cell28;
	public JTextField cell33;
	public JTextField cell34;
	public JTextField cell35;
	public JTextField cell43;
	public JTextField cell44;
	public JTextField cell45;
	public JTextField cell53;
	public JTextField cell54;
	public JTextField cell55;
	public JTextField cell30;
	public JTextField cell31;
	public JTextField cell32;
	public JTextField cell40;
	public JTextField cell41;
	public JTextField cell42;
	public JTextField cell50;
	public JTextField cell51;
	public JTextField cell52;
	public JTextField cell36;
	public JTextField cell37;
	public JTextField cell38;
	public JTextField cell46;
	public JTextField cell47;
	public JTextField cell48;
	public JTextField cell56;
	public JTextField cell57;
	public JTextField cell58;
	public JTextField cell63;
	public JTextField cell64;
	public JTextField cell65;
	public JTextField cell73;
	public JTextField cell74;
	public JTextField cell75;
	public JTextField cell83;
	public JTextField cell84;
	public JTextField cell85;
	public JTextField cell60;
	public JTextField cell61;
	public JTextField cell62;
	public JTextField cell70;
	public JTextField cell71;
	public JTextField cell72;
	public JTextField cell80;
	public JTextField cell81;
	public JTextField cell82;
	public JTextField cell66;
	public JTextField cell67;
	public JTextField cell68;
	public JTextField cell76;
	public JTextField cell77;
	public JTextField cell78;
	public JTextField cell86;
	public JTextField cell87;
	public JTextField cell88;
	
	List<JTextField> cells = new ArrayList();
	int[][] sudoku = new int[9][9];
	
	public void clear() {
		
		cells.forEach(cell->cell.setText(""));
	}
	
	public void getPuzzle() {
		
		int col = 0;
		int row = 0;
		
		for(int i=0; i<cells.size(); i++) {
			String val = cells.get(i).getText();
			System.out.println(val);
			if (val.isEmpty()) {
				sudoku[row][col] = 0;
			}else {
				sudoku[row][col] = Integer.parseInt(val);
			}
			if(col == 8) {
				row++;
				col = 0;
			}else {
				col++;
			}
		}		
	}
	
	public boolean checkRow(int row, int number) { 
		for(int i=0; i<9; i++) {
			if(sudoku[row][i] == number) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkCol(int col, int number) {
		for(int i=0; i<9; i++) {
			if(sudoku[i][col] == number) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkBox(int row, int col, int number) {
		int startRow = row - (row % 3);
		int startCol = col - (col % 3);
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(sudoku[startRow + i][startCol + j] == number) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkElement(int row, int col, int num) {
		return checkRow(row, num) && checkCol(col, num) && checkBox(row, col, num);
	}
	
	public int[] nextZeroCell(){
		int[] nextZero = new int[2];
		nextZero[0] = -1;		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(sudoku[i][j] == 0) {
					nextZero[0] = i;
					nextZero[1] = j;
					return nextZero;
				}
			}
		}	
		return nextZero;
	}
	
	public void displayCurrent() {
		int row = 0;
		int col = 0;
		for (int i=0; i<cells.size(); i++) {
			cells.get(i).setText(Integer.toString(sudoku[row][col]));
			if(col == 8) {
				row++;
				col = 0;
			}else {
				col++;
			}			
		}
	}
	
	public boolean solve() {
		int[] nextZero = nextZeroCell();
		if(nextZero[0] == -1) {
			return true;
		}else{
			for(int tryingNum=1; tryingNum<10; tryingNum++) {
				if(checkElement(nextZero[0], nextZero[1], tryingNum) == true) {
					sudoku[nextZero[0]][nextZero[1]] = tryingNum;
						if(solve() == true) {
							return true;
						}
					}
				sudoku[nextZero[0]][nextZero[1]] = 0;
				}
			}
		return false;
	}
	
	public void generate() {
		clear();
		Random rand = new Random();
		List<Integer> options = new ArrayList<>();
		for(int i=0; i<10; i++) {
			options.add(i);
		}
		for(int i=0; i<9; i++) {
			int randIdx = rand.nextInt(options.size());
			sudoku[i][i] = options.get(randIdx);
			options.remove(randIdx);
		}
		solve();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 708, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 644, 26);
		frame.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		
		JMenu mnPerferences = new JMenu("Settings");
		menuBar.add(mnPerferences);
		
		JMenuItem mntmSettings = new JMenuItem("Preferences");
		mnPerferences.add(mntmSettings);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAlgorithm = new JMenuItem("Algorithm");
		mnAbout.add(mntmAlgorithm);
		
		JMenuItem mntmAuthor = new JMenuItem("Author");
		mnAbout.add(mntmAuthor);
		
		JPanel leftBar = new JPanel();
		leftBar.setBackground(new Color(30, 144, 255));
		leftBar.setBounds(0, 25, 189, 590);
		frame.getContentPane().add(leftBar);
		leftBar.setLayout(null);
		
		JButton btnNewButton = new JButton("Solve");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getPuzzle();
				solve();
			}
		});
		btnNewButton.setBounds(32, 30, 102, 25);
		leftBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		btnNewButton_1.setBounds(32, 60, 102, 25);
		leftBar.add(btnNewButton_1);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(32, 98, 102, 25);
		leftBar.add(btnGenerate);
		
		JPanel box00 = new JPanel();
		box00.setLayout(null);
		box00.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		box00.setBounds(213, 89, 154, 154);
		frame.getContentPane().add(box00);
		
		JPanel box01 = new JPanel();
		box01.setLayout(null);
		box01.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		box01.setBounds(367, 89, 154, 154);
		frame.getContentPane().add(box01);
		
		JPanel box02 = new JPanel();
		box02.setLayout(null);
		box02.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		box02.setBounds(521, 89, 154, 154);
		frame.getContentPane().add(box02);
		
		JPanel box11 = new JPanel();
		box11.setLayout(null);
		box11.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		box11.setBounds(367, 243, 154, 154);
		frame.getContentPane().add(box11);
		
		JPanel box10 = new JPanel();
		box10.setLayout(null);
		box10.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		box10.setBounds(213, 243, 154, 154);
		frame.getContentPane().add(box10);
		
		JPanel box12 = new JPanel();
		box12.setLayout(null);
		box12.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		box12.setBounds(521, 243, 154, 154);
		frame.getContentPane().add(box12);
		
		JPanel box21 = new JPanel();
		box21.setLayout(null);
		box21.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		box21.setBounds(367, 397, 154, 154);
		frame.getContentPane().add(box21);
		
		JPanel box20 = new JPanel();
		box20.setLayout(null);
		box20.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		box20.setBounds(213, 397, 154, 154);
		frame.getContentPane().add(box20);
		
		JPanel box22 = new JPanel();
		box22.setLayout(null);
		box22.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		box22.setBounds(521, 397, 154, 154);
		frame.getContentPane().add(box22);
		
		cell00 = new JTextField();
		cell00.setText("9");
		cell00.setHorizontalAlignment(SwingConstants.CENTER);
		cell00.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell00.setColumns(10);
		cell00.setBounds(2, 2, 50, 50);
		box00.add(cell00);
		cells.add(cell00);
		
		cell01 = new JTextField();
		cell01.setText("9");
		cell01.setHorizontalAlignment(SwingConstants.CENTER);
		cell01.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell01.setColumns(10);
		cell01.setBounds(52, 2, 50, 50);
		box00.add(cell01);
		cells.add(cell01);
		
		cell02 = new JTextField();
		cell02.setText("9");
		cell02.setHorizontalAlignment(SwingConstants.CENTER);
		cell02.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell02.setColumns(10);
		cell02.setBounds(102, 2, 50, 50);
		box00.add(cell02);
		cells.add(cell02);
		
		cell03 = new JTextField();
		cell03.setText("9");
		cell03.setHorizontalAlignment(SwingConstants.CENTER);
		cell03.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell03.setColumns(10);
		cell03.setBounds(2, 2, 50, 50);
		box01.add(cell03);
		cells.add(cell03);
		
		cell04 = new JTextField();
		cell04.setText("9");
		cell04.setHorizontalAlignment(SwingConstants.CENTER);
		cell04.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell04.setColumns(10);
		cell04.setBounds(52, 2, 50, 50);
		box01.add(cell04);
		cells.add(cell04);
		
		cell05 = new JTextField();
		cell05.setText("9");
		cell05.setHorizontalAlignment(SwingConstants.CENTER);
		cell05.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell05.setColumns(10);
		cell05.setBounds(102, 2, 50, 50);
		box01.add(cell05);
		cells.add(cell05);
		
		cell06 = new JTextField();
		cell06.setText("9");
		cell06.setHorizontalAlignment(SwingConstants.CENTER);
		cell06.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell06.setColumns(10);
		cell06.setBounds(2, 2, 50, 50);
		box02.add(cell06);
		cells.add(cell06);
		
		cell07 = new JTextField();
		cell07.setText("9");
		cell07.setHorizontalAlignment(SwingConstants.CENTER);
		cell07.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell07.setColumns(10);
		cell07.setBounds(52, 2, 50, 50);
		box02.add(cell07);
		cells.add(cell07);
		
		cell08 = new JTextField();
		cell08.setText("9");
		cell08.setHorizontalAlignment(SwingConstants.CENTER);
		cell08.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell08.setColumns(10);
		cell08.setBounds(102, 2, 50, 50);
		box02.add(cell08);
		cells.add(cell08);
		
		cell10 = new JTextField();
		cell10.setText("9");
		cell10.setHorizontalAlignment(SwingConstants.CENTER);
		cell10.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell10.setColumns(10);
		cell10.setBounds(2, 52, 50, 50);
		box00.add(cell10);
		cells.add(cell10);
		
		cell11 = new JTextField();
		cell11.setText("9");
		cell11.setHorizontalAlignment(SwingConstants.CENTER);
		cell11.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell11.setColumns(10);
		cell11.setBounds(52, 52, 50, 50);
		box00.add(cell11);
		cells.add(cell11);
		
		cell12 = new JTextField();
		cell12.setText("9");
		cell12.setHorizontalAlignment(SwingConstants.CENTER);
		cell12.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell12.setColumns(10);
		cell12.setBounds(102, 52, 50, 50);
		box00.add(cell12);
		cells.add(cell12);
		
		cell13 = new JTextField();
		cell13.setText("9");
		cell13.setHorizontalAlignment(SwingConstants.CENTER);
		cell13.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell13.setColumns(10);
		cell13.setBounds(2, 52, 50, 50);
		box01.add(cell13);
		cells.add(cell13);
		
		cell14 = new JTextField();
		cell14.setText("9");
		cell14.setHorizontalAlignment(SwingConstants.CENTER);
		cell14.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell14.setColumns(10);
		cell14.setBounds(52, 52, 50, 50);
		box01.add(cell14);
		cells.add(cell14);
		
		cell15 = new JTextField();
		cell15.setText("9");
		cell15.setHorizontalAlignment(SwingConstants.CENTER);
		cell15.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell15.setColumns(10);
		cell15.setBounds(102, 52, 50, 50);
		box01.add(cell15);
		cells.add(cell15);
		
		cell16 = new JTextField();
		cell16.setText("9");
		cell16.setHorizontalAlignment(SwingConstants.CENTER);
		cell16.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell16.setColumns(10);
		cell16.setBounds(2, 52, 50, 50);
		box02.add(cell16);
		cells.add(cell16);
		
		cell17 = new JTextField();
		cell17.setText("9");
		cell17.setHorizontalAlignment(SwingConstants.CENTER);
		cell17.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell17.setColumns(10);
		cell17.setBounds(52, 52, 50, 50);
		box02.add(cell17);
		cells.add(cell17);
		
		cell18 = new JTextField();
		cell18.setText("9");
		cell18.setHorizontalAlignment(SwingConstants.CENTER);
		cell18.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell18.setColumns(10);
		cell18.setBounds(102, 52, 50, 50);
		box02.add(cell18);
		cells.add(cell18);
		
		cell20 = new JTextField();
		cell20.setText("9");
		cell20.setHorizontalAlignment(SwingConstants.CENTER);
		cell20.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell20.setColumns(10);
		cell20.setBounds(2, 102, 50, 50);
		box00.add(cell20);
		cells.add(cell20);
		
		cell21 = new JTextField();
		cell21.setText("9");
		cell21.setHorizontalAlignment(SwingConstants.CENTER);
		cell21.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell21.setColumns(10);
		cell21.setBounds(52, 102, 50, 50);
		box00.add(cell21);
		cells.add(cell21);
		
		cell22 = new JTextField();
		cell22.setText("9");
		cell22.setHorizontalAlignment(SwingConstants.CENTER);
		cell22.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell22.setColumns(10);
		cell22.setBounds(102, 102, 50, 50);
		box00.add(cell22);
		cells.add(cell22);
		
		cell23 = new JTextField();
		cell23.setText("9");
		cell23.setHorizontalAlignment(SwingConstants.CENTER);
		cell23.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell23.setColumns(10);
		cell23.setBounds(2, 102, 50, 50);
		box01.add(cell23);
		cells.add(cell23);
		
		cell24 = new JTextField();
		cell24.setText("9");
		cell24.setHorizontalAlignment(SwingConstants.CENTER);
		cell24.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell24.setColumns(10);
		cell24.setBounds(52, 102, 50, 50);
		box01.add(cell24);
		cells.add(cell24);
		
		cell25 = new JTextField();
		cell25.setText("9");
		cell25.setHorizontalAlignment(SwingConstants.CENTER);
		cell25.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell25.setColumns(10);
		cell25.setBounds(102, 102, 50, 50);
		box01.add(cell25);
		cells.add(cell25);
		
		cell26 = new JTextField();
		cell26.setText("9");
		cell26.setHorizontalAlignment(SwingConstants.CENTER);
		cell26.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell26.setColumns(10);
		cell26.setBounds(2, 102, 50, 50);
		box02.add(cell26);
		cells.add(cell26);
		
		cell27 = new JTextField();
		cell27.setText("9");
		cell27.setHorizontalAlignment(SwingConstants.CENTER);
		cell27.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell27.setColumns(10);
		cell27.setBounds(52, 102, 50, 50);
		box02.add(cell27);
		cells.add(cell27);
		
		cell28 = new JTextField();
		cell28.setText("9");
		cell28.setHorizontalAlignment(SwingConstants.CENTER);
		cell28.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell28.setColumns(10);
		cell28.setBounds(102, 102, 50, 50);
		box02.add(cell28);
		cells.add(cell28);
		
		cell30 = new JTextField();
		cell30.setText("9");
		cell30.setHorizontalAlignment(SwingConstants.CENTER);
		cell30.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell30.setColumns(10);
		cell30.setBounds(2, 2, 50, 50);
		box10.add(cell30);
		cells.add(cell30);
		
		cell31 = new JTextField();
		cell31.setText("9");
		cell31.setHorizontalAlignment(SwingConstants.CENTER);
		cell31.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell31.setColumns(10);
		cell31.setBounds(52, 2, 50, 50);
		box10.add(cell31);
		cells.add(cell31);
		
		cell32 = new JTextField();
		cell32.setText("9");
		cell32.setHorizontalAlignment(SwingConstants.CENTER);
		cell32.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell32.setColumns(10);
		cell32.setBounds(102, 2, 50, 50);
		box10.add(cell32);
		cells.add(cell32);
		
		cell33 = new JTextField();
		cell33.setText("9");
		cell33.setHorizontalAlignment(SwingConstants.CENTER);
		cell33.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell33.setColumns(10);
		cell33.setBounds(2, 2, 50, 50);
		box11.add(cell33);
		cells.add(cell33);
		
		cell34 = new JTextField();
		cell34.setText("9");
		cell34.setHorizontalAlignment(SwingConstants.CENTER);
		cell34.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell34.setColumns(10);
		cell34.setBounds(52, 2, 50, 50);
		box11.add(cell34);
		cells.add(cell34);
		
		cell35 = new JTextField();
		cell35.setText("9");
		cell35.setHorizontalAlignment(SwingConstants.CENTER);
		cell35.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell35.setColumns(10);
		cell35.setBounds(102, 2, 50, 50);
		box11.add(cell35);
		cells.add(cell35);
		
		cell36 = new JTextField();
		cell36.setText("9");
		cell36.setHorizontalAlignment(SwingConstants.CENTER);
		cell36.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell36.setColumns(10);
		cell36.setBounds(2, 2, 50, 50);
		box12.add(cell36);
		cells.add(cell36);
		
		cell37 = new JTextField();
		cell37.setText("9");
		cell37.setHorizontalAlignment(SwingConstants.CENTER);
		cell37.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell37.setColumns(10);
		cell37.setBounds(52, 2, 50, 50);
		box12.add(cell37);
		cells.add(cell37);
		
		cell38 = new JTextField();
		cell38.setText("9");
		cell38.setHorizontalAlignment(SwingConstants.CENTER);
		cell38.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell38.setColumns(10);
		cell38.setBounds(102, 2, 50, 50);
		box12.add(cell38);
		cells.add(cell38);
		
		cell40 = new JTextField();
		cell40.setText("9");
		cell40.setHorizontalAlignment(SwingConstants.CENTER);
		cell40.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell40.setColumns(10);
		cell40.setBounds(2, 52, 50, 50);
		box10.add(cell40);
		cells.add(cell40);
		
		cell41 = new JTextField();
		cell41.setText("9");
		cell41.setHorizontalAlignment(SwingConstants.CENTER);
		cell41.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell41.setColumns(10);
		cell41.setBounds(52, 52, 50, 50);
		box10.add(cell41);
		cells.add(cell41);
		
		cell42 = new JTextField();
		cell42.setText("9");
		cell42.setHorizontalAlignment(SwingConstants.CENTER);
		cell42.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell42.setColumns(10);
		cell42.setBounds(102, 52, 50, 50);
		box10.add(cell42);
		cells.add(cell42);
		
		cell43 = new JTextField();
		cell43.setText("9");
		cell43.setHorizontalAlignment(SwingConstants.CENTER);
		cell43.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell43.setColumns(10);
		cell43.setBounds(2, 52, 50, 50);
		box11.add(cell43);
		cells.add(cell43);
		
		cell44 = new JTextField();
		cell44.setText("9");
		cell44.setHorizontalAlignment(SwingConstants.CENTER);
		cell44.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell44.setColumns(10);
		cell44.setBounds(52, 52, 50, 50);
		box11.add(cell44);
		cells.add(cell44);
		
		cell45 = new JTextField();
		cell45.setText("9");
		cell45.setHorizontalAlignment(SwingConstants.CENTER);
		cell45.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell45.setColumns(10);
		cell45.setBounds(102, 52, 50, 50);
		box11.add(cell45);
		cells.add(cell45);
		
		cell46 = new JTextField();
		cell46.setText("9");
		cell46.setHorizontalAlignment(SwingConstants.CENTER);
		cell46.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell46.setColumns(10);
		cell46.setBounds(2, 52, 50, 50);
		box12.add(cell46);
		cells.add(cell46);
		
		cell47 = new JTextField();
		cell47.setText("9");
		cell47.setHorizontalAlignment(SwingConstants.CENTER);
		cell47.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell47.setColumns(10);
		cell47.setBounds(52, 52, 50, 50);
		box12.add(cell47);
		cells.add(cell47);
		
		cell48 = new JTextField();
		cell48.setText("9");
		cell48.setHorizontalAlignment(SwingConstants.CENTER);
		cell48.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell48.setColumns(10);
		cell48.setBounds(102, 52, 50, 50);
		box12.add(cell48);
		cells.add(cell48);
		
		cell50 = new JTextField();
		cell50.setText("9");
		cell50.setHorizontalAlignment(SwingConstants.CENTER);
		cell50.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell50.setColumns(10);
		cell50.setBounds(2, 102, 50, 50);
		box10.add(cell50);
		cells.add(cell50);
		
		cell51 = new JTextField();
		cell51.setText("9");
		cell51.setHorizontalAlignment(SwingConstants.CENTER);
		cell51.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell51.setColumns(10);
		cell51.setBounds(52, 102, 50, 50);
		box10.add(cell51);
		cells.add(cell51);
		
		cell52 = new JTextField();
		cell52.setText("9");
		cell52.setHorizontalAlignment(SwingConstants.CENTER);
		cell52.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell52.setColumns(10);
		cell52.setBounds(102, 102, 50, 50);
		box10.add(cell52);
		cells.add(cell52);
		
		cell53 = new JTextField();
		cell53.setText("9");
		cell53.setHorizontalAlignment(SwingConstants.CENTER);
		cell53.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell53.setColumns(10);
		cell53.setBounds(2, 102, 50, 50);
		box11.add(cell53);
		cells.add(cell53);
		
		cell54 = new JTextField();
		cell54.setText("9");
		cell54.setHorizontalAlignment(SwingConstants.CENTER);
		cell54.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell54.setColumns(10);
		cell54.setBounds(52, 102, 50, 50);
		box11.add(cell54);
		cells.add(cell54);
		
		cell55 = new JTextField();
		cell55.setText("9");
		cell55.setHorizontalAlignment(SwingConstants.CENTER);
		cell55.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell55.setColumns(10);
		cell55.setBounds(102, 102, 50, 50);
		box11.add(cell55);
		cells.add(cell55);
		
		cell56 = new JTextField();
		cell56.setText("9");
		cell56.setHorizontalAlignment(SwingConstants.CENTER);
		cell56.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell56.setColumns(10);
		cell56.setBounds(2, 102, 50, 50);
		box12.add(cell56);
		cells.add(cell56);
		
		cell57 = new JTextField();
		cell57.setText("9");
		cell57.setHorizontalAlignment(SwingConstants.CENTER);
		cell57.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell57.setColumns(10);
		cell57.setBounds(52, 102, 50, 50);
		box12.add(cell57);
		cells.add(cell57);
		
		cell58 = new JTextField();
		cell58.setText("9");
		cell58.setHorizontalAlignment(SwingConstants.CENTER);
		cell58.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell58.setColumns(10);
		cell58.setBounds(102, 102, 50, 50);
		box12.add(cell58);
		cells.add(cell58);
		
		cell60 = new JTextField();
		cell60.setText("9");
		cell60.setHorizontalAlignment(SwingConstants.CENTER);
		cell60.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell60.setColumns(10);
		cell60.setBounds(2, 2, 50, 50);
		box20.add(cell60);
		cells.add(cell60);
		
		cell61 = new JTextField();
		cell61.setText("9");
		cell61.setHorizontalAlignment(SwingConstants.CENTER);
		cell61.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell61.setColumns(10);
		cell61.setBounds(52, 2, 50, 50);
		box20.add(cell61);
		cells.add(cell61);
		
		cell62 = new JTextField();
		cell62.setText("9");
		cell62.setHorizontalAlignment(SwingConstants.CENTER);
		cell62.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell62.setColumns(10);
		cell62.setBounds(102, 2, 50, 50);
		box20.add(cell62);
		cells.add(cell62);
				
		cell63 = new JTextField();
		cell63.setText("9");
		cell63.setHorizontalAlignment(SwingConstants.CENTER);
		cell63.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell63.setColumns(10);
		cell63.setBounds(2, 2, 50, 50);
		box21.add(cell63);
		cells.add(cell63);
		
		cell64 = new JTextField();
		cell64.setText("9");
		cell64.setHorizontalAlignment(SwingConstants.CENTER);
		cell64.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell64.setColumns(10);
		cell64.setBounds(52, 2, 50, 50);
		box21.add(cell64);
		cells.add(cell64);
		
		cell65 = new JTextField();
		cell65.setText("9");
		cell65.setHorizontalAlignment(SwingConstants.CENTER);
		cell65.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell65.setColumns(10);
		cell65.setBounds(102, 2, 50, 50);
		box21.add(cell65);
		cells.add(cell65);
		
		cell66 = new JTextField();
		cell66.setText("9");
		cell66.setHorizontalAlignment(SwingConstants.CENTER);
		cell66.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell66.setColumns(10);
		cell66.setBounds(2, 2, 50, 50);
		box22.add(cell66);
		cells.add(cell66);
		
		cell67 = new JTextField();
		cell67.setText("9");
		cell67.setHorizontalAlignment(SwingConstants.CENTER);
		cell67.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell67.setColumns(10);
		cell67.setBounds(52, 2, 50, 50);
		box22.add(cell67);
		cells.add(cell67);
		
		cell68 = new JTextField();
		cell68.setText("9");
		cell68.setHorizontalAlignment(SwingConstants.CENTER);
		cell68.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell68.setColumns(10);
		cell68.setBounds(102, 2, 50, 50);
		box22.add(cell68);
		cells.add(cell68);
		
		cell70 = new JTextField();
		cell70.setText("9");
		cell70.setHorizontalAlignment(SwingConstants.CENTER);
		cell70.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell70.setColumns(10);
		cell70.setBounds(2, 52, 50, 50);
		box20.add(cell70);
		cells.add(cell70);
		
		cell71 = new JTextField();
		cell71.setText("9");
		cell71.setHorizontalAlignment(SwingConstants.CENTER);
		cell71.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell71.setColumns(10);
		cell71.setBounds(52, 52, 50, 50);
		box20.add(cell71);
		cells.add(cell71);
		
		cell72 = new JTextField();
		cell72.setText("9");
		cell72.setHorizontalAlignment(SwingConstants.CENTER);
		cell72.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell72.setColumns(10);
		cell72.setBounds(102, 52, 50, 50);
		box20.add(cell72);
		cells.add(cell72);
		
		cell73 = new JTextField();
		cell73.setText("9");
		cell73.setHorizontalAlignment(SwingConstants.CENTER);
		cell73.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell73.setColumns(10);
		cell73.setBounds(2, 52, 50, 50);
		box21.add(cell73);
		cells.add(cell73);
		
		cell74 = new JTextField();
		cell74.setText("9");
		cell74.setHorizontalAlignment(SwingConstants.CENTER);
		cell74.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell74.setColumns(10);
		cell74.setBounds(52, 52, 50, 50);
		box21.add(cell74);
		cells.add(cell74);
		
		cell75 = new JTextField();
		cell75.setText("9");
		cell75.setHorizontalAlignment(SwingConstants.CENTER);
		cell75.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell75.setColumns(10);
		cell75.setBounds(102, 52, 50, 50);
		box21.add(cell75);
		cells.add(cell75);
		
		cell76 = new JTextField();
		cell76.setText("9");
		cell76.setHorizontalAlignment(SwingConstants.CENTER);
		cell76.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell76.setColumns(10);
		cell76.setBounds(2, 52, 50, 50);
		box22.add(cell76);
		cells.add(cell76);
		
		cell77 = new JTextField();
		cell77.setText("9");
		cell77.setHorizontalAlignment(SwingConstants.CENTER);
		cell77.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell77.setColumns(10);
		cell77.setBounds(52, 52, 50, 50);
		box22.add(cell77);
		cells.add(cell77);
		
		cell78 = new JTextField();
		cell78.setText("9");
		cell78.setHorizontalAlignment(SwingConstants.CENTER);
		cell78.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell78.setColumns(10);
		cell78.setBounds(102, 52, 50, 50);
		box22.add(cell78);
		cells.add(cell78);
		
		cell80 = new JTextField();
		cell80.setText("9");
		cell80.setHorizontalAlignment(SwingConstants.CENTER);
		cell80.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell80.setColumns(10);
		cell80.setBounds(2, 102, 50, 50);
		box20.add(cell80);
		cells.add(cell80);
		
		cell81 = new JTextField();
		cell81.setText("9");
		cell81.setHorizontalAlignment(SwingConstants.CENTER);
		cell81.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell81.setColumns(10);
		cell81.setBounds(52, 102, 50, 50);
		box20.add(cell81);
		cells.add(cell81);
		
		cell82 = new JTextField();
		cell82.setText("9");
		cell82.setHorizontalAlignment(SwingConstants.CENTER);
		cell82.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell82.setColumns(10);
		cell82.setBounds(102, 102, 50, 50);
		box20.add(cell82);
		cells.add(cell82);
		
		cell83 = new JTextField();
		cell83.setText("9");
		cell83.setHorizontalAlignment(SwingConstants.CENTER);
		cell83.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell83.setColumns(10);
		cell83.setBounds(2, 102, 50, 50);
		box21.add(cell83);
		cells.add(cell83);
		
		cell84 = new JTextField();
		cell84.setText("9");
		cell84.setHorizontalAlignment(SwingConstants.CENTER);
		cell84.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell84.setColumns(10);
		cell84.setBounds(52, 102, 50, 50);
		box21.add(cell84);
		cells.add(cell84);
		
		cell85 = new JTextField();
		cell85.setText("9");
		cell85.setHorizontalAlignment(SwingConstants.CENTER);
		cell85.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell85.setColumns(10);
		cell85.setBounds(102, 102, 50, 50);
		box21.add(cell85);
		cells.add(cell85);
								
		cell86 = new JTextField();
		cell86.setText("9");
		cell86.setHorizontalAlignment(SwingConstants.CENTER);
		cell86.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell86.setColumns(10);
		cell86.setBounds(2, 102, 50, 50);
		box22.add(cell86);
		cells.add(cell86);
		
		cell87 = new JTextField();
		cell87.setText("9");
		cell87.setHorizontalAlignment(SwingConstants.CENTER);
		cell87.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell87.setColumns(10);
		cell87.setBounds(52, 102, 50, 50);
		box22.add(cell87);
		cells.add(cell87);
		
		cell88 = new JTextField();
		cell88.setText("9");
		cell88.setHorizontalAlignment(SwingConstants.CENTER);
		cell88.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cell88.setColumns(10);
		cell88.setBounds(102, 102, 50, 50);
		box22.add(cell88);
		cells.add(cell88);
	}
}
