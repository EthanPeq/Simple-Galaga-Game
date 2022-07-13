import javax.swing.*;

public class Story {
	GalagaPanel galPanel;
	
	public void run() {
		init();
		updateStory();
	}
	public void init() {
		JFrame fr = new JFrame("Galaga");
		fr.setSize(600, 600);
		fr.setLocation(300, 100);
		
		galPanel = new GalagaPanel();
		fr.add(galPanel);
		
		fr.setVisible(true);
	}
	
	public void updateStory() {
		UpdateThread ut = new UpdateThread(galPanel);
		ut.start();
	}
}

class UpdateThread extends Thread{
	GalagaPanel panel;
	
	UpdateThread(GalagaPanel p){
		panel = p;
	}

	public void run(){
		while(true){
			panel.update();
			try{Thread.sleep(50);}
			catch(Exception e){}
		}
	}
}
