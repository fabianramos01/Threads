package model;

import javax.swing.JOptionPane;

public class ThreadFile extends MyThread {

	public ThreadFile(String name) {
		super(name);
	}

	void executeTask() {
		
	}
	
	public static void main(String[] args) {
		MyThread m = new ThreadFile("Hello");
		m.start();
		int option = JOptionPane.showConfirmDialog(null, "¿Detener?");
		if (option == JOptionPane.OK_OPTION) {
			m.stop();
		}
		MyThread m1 = new ThreadFile("World");
		m1.start();
	}
}