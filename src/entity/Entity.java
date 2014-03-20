package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import content.Animation;

public class Entity {

	// position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	// dimensions
	protected int width;
	protected int height;

	// collision box
	protected int cwidth;
	protected int cheight;

	// collision
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
//	protected boolean topLeft;
//	protected boolean topRight;
//	protected boolean bottomLeft;
//	protected boolean bottomRight;

	// animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;

	protected boolean facingRight;

	// movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;

	// movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;

	protected boolean isFlightEnabled = false;

	public static boolean showBox = false;

	
	public int health;
	public int maxHealth;
	
	// constructor
	public Entity() {
		health = 150;
		maxHealth= 150;
	}

	public void calculateCorners(double x, double y) {

		//			final int leftTile = (int) (x - (cwidth / 2)) / tileSize;
		//			final int rightTile = (int) ((x + (cwidth / 2)) - 1) / tileSize;
		//			final int topTile = (int) (y - (cheight / 2)) / tileSize;
		//			final int bottomTile = (int) ((y + (cheight / 2)) - 1) / tileSize;
		//
		////			final int tl = tileMap.getType(topTile, leftTile);
		////			final int tr = tileMap.getType(topTile, rightTile);
		////			final int bl = tileMap.getType(bottomTile, leftTile);
		////			final int br = tileMap.getType(bottomTile, rightTile);
		//
		//			topLeft = tl == Tile.BLOCKED;
		//			topRight = tr == Tile.BLOCKED;
		//			bottomLeft = bl == Tile.BLOCKED;
		//			bottomRight = br == Tile.BLOCKED;

	}

	public void checkTileMapCollision() {

		xdest = x + dx;
		ydest = y + dy;

		xtemp = x;
		ytemp = y;

//		calculateCorners(x, ydest);
		if (dy < 0)
			if (y == 0) {
				dy = 0;

			} else
				ytemp += dy;
		if (dy > 0)
			if (y >= 200) {
				dy = 0;
				falling = false;
			} else
				ytemp += dy;

//		calculateCorners(xdest, y);
		if (dx < 0)
			if (x <= 0 + width/2) {
				dx = 0;
			} else
				xtemp += dx;
		if (dx > 0)
			if (x >= 320-width/2) {
				dx = 0;
			} else
				xtemp += dx;

		if (!falling) {
			if (y < 200)
				falling = true;
		}
	}

	public void draw(Graphics2D g) {
		drawSprite(g, animation);
	}

	private void drawSprite(Graphics2D g, Animation am) {

		if (showBox) {
			// Rectangle r = getRectangle();
			// g.setColor(Color.WHITE);
			// g.draw(r);
		}
		
		// draw

		if (facingRight)
			g.drawImage(animation.getImage(), 
					(int) ((x) - (width / 2)),
					(int) ((y) - (height / 2)), null);
		else
			g.drawImage(animation.getImage(),
					(int) ((x) - (width /2)) + width,
					(int) ((y) - (height/ 2)),
					-width, height, null);
	}

	public int getCHeight() {
		return cheight;
	}

	public int getCWidth() {
		return cwidth;
	}

	public int getHeight() {
		return height;
	}

	public void getNextPosition() {

		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed)
				dx = -maxSpeed;
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed)
				dx = maxSpeed;
		}

		if (falling && !isFlightEnabled)
			dy += fallSpeed;
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x-(cwidth/2) , (int) y-(cheight/2), cwidth,
				cheight);
	}

	public int getWidth() {
		return width;
	}


		public int getx() {
			return (int) x;
		}

		public int gety() {
			return (int) y;
		}

		public boolean intersects(Entity o) {
			final Rectangle r1 = getRectangle();
			final Rectangle r2 = o.getRectangle();
			return r1.intersects(r2);
		}

		public void setDown(boolean b) {
			down = b;
		}

		public void setJumping(boolean b) {
			jumping = b;
		}

		public void setLeft(boolean b) {
			left = b;
		}

//		public void setMapPosition() {
//			x = tileMap.getx();
//			y = tileMap.gety();
//		}

		public void setPosition(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public void setRight(boolean b) {
			right = b;
		}

		public void setUp(boolean b) {
			up = b;
		}

		public void setVector(double dx, double dy) {
			this.dx = dx;
			this.dy = dy;
		}
		
		public void damageEntity(int damage){
			health -= damage;
		}
}