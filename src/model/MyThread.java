package model;

public abstract class MyThread implements Runnable {
	
	private Thread thread;
	private String name;
	private boolean stop;
	private boolean pause;
	
	public MyThread(String name) {
		this.name = name;
		thread = new Thread(this, name);
	}
	
	public void start() {
		thread.start();
	}
	
	public synchronized void stop() {
		pause = false;
		stop = true;
		notify();
	}
	
	public synchronized void pause() {
		pause = true;
	}
	
	public synchronized void resume() {
		pause = false;
		notify();
	}
	
	public void run() {
		while (!stop) {
			executeTask(name);
			synchronized (this) {
				while (pause) {
					try {
						wait();
					} catch (InterruptedException e) {
					}
				}
				if (stop) {
					break;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	abstract void executeTask();

	void executeTask(String word) {
		System.out.println(word);
	}
}