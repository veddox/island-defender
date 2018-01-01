package model.roundManager;

import main.Log;

public class RoundManagerTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RoundManager manager = new RoundManager();
		Log.i("RoundManagerTester", "start Tester ...");
		manager.startGame();
		Log.e("RoundManagerTester", "should not be reached");
	}

}
