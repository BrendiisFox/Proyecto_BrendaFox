import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
//Deshace las advertencias

public class Teclado extends JPanel {
	
	public Teclado() {
		KeyListener listener = new MiTeclado();
		addKeyListener(listener);
		//Se encarga de resgistrar que tecla es presionada.
		//Si se quiere registras los eventos del ratón se usaria addMouseListener(MouseListener listener).
		//http://tech.stolsvik.com/2009/03/awt-swing-event-pumping-and-targeting.html
		setFocusable(true);
		//Para que funcionen los ventos del teclado.
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Tennis");
		Teclado Teclado = new Teclado();
		frame.add(Teclado);
		frame.setSize(200, 200);
		//Tamaño de la ventana
		frame.setVisible(true);
		//Hace visible la ventana
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class MiTeclado implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
			//Imprime la tecla que ha sido presionada.
			//al presionar una tecla el getKeyCode da un codigo al getKeyText para que devuelva el valor de la tecla presiona.
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
		}
		
	}
}