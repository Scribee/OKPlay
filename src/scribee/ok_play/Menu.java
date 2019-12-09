package scribee.ok_play;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	
	private BufferedImage settingsButtonImage, resetButtonImage;
	
	public Menu() {
		initUI();
	}
	
	private void initUI() {
		settingsButtonImage = OKPlay.loadImage("src/resources/settings_button.png");
		resetButtonImage = OKPlay.loadImage("src/resources/reset_button.png");
		
		setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
		
		MenuButton settingsButton = new MenuButton("Settings", settingsButtonImage);
		settingsButton.addActionListener(new SettingsButtonAction());
		
		MenuButton resetButton = new MenuButton("Reset Board", resetButtonImage);
		resetButton.addActionListener(new ResetButtonAction());
		
		add(settingsButton);
		add(resetButton);
	}
	
	/**
	 * What to do when the settings button is clicked.
	 */
	private class SettingsButtonAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			MenuButton button = (MenuButton) e.getSource();

			System.out.println("settings");
		}
	}
	
	private class ResetButtonAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			MenuButton button = (MenuButton) e.getSource();
			
			OKPlay.board.resetBoard();
		}
	}
}
