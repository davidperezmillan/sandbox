package com.davidperezmillan.tools.mycki.frames;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import com.davidperezmillan.tools.mycki.threads.impl.MoveMouseThread;

public class MyJToggleButton extends JFrame {
	private static final long serialVersionUID = 1L;

	private JToggleButton btn;
	private JToggleButton btnConsole;
	private JTextArea txtConsole;
	private MoveMouseThread lT;

	public MyJToggleButton() {
		setTitle("mycki");
		setLayout(new FlowLayout());
		setJToggleButton();
		setAction();
		setSize(600, 75);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(1000, 700);
	}

	private void setJToggleButton() {

		btn = new JToggleButton("En espera");
		btn.setPreferredSize(new Dimension(200, 20));
		add(btn);
		btnConsole = new JToggleButton("Info Visible");
		btnConsole.setPreferredSize(new Dimension(200, 20));
		add(btnConsole);
		txtConsole = new JTextArea(10,50);
		
		// Now create a new TextAreaOutputStream to write to our JTextArea control and wrap a
		// PrintStream around it to support the println/printf methods.
		PrintStream out = new PrintStream( new TextAreaOutputStream( txtConsole ) );

		// redirect standard output stream to the TextAreaOutputStream
		// redirect standard error stream to the TextAreaOutputStream
		System.setOut( out );
		//System.setErr( out );
		txtConsole.setVisible(false);
		add(txtConsole);
		
	}

	private void setAction() {
		
		btn.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent eve) {
				if (btn.isSelected()){
					lT = new MoveMouseThread();
					lT.start();
					btn.setText("En destino");
				}else{
					lT.stop();
					btn.setText("En espera");
				}
			}
		});
		
		btnConsole.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		    	if (btnConsole.isSelected()){
		    		txtConsole.setVisible(true);
					setSize(600, 300);
					btnConsole.setText("Info Visible"); //$NON-NLS-1$
				}else{
					txtConsole.setVisible(false);
		    		setSize(600, 75);
		    		btnConsole.setText("Info Hidden"); //$NON-NLS-1$
					
				}
		    }   
		});
	}
}