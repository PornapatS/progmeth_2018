package character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import sharedObject.RenderableHolder;

public class Player extends Position {
	
	private int level = 1;
	private int life = 10;
	private int score = 0;
	private int speed = 10;
	private int barrierCount;
	private boolean isBarrierOn = false;
	private boolean isBoss = false;
	private String control;
	public Image ownerPic = new Image("player.png");
	public Image barrierPic = new Image("barrier.png");
	public AudioClip levelupSound = new AudioClip(ClassLoader.getSystemResource("levelup.wav").toString());
	
	public Player() {
		super(400,500);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		if (isBarrierOn) {
			gc.drawImage(barrierPic, x-25, y-30);
		}
		gc.drawImage(ownerPic, x, y);
	}
	
	@Override
	public void updatePos() {
		if (control.contains("a")) {
			if (x >= -30) {
				x -= speed;
			}
		}
		if (control.contains("d")) {
			if (x <= 700) {
				x += speed; 	
			}
		}
		if (control.contains("w")) {
			if (y >= 20) {
				y -= speed;
			}
		}
		if (control.contains("s")) {
			if (y + 100 <= 600) {
				y += speed;
			}
		}
	}
	
	public Bullet attack(char c) {
		Bullet bullet = new Bullet(x + 28, y - 15, c);
		RenderableHolder.getInstance().add(bullet);
		bullet.setBullet();
		return bullet;
	}
	
	public boolean isAttacked(double x,double y) {
		if (Math.abs(this.x-x) <= 75 && Math.abs(this.y-y) <= 54) {
			decreaseLife();
			return true;
		}
		return false;
	}
	
	public int getLevel() {
		return level;
	}
	public void levelUp() {
		level++;
		levelupSound.play();
	}
	public int getScore() {
		return score;
	}
	public void addScore(int score) {
		this.score += score;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public void gainLife() {
		life++;
		if(this.life > 10) {
			this.life = 10;
		}
	}
	public void decreaseLife() {
		if (isBarrierOn) {
			barrierCount--;
			if (barrierCount == 0) isBarrierOn = false;
		} else {
			life--;
		}
	}
	public boolean isDead() {
		if (life <= 0) return true;
		return false;
	}
	public void getBarrier() {
		isBarrierOn = true;
		barrierCount = 3;
	}
	public int getBarrierCount() {
		return barrierCount;
	}
	public boolean isBarrierOn() {
		return isBarrierOn;
	}
	public void setBarrierOn(boolean isBarrierOn) {
		this.isBarrierOn = isBarrierOn;
	}
	public boolean isBoss() {
		return isBoss;
	}
	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}
	public void setControl(String control) {
		this.control = control;
	}
}
