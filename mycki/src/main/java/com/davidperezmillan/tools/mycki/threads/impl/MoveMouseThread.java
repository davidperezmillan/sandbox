package com.davidperezmillan.tools.mycki.threads.impl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;


public class MoveMouseThread {

	private static Robot hal;

	public Thread t = new Thread(() -> {
		try {
			hal = new Robot();
			Random random = new Random();
			while (!Thread.currentThread().isInterrupted()) { // Preparamos la
				int x = random.nextInt(640 - 0) + 0;
				int y = random.nextInt(480 - 0) + 0;

				hal.mouseMove(x, y);
				// Simula el click del raton
				click();

				// simula presion de teclas
				keypress("Ancer", true);

				System.out.printf("Time : {d} --> x: {d}, y: {d}\n", new Date(), String.valueOf(x), String.valueOf(y)); 
				// Simula Movimiento

				hal.mouseMove(200 + x, 2000 + y);
				// Simula click
				click();

				try {
					Thread.sleep((60 * 1000) * 3); // (Segundo * milisegundo)*
													// minutos
				} catch (InterruptedException e) {
					// Evitamos que si esta en sleep no lanze excepcion
					Thread.currentThread().interrupt();
				}
			}
		} catch (AWTException e) {
			System.err.println("ERROR" + e); //$NON-NLS-1$
			Thread.currentThread().interrupt();
		}
	});

	private void click() throws AWTException {
		hal.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		hal.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	private void keypress(String text, Boolean isDelete) throws AWTException {
		for (char letter : text.toCharArray()) {
			hal.keyPress(KeyEvent.getExtendedKeyCodeForChar(letter));
			if (isDelete)
				hal.keyPress(KeyEvent.VK_BACK_SPACE);
		}
	}

	public void start() {
		System.out.printf("Time : {d} --> Start\n", new Date()); 
		t.start();
	}

	public void stop() {
		System.out.printf("Time : {d} --> Stop\n", new Date()); 
		t.interrupt();

	}

}
