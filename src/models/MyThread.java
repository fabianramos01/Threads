package models;

import javax.swing.JOptionPane;

public class MyThread implements Runnable{

	private Thread thread;
	private String text;
	private int sleep;
	private boolean stop;
	private boolean pause;
	
	public MyThread(String text, int sleep) {
		this.text = text;
		this.sleep = sleep;
		thread = new Thread(this);
	}
	
	@Override
	public void run() {
		int i = 0;
		while (!stop) {
			i++;
			if (i > 3) {
				break;
			}
			System.out.println(text);
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (this) {
				if (stop) {
					break;
				}
				while (pause) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void start() {
		thread.start();
	}
	
	public synchronized void stop() {
		stop = true;
		notify();
	}
	
	public synchronized void pause() {
		pause = true;
		notify();
	}
	
	public synchronized void resume() {
		pause = false;
		notify();
	}
	
	public Thread getThread() {
		return thread;
	}

	public static void main(String[] args) {
		MyThread t1 = new MyThread("Hola", 3000);
		MyThread t2 = new MyThread("Mundo", 1000);
		
		t1.start();
		try {
			t1.getThread().join();
			t2.getThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}
}