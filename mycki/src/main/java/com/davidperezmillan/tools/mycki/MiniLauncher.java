package com.davidperezmillan.tools.mycki;

import com.davidperezmillan.tools.mycki.threads.impl.MoveMouseThread;

public class MiniLauncher {

	private static MoveMouseThread it;

	public static void main(String[] args) {
		it = new MoveMouseThread();
		it.start();

	}

}