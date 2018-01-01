package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ChatClient;

public class ChatController implements ActionListener{
	
	private GUIController guiController;
	
	private ChatClient chatClient;
	
	
	
	public ChatController(GUIController guiController){
		this.guiController = guiController;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	public ChatClient getChatClient() {
		return chatClient;
	}

	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}




}
