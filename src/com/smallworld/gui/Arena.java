package com.smallworld.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.smallworld.Smurf;

public class Arena extends JFrame{

	Arena arena;
	int arenaX = 800;
	int arenaY = 600;
	Smurf[] smurf;
	Random generator = new Random();
	int intervalDelay = 50; // in msec.;
	int x;
	int y;
	int move;
	int moves = 1000;
	int population = 100;
    Image[] img = new Image[2]; 
    int[][] matrix;
	int[] stats = new int[8];
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Arena arena = new Arena();
		arena.init();

	}

	public void init(){
		JPanel jpanel = new JPanel();
		this.add(jpanel);
		this.setSize(arenaX, arenaY);
		this.matrix = new int[arenaX][arenaY];
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		smurf = new Smurf[population];
			
		// create the 'smurf' population
		for ( int i=0; i< smurf.length; i++)
		{
			smurf[i] = new Smurf(arenaX,arenaY, generator.nextInt(arenaX), generator.nextInt(arenaY));
			smurf[i].setKeepDirectionX(generator.nextInt(20));
			smurf[i].setKeepDirectionY(generator.nextInt(20));
			smurf[i].setSpeed(generator.nextInt(2)+1);
		}
		
		for ( move=0; move < moves; move++ )
		{
			repaint();
			
			try {
				Thread.sleep(intervalDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for( int a=0; a < stats.length; a++ )
			System.out.print(" "+stats[a]);
		
		  
        for (int a=0; a < arenaY; a++)
        {
        	for (int b=0; b < arenaX; b++)
    		{
    			System.out.print(matrix[b][a]);
    		}
        	System.out.println();
        }
	}
	
	public void paint( Graphics g )
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(g2.getBackground());
		g2.drawString("Move:"+move, 5, this.getHeight()-5);
		g2.fillRect(0, this.getHeight()-20, 300, 20);  
		
		for ( int i=0; i < smurf.length; i++)
		{	
			// here comes the new position
			int[] xy = smurf[i].getPositionXY();

			// collision detection
			switch (matrix[xy[0]][xy[1]])
			{
				case 0: 
						g2.drawImage(smurf[i].getImage(0), smurf[i].getOldPositionX(), smurf[i].getOldPositionY(), null);
						g2.setColor(g2.getBackground());
						g2.drawRect(smurf[i].getOldPositionX(), smurf[i].getOldPositionY(), 16, 16);
						g2.drawImage(smurf[i].getImage(1), xy[0], xy[1], null);
						g2.setColor(Color.BLUE);
						g2.drawRect(xy[0], xy[1], 16, 16);
						
//						matrix[smurf[i].getOldPositionX()][smurf[i].getOldPositionY()] = 0;
//						matrix[smurf[i].getPositionX()][smurf[i].getPositionY()] = 1;
						
						// clean old matrix
						for (int x=0; x<16; x++)
							for (int y=0; y<16; y++)
								if ( ( smurf[i].getOldPositionX()+x < arenaX ) &&
									 ( smurf[i].getOldPositionY()+y < arenaY) )
									matrix[smurf[i].getOldPositionX()+x][smurf[i].getOldPositionY()+y] = 0;
						
						// set new matrix
						for (int x=0; x<16; x++)
							for (int y=0; y<16; y++)
								if ( ( smurf[i].getPositionX()+x < arenaX ) &&
									 ( smurf[i].getPositionY()+y < arenaY) )
									matrix[smurf[i].getPositionX()+x][smurf[i].getPositionY()+y] = 1;
						
						break;
						
				case 1: 
						// reset the position - remain where you are
						smurf[i].setPositionX(smurf[i].getOldPositionX());
						smurf[i].setPositionY(smurf[i].getOldPositionY());
						// set new matrix
						for (int x=0; x<16; x++)
							for (int y=0; y<16; y++)
								if ( ( smurf[i].getPositionX()+x < arenaX ) &&
									 ( smurf[i].getPositionY()+y < arenaY) )
									matrix[smurf[i].getPositionX()+x][smurf[i].getPositionY()+y] = 1;
			
						break;
						
				case 2: 
						break;
			
				default: break;
			}      
		}
		
		g2.setColor(Color.GRAY);
        g2.drawString("Move:"+ move +" ["+moves+"] - ["+ smurf[0].getPositionX() +","+ smurf[0].getPositionY() +"]", 5, this.getHeight()-5 );
	}	
}


