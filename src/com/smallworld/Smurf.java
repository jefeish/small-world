package com.smallworld;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Smurf {

		int positionX;
		int positionY;
		int oldPositionX;
		int oldPositionY;
		int deltaPositionX;
		int deltaPositionY;
		int borderPadding = 30;
		int mood = 0;			// mood indicator (0..4) good to bad
		int moodX;				// support for mood indicator
		int moodY;				// support for mood indicator
		int attitude;			// for future...
		int rangeX;				// Width of the area the 'smurf' moves in
		int rangeY;				// Height of the area the 'smurf' moves in
		int speed = 1;			// how big are the 'leaps' the 'smurf' takes with each move
		int agile = 1;			// how agile is the 'smurf' when moving
		int size = 3;			// size of the 'smurf'
		int keepDirectionX = 20; // for how many steps should a 'smurf' follow in one direction
		int keepDirectionY = 20;	// for how many steps should a 'smurf' follow in one direction
		int distanceTraveledX; 	// for how long has he moved in this direction
		int distanceTraveledY; 	// for how long has he moved in this direction
		int p2nRatioX = 2;		// positive to negative ratio for X position
		int p2nRatioY = 1;		// positive to negative ratio for Y position
		Image[] image = new Image[2];
		
		Random generator = new Random();

		public Smurf(int sizeX, int sizeY)
		{
			rangeX = sizeX;
			rangeY = sizeY;
			// start position
			positionX = rangeX/2;
			positionY = rangeY/2;
			init();
		}
		
		public Smurf(int sizeX, int sizeY, int startX, int startY)
		{
			rangeX = sizeX;
			rangeY = sizeY;
			// start position
			positionX = startX;
			positionY = startY;
			init();
		}
		
		public void init()
		{
			try {
				image[0] = ImageIO.read(this.getClass().getResource("nosmurf.gif"));
				image[1] = ImageIO.read(this.getClass().getResource("smurf.gif"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 
		 * @return
		 */
		public int[] getPosition()
		{
			int [] pos = {positionX,positionY};	
			return pos;
		}
		
		/**
		 * 
		 * @return
		 */
	    public int getDeltaPositionX() 
	    { 	
//	    	int sign;
//	    	Random notation = new Random();
//	    	
//	    	if ( notation.nextInt() > 0 )
//	    	{
//	    		sign = 1;
//	    	}
//	    	else
//	    	{
//	    		sign = -1;
//	    	}
//	    	
//	    	if ( distanceTraveledX >= keepDirectionX )
//	    	{
//	    		deltaPositionX = generator.nextInt(2);
//	    		deltaPositionX = deltaPositionX + size*speed*(sign); 
//	    		distanceTraveledX = 0;
//	    	}
//	    	else
//	    		distanceTraveledX += 1;
//	    	
//			if ( (positionX > deltaPositionX*2) && 
//				 (positionX <= (rangeX - deltaPositionX*2)) 
//			   )
//			{
//				positionX = positionX + deltaPositionX;
//				mood = 0;
//				moodX = 0;
//			}
//			else 
//				moodX = 3;	// hitting the wall makes me feel bad :(
			
	    	return positionX;
		}
	    
	    /**
		 * 
		 * @return
		 */
	    public void setDeltaPositionX( int direction ) 
	    { 	  	
	    	if ( distanceTraveledX >= keepDirectionX )
	    	{
	    		deltaPositionX = size*speed*(direction); 
	    		distanceTraveledX = 0;
	    	}
	    	else
	    		distanceTraveledX += 1;
	    	
	    	// check if we are still inside the 'walls'
    		oldPositionX = positionX;	// backup the current position
			int tmpX = positionX + deltaPositionX;
			
			if ( tmpX <= borderPadding )
			{
				positionX = borderPadding;
				moodX = 3;	// hitting the wall makes me feel bad :(
			}
			else if ( tmpX > rangeX-borderPadding )
			{
				positionX = rangeX-borderPadding;
				moodX = 3;	// hitting the wall makes me feel bad :(
			}
			else
			{
				positionX = tmpX;
				mood = 0;
				moodX = 0;		
			}	
		}
	    
	    /**
	     * 
	     * @return
	     */
		public int getDeltaPositionY() {
//	    	int sign;
//	    	Random notation = new Random();
//	    	
//	    	if ( notation.nextInt() > 0 )
//	    		sign = 1;
//	    	else
//	    		sign = -1;
//	    	
//	    	if ( distanceTraveledY >= keepDirectionY )
//	    	{
//	    		deltaPositionY = generator.nextInt(2);
//	    		deltaPositionY = deltaPositionY + size*speed*(sign); 
//	    		distanceTraveledY = 0;
//	    	}
//	    	else
//	    		distanceTraveledY += 1;
//	    	
//			if ( (positionY > deltaPositionY*2) && 
//				 (positionY <= (rangeY - deltaPositionY*2)) 
//			   )
//			{
////				System.out.println("positionY="+positionY+"+"+deltaPositionY);
//				positionY = positionY + deltaPositionY;
//				mood = 0;
//				moodY = 0;			
//			}
//			else 
//				moodY = 3;	// hitting the wall makes me feel bad :(
//			
			return positionY;
		}

	    /**
	     * 
	     * @return
	     */
		public void setDeltaPositionY( int direction ) 
		{
	    	if ( distanceTraveledY >= keepDirectionY )
	    	{
	    		deltaPositionY = size*speed*(direction); 
	    		distanceTraveledY = 0;
	    	}
	    	else
	    		distanceTraveledY += 1;
	    	
	    	// check if we are still inside the 'walls'
	    		oldPositionY = positionY;	// backup the current position
				int tmpY = positionY + deltaPositionY;
				
				if ( tmpY < borderPadding )
				{
					positionY = borderPadding;
					moodY = 3;	// hitting the wall makes me feel bad :(
				}
				else if ( tmpY >= rangeY-borderPadding )
				{
					positionY = rangeY-borderPadding;
					moodY = 3;	// hitting the wall makes me feel bad :(
				}
				else
				{
					positionY = tmpY;
					mood = 0;
					moodY = 0;		
				}
		}
		
		public int getPositionX() {
			return positionX;
		}
		
		public int getPositionY() {
			return positionY;
		}

		public void setPositionX(int positionX) {
			this.positionX = positionX;
		}
		
		public void setPositionY(int positionY) {
			this.positionY = positionY;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getMood() {
			
			return mood;
		}
		
		public Color getMoodColor() {
			
			if (moodX != 0) 
				mood = moodX;
			else if (moodY != 0)
				mood = moodY;
			
			switch (mood) {
				case 0: return Color.BLUE;
						
				case 1: return Color.YELLOW;
						
				case 2: return Color.ORANGE;
						
				case 3: return Color.RED;
						
				default: return Color.GREEN;					
			}
		}

		public void setMood(int mood) {
			this.mood = mood;
		}

		public int getAttitude() {
			return attitude;
		}

		public void setAttitude(int attitude) {
			this.attitude = attitude;
		}

		public int getKeepDirectionX() {
			return keepDirectionX;
		}

		public void setKeepDirectionX(int keepDirectionX) {
			this.keepDirectionX = keepDirectionX;
		}

		public int getKeepDirectionY() {
			return keepDirectionY;
		}

		public void setKeepDirectionY(int keepDirectionY) {
			this.keepDirectionY = keepDirectionY;
		}

		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}

		public Image getImage(int i) {
			return image[i];
		}

		public void setImage(int i, Image image) {
			this.image[i] = image;
		}

		public int getOldPositionX() {
			return oldPositionX;
		}

		public void setOldPositionX(int oldPositionX) {
			this.oldPositionX = oldPositionX;
		}

		public int getOldPositionY() {
			return oldPositionY;
		}

		public void setOldPositionY(int oldPositionY) {
			this.oldPositionY = oldPositionY;
		}
		
		public int[] getPositionXY()
		{
			int[] xy = new int[2];
			giveDirection();
			xy[0]=getPositionX();
			xy[1]=getPositionY();
			
			return xy;
		}
		
		public void giveDirection()
		{
			int direction = generator.nextInt(8);

			// move direction explanation
			// 7  0  1         1
			//  \ | /          |
			// 6- * -2    -1 <-*-> 1
			//  / | \          |
			// 5  4  3        -1
			
			switch (direction) {
				case 0: setDeltaPositionX( 0 );
						setDeltaPositionY( 1 ); 
					break;
				case 1: setDeltaPositionX( 1 );
						setDeltaPositionY( 1 ); 
					break;
				case 2: setDeltaPositionX( 1 );
						setDeltaPositionY( 0 ); 
					break;
				case 3: setDeltaPositionX( 1 );
						setDeltaPositionY( -1 ); 
					break;
				case 4: setDeltaPositionX( 0 );
						setDeltaPositionY( -1 ); 
					break;
				case 5: setDeltaPositionX( -1 );
						setDeltaPositionY( -1 ); 
					break;
				case 6: setDeltaPositionX( -1 );
						setDeltaPositionY( 0 );  
					break;
				case 7: setDeltaPositionX( -1 );
						setDeltaPositionY( 1 ); 
					break;
				default: break;
			}
		}
	}
