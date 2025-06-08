package utils;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class LoadingFrame implements Runnable {

	private JLabel gifLabel;
	private Runnable tarea;

	// redibe el label y el objeto runnable 
	public LoadingFrame(JLabel gifLabel, Runnable tarea) {
		// TODO Auto-generated constructor stub
		this.gifLabel = gifLabel;
		this.tarea = tarea;
		
	}

	// se inicia el hilo
	public void show() {
		new Thread(this).start();
	}
	
	// aqui esta lo que ejecuta el hilo
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// se muestra el label del gif
		gifLabel.setVisible(true); 
		// se ejeucta la tarea
		tarea.run();
		// cuando termina la tarea esconde el gif
		gifLabel.setVisible(false); 
	}

}
