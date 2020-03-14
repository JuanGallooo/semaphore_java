package model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import threads.Student;

public class Office {
	/**
	 * Lista de estudiantes en espera en las sillas de la oficina
	 */
	private ArrayList<Student> students;
	/**
	 * Este paramtro indica el numero de sillas disponibles
	 */
	private int availableChairs;
	/**
	 * Este atributo indica el semaforo utilizado
	 */
	private Semaphore semaphore;
	/**
	 * Constructor de la clase office
	 * @param availableChairs indica el numero de sillas disponible en esta oficina
	 */
	public Office(int availableChairs) {
		this.availableChairs= availableChairs;
		this.semaphore=new Semaphore(1);
		this.students= new ArrayList<Student>();
	}
	/**
	 * Este metodo se encarga de agregar un nuevo item a la lista de estudiantes, utilizando el semaforo para indicar el nuevo ingreso
	 * @param student es el estudiante que se desea ingresar
	 */
	public void newStudent(Student student) {
		try {
			semaphore.acquire();
			students.add(student);
			semaphore.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Este metodo se encarga de atender a un estudiante, atiende al primero de la lista que sería el siguiente en cola por atender
	 * @return un integer que indica el id del estudiante que atendió, en caso de no atender ninguno devuelve un -1
	 */
	public int attendStudent() {
		Student student;
		try {
			if(isPendingStudent()) {
				semaphore.acquire();
				// se pone el estado del estudiante de computo para indicar que se acabo de atender y vuelve a la sala
				students.get(0).setState(Student.STATE_COMPUTO);
				// se saca de la lista de pendientes
				student= students.remove(0);
				semaphore.release();
				return student.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * Metodo que se encarga de indicar si hay algun estudiante pendiente en cola por atender 
	 * @return un boolean para indicar si hay pendientes o no 
	 */
	public boolean isPendingStudent() {
		return !students.isEmpty();
	}
	/**
	 * Metodo que retorna la lista de los estudiantes
	 * @return los estudiantes de la lista en un atributo de tipo ArrayList
	 */
	public ArrayList<Student> getStudents() {
		return students;
	}
	/**
	 * El metodo get para obtener el numero de sillas disponibles para esta oficina
	 * @return un atributo de tipo int con el numero de sillas disponibles para la oficina 
	 */
	public int getAvailableChairs() {
		return availableChairs;
	}
}
