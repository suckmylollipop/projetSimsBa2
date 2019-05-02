package Model;

import java.util.Timer;
import java.util.TimerTask;

public class Computer extends GameObject implements Activable, OversizedObject {
	private int money = 40;
	private Player p;
	private Game g;
	private static int count = 0;
	private static int count1 = 0;
	private static int waitCount = 0;
	public Computer(int X, int Y, Player player, int widthIntRatio, int heightIntRatio, Game g) {
		super(X,Y,6, widthIntRatio, heightIntRatio);
		this.p = player;
		this.g  = g;
		
	}
	
	 public void activate() {
		 if(waitCount ==0) {
		 Timer timer = new Timer();
		 TimerTask task = new TimerTask() {
				public void run() {
				if(count1 >= 1) {
				timer.cancel();
				StartAndEnd();}
				g.removePlayerFromPlayers(p);
				g.addPlayerToSleepingObjects(p);
				p.setSleepingState();
				count1++;
				
			}
		 
			      };
			timer.schedule(task,0,1000);  
		 }
			
		}
	 
	 public int getComputerMoney() {
		 return(money);
	 }
	 
	 public void StartAndEnd() {
		 Timer destimer = new Timer();
		 TimerTask desTask = new TimerTask() {
			 
			 public void run() {
				 if(count == 7) {
					 p.setAwakeState();
					 p.addMoney(getComputerMoney());
					 
					 g.removePlayerFromSleepingObjects(p);
					 g.addPlayerToPlayers(p);
					 
					 destimer.cancel();
					 initializingWaitTime();
					 count = 0;
				 }
				 else {
					 count++;	 
				 }
			 }
		 };
		 destimer.schedule(desTask,0,1000);
	 }
	 
	 public void initializingWaitTime() {
		 Timer waitTimer = new Timer();
		 TimerTask waitTimerTask = new TimerTask() {
			 
			 public void run() {
				 if(waitCount == 180) {
					 waitTimer.cancel();
					 waitCount = 0;
				 }
				 else {
					 waitCount ++;	 
				 }
			 }
		 };
		 waitTimer.schedule(waitTimerTask,0,1000);
	 }
	 
	 
	 public boolean isObstacle(){
		 return(true);
	 }
	 
	 public int getExtraMoney() {
		 return(money);
	 }
	 
	 public void addEnergyToPlayer() {
		p.addMoney(money);
	 }
	 
	 public boolean isInObjectSpace(int x, int y) {
	    	boolean z = false;
	    	int k1 = this.getPosX(); 
	    	int k2  = this.getPosY() ;
	    	int k3 = this.getWidthRatio() ; 
	    	int k4 =  this.getHeightRatio();
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
}
