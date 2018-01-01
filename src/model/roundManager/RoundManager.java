package model.roundManager;

import java.util.Timer;

import main.Log;

/**
 * @author Sebastian Reith
 */

public class RoundManager {

	// time in milliseconds after which every user user must have been finished
	// a round
	public static final int TIMEOUT_TIME = 10 * 60 * 1000;	// 10 minutes

	
	/**
	 * runs game on server with RoundManager
	 */
	public void startGame(){
		Timer timeoutTimer = new Timer();	// initialise Timer
		
		while(true){	// rounds
			TimeoutTask myTimeoutTask = new TimeoutTask();
			
			// TODO start round
		    
		    timeoutTimer.schedule(myTimeoutTask, TIMEOUT_TIME); //start Timer
		    
		    // iterate players
		    for(int i = 0; i<4/*TODO<=count of Players*/; i++){
		    	
		    	// wait for Data from Players
		    	while(true){
		    		if(myTimeoutTask.isTimeout()){
		    			// Timeout reached
		    			// TODO fire Players, which have not committed
		    			Log.i("RoundManager", "Timeout reached!");
		    			break;
		    		}
		    		
		    		if(false/*TODO Data from player received*/){
		    			// TODO save received data
		    			break;
		    		}
		    		
		    		// TODO does thread need much processing time?
		    		try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    	}
		    }
		    
		    // TODO handle data
		    // TODO send gamestates to player(s)
		    
		    // next round
		}
	}
}
