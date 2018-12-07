package uet.oop.bomberman;

import uet.oop.bomberman.Sound.play;
import uet.oop.bomberman.gui.Frame;

public class BombermanGame {
	
	public static void main(String[] args) {
		play.playMusic("res\\sound\\bg.wav");
		new Frame();
	}
}
