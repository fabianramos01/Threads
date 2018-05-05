package models;

public class Example {
	
	public void executeTaskOne() {
		for (int i = 0; i < 1000000; i++) {
			System.out.println("Hola");
		}
	}
	
	public void executeTaskTwo() {
		for (int i = 0; i < 10000; i++) {
			System.out.println("Mundo");
		}
	}
	public static void main(String[] args) {
		Example e = new Example();
		e.executeTaskOne();
		e.executeTaskTwo();
	}
}
