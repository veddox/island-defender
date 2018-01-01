package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TradingPost;

public class TradeController implements ActionListener{
	
	
	private GUIController guiController;
	
	private TradingPost tradingPost;
	
	
	public TradeController(GUIController guiController){
		this.guiController = guiController;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public TradingPost getTradingPost() {
		return tradingPost;
	}

	public void setTradingPost(TradingPost tradingPost) {
		this.tradingPost = tradingPost;
	}

	
	
}
