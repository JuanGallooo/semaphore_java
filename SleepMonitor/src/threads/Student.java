package threads;

import model.Office;

public class Student implements Runnable{
	/**
	 * Atributo de tipo estatico que indica el posible estado del estudiante, en este caso indicando que se encuentra en la sala de computo
	 */
	public static final String STATE_COMPUTO="STATE_COMPUTO";
	/**
	 * Atributo de tipo estatico que indica el posible estado del estudiante, en este caso indicando que se encuentra en la oficina del monitor
	 */
	public static final String STATE_OFFICE="STATE_OFFICE";
	
	/**
	 * Atributo de tipo integer que indica el identificador del estudiante actual
	 */
	private int id;
	/**
	 * Atributo de tipo string que indica el estado del estudiante, puede ser STATE_COMPUTO o STATE_OFFICE
	 */
	private String state;
	/**
	 * Oficina del monitor a la que pertenece el estudiante 
	 */
	private Office office;
	/**
	 * Constructor de la clase de estudiante
	 * @param id el id de tipo integer con el cual se va identificar el estudiante
	 * @param office indica la oficina a la que sera asignada
	 */
	public Student(int id,Office office) {
		this.id= id;
		//Todos los estudiantes empiezan en la sala de computo
		this.state= Student.STATE_COMPUTO;
		this.office= office;
	}
	/**
	 * Metodo run del hilo
	 */
	@Override
	public void run() {
		while (true) {
			try {
				//Variable change que indica si posiblemente necesita ir a una monitoria es un random de 0 a 1 
				double change=Math.random();
				// En caso de ser menor al <0.35 indica que necesita una monitoria, siempre que este en la sala de computo
				if(change<0.35 && state.equals(Student.STATE_COMPUTO)) {
					System.out.println("El estudiante "+ id +" se dirige a la oficina del monitor");
					// Busca si hay lugar en las sillas de la oficina, o sino se dirige a la sala de computo de nuevo, y espera
					if(office.getAvailableChairs()>office.getStudents().size()) {
						System.out.println("El estudiante "+ id +" ha encontrado una silla disponible");
						this.state=Student.STATE_OFFICE;
						office.newStudent(this);
						System.out.println("Estudiante "+id+" puesto en cola en la posicion " + office.getStudents().size());
					}
					else System.out.println("No hay sillas disponibles, el estudiante "+id+" vuelve a la sala de computo.");
				}
				//la espera es aleatoria, asi determina cada cierto tiempo si es necesario cambiar y buscar una monitoria
				Thread.sleep((long)(Math.random()*100));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Retorna el estado estudiante 
	 * @return un string indicando el estado actual del estudiante
	 */
	public String getState() {
		return state;
	}
	/**
	 * Metodo set para cambiar el estado del estudiante
	 * @param state el nuevo estado del estudiante 
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * Retorna la oficina a la que esta asociada el estudiante
	 * @return
	 */
	public Office getOffice() {
		return office;
	}
	/**
	 * Metodo que retorna el id del estudiante
	 * @return un integer indicando el id
	 */
	public int getId() {
		return id;
	}
}
