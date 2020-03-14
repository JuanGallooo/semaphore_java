package threads;

import model.Office;

public class Monitor implements Runnable {
	/**
	 * Este atributo indica si el momitor se encuentra dormido
	 */
	private boolean sleep;
	/**
	 * Este atributo indica el id del monitor que se encuentra actualmente
	 */
	private int id;
	/**
	 * Este atributo indica la oficina a la que esta asignada el monitor
	 */
	private Office office;
	/**
	 * Metodo constructor del monitor, se indica el id que tendrá, y empieza su artributo sleep como True
	 * @param id el identificador del monitor  
	 */
	public Monitor(int id,Office office) {
		this.id = id;
		this.sleep = true;
		this.office= office;
	}
	
	/**
	 * Metodo run del hilo
	 */
	@Override
	public void run() {
		int consultTime;
		int attendStudent;
		while (true) {
			try {
				if (office.isPendingStudent()) {
					if(sleep==true) {
						sleep= false;
					}
					consultTime = (int) (Math.random() * 90 + 10);
					attendStudent = office.attendStudent();
					System.out.println("El monitor se ha despertado para atender el estudiante "+attendStudent);
					Thread.sleep(consultTime);
					System.out.println("El monitor ha atendido al siguiente estudiante en cola con id " + attendStudent + " en un tiempo de "+ consultTime);
				}
				else {
					System.out.println("El monitor está dormido!");
					Thread.sleep(20);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Metodo get del atributo sleep
	 * @return indica si el monitor se encuentra dormido
	 */
	public boolean isSleep() {
		return sleep;
	}
	/**
	 * Metodo set del sleep
	 * @param sleep el nuevo parametro sleep
	 */
	public void setSleep(boolean sleep) {
		this.sleep = sleep;
	}
	/**
	 * Metodo get del id
	 * @return retorna un integer con el id del monitor
	 */
	public int getId() {
		return id;
	}
	/**
	 * Metodo set del id 
	 * @param id el nuevo id para el monitor
	 */
	public void setId(int id) {
		this.id = id;
	}

}
