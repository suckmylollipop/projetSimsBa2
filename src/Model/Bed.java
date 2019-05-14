package Model;

import java.io.File;
import java.util.ArrayList;
import View.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Bed extends GameObject implements Activable, OversizedObject, Changeable, Sounds{
	private int energy = 40;
	private Player p;
	private Game g;
	private boolean used;
	private int count = 0;
	private int count1 = 0;
	public Bed(int X, int Y,  int widthIntRatio, int heightIntRatio, Game g) {
		super(X,Y,6, widthIntRatio, heightIntRatio);
		this.g  = g;
		
	}
	
	 public void activate() {
		 p = g.getActive_player();
		 
		 Timer timer = new Timer();
		 TimerTask task = new TimerTask() {
				public void run() {
				if(count1 >= 1) {
				playSound("Resources/Sounds/snore.wav");
				timer.cancel();
				StartAndEnd();}
				g.addPlayerToSleepingObjects(p);
				p.setSleepingState();
				count1++;
				used = true;
				
			}
				
			      };
			timer.schedule(task,0,1000);  
			
		}
	 
	 public int getBedEnergy() {
		 return(energy);
	 }
	 
	 public void StartAndEnd() {
		 Timer destimer = new Timer();
		 TimerTask desTask = new TimerTask() {
			 
			 public void run() {
				 if(count >=5) {
					 p.setAwakeState();
					 p.addEnergy(getBedEnergy());
					 used = false;
					 
					 g.removePlayerFromSleepingObjects(p);
					 
					 destimer.cancel();
					 count = 0;
				 }
				 else {
					 count++;	 
				 }
			 }
		 };
		 destimer.schedule(desTask,0,1000);
	 }
	 

	 public boolean isObstacle(){
		 return(true);
	 }
	 
	 public int getExtraEnergy() {
		 return(energy);
	 }
	 
	 public void addEnergyToPlayer() {
		p.addEnergy(energy);
	 }
	 
	 public boolean isInObjectSpace(int x, int y) {
	    	boolean z = false;
	    	int k1 = getPosX(); 
	    	int k2  = getPosY() ;
	    	int k3 = getWidthRatio() ; 
	    	int k4 =  getHeightRatio();
	    	for(int i = k1; i < k1+k3 ;i++) {
				for(int j = k2; j<k2+k4; j++){
					int x1 = i;
					int y1 = j;
				if(x == x1 && y == y1){
					z = true;
				}
			}
		}	
	    	
	    	return z;
	  }

	@Override
	public boolean isUsed() {
		return used;
	}
	
	public  void playSound(String file) {
		File voice = new File(file);
		
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(voice));
			clip.start();
		}
		
		catch(Exception e) {
			
		}
	}
}
