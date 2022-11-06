package dynamic_beat3;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	protected boolean my_Impact_Condition;
	int x, y;

	@Override
	public void keyPressed(KeyEvent e) {
		if (DynamicBeat.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			DynamicBeat.game.pressESCAPE();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			x = 165;
			y = 150;
			DynamicBeat.game.pressA();
			my_Impact_Condition = true;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			x = 325;
			y = 150;
			DynamicBeat.game.pressS();
			my_Impact_Condition = true;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			x = 485;
			y = 150;
			DynamicBeat.game.pressD();
			my_Impact_Condition = true;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			x = 645;
			y = 150;
			DynamicBeat.game.pressJ();
			my_Impact_Condition = true;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			x = 805;
			y = 150;
			DynamicBeat.game.pressK();
			my_Impact_Condition = true;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			x = 965;
			y = 150;
			DynamicBeat.game.pressL();
			my_Impact_Condition = true;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (DynamicBeat.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			DynamicBeat.game.releaseA();
			my_Impact_Condition = false;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.releaseS();
			my_Impact_Condition = false;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.releaseD();
			my_Impact_Condition = false;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.releaseJ();
			my_Impact_Condition = false;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.releaseK();
			my_Impact_Condition = false;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.releaseL();
			my_Impact_Condition = false;
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			DynamicBeat.game.releaeESCAPE();
			DynamicBeat.game.send(my_Impact_Condition, x, y);
		}
	}
}