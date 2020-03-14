package model;

import threads.Monitor;
import threads.Student;

public class Main {
	/**
	 * Metodo main de la solucion de monitor dormilon, hecho por Juan Esteban Gallo Plaza
	 * @param args
	 */
	public static void main(String[] args) {
		// Indica el numero de sillas que habrá
		int chairs=3;
		// La oficina del monitor
		Office oficina= new Office(chairs);
		// Numeros al azar del numero de estudiantes que habra
		int numStudents= (int)((Math.random()*10)+ 1);
		//Inicializamos todos los estudiantes
		for (int i = 0; i < numStudents; i++) {
			Student nuevo= new Student(i, oficina);
			Thread hiloStudent = new Thread(nuevo);
			hiloStudent.start();
		}
		//Inicializamos el monitor
		int idMonitor=0;
		Monitor monitor= new Monitor(idMonitor,oficina);
		Thread hiloMonitor = new Thread(monitor);
		hiloMonitor.start();
	}

}
