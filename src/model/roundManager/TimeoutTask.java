package model.roundManager;

import java.util.TimerTask;

/**
 * @author Sebastian Reith
 */

public class TimeoutTask extends TimerTask {

	private boolean timeoutFlag = false;
	
	@Override
	public void run() {
		this.timeoutFlag = true;
	}

	public boolean isTimeout() {
		return this.timeoutFlag;
	}

// currently not in use
//	public void resetTimeout() {
//		Log.i("TimeoutTask",  "Timer resetted");
//		this.timeoutFlag = false;
//	}



}
