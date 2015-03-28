import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Juego extends JPanel {

	Pelota pelota = new Pelota(this);
	// El constructor this es el objeto que realiza el new Pelota de Juego
	Raqueta raqueta = new Raqueta(this);
	// El constructor this es el onjeto que realiza el new Raqueta de Juego
	int velocidad = 1;

	private int getPuntuacion()
	{
		return velocidad - 1;
	}
	

	public Juego()
	{
		addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				raqueta.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				raqueta.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	private void mover() throws InterruptedException
	{
		pelota.mover();
		//Se lama al metodo mover de Pelota
		raqueta.mover();
		//Se llama al metodo mover de Raqueta
	}

	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		//Super.paint(g) borra la pantalla al momento que se mueve la pelota y raqueta.
		Graphics2D g2d = (Graphics2D) g;
		//Este Graphics2D clase extiende la Graphics clase para proporcionar un control más sofisticado sobre geometría, transformaciones de coordenadas, la gestión del color y la disposición del texto. 
		//Esta es la clase fundamental para la prestación de formas de 2 dimensiones, texto e imágenes en la (tm) plataforma Java.
		//http://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		//Las antialias suavizan los bordes de la pelota para que no se mire de manera aspera.
		g2d.setColor(Color.ORANGE);
		//Color de la Pelota
		pelota.paint(g2d);
		raqueta.paint(g2d);

		g2d.setColor(Color.ORANGE);
		//Color del Puntaje
		g2d.setFont(new Font("Cambria Math", Font.ITALIC, 30));
		//Fuente de Puntaje
		g2d.drawString(String.valueOf(getPuntuacion()), 10, 30);
	
	}


	public void Perdiste()  throws InterruptedException
	{
		int resp=JOptionPane.showOptionDialog(null,"¡Perdiste!" + "\n Su puntaje es de "+ getPuntuacion() + " puntos."+ "\n ¿Qué deseas hacer?", "Fin del juego", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE,
                null,    
                // null para icono por defecto.
                new Object[] { " Jugar otra vez ", " Salir de Programa " }, "Jugar otra vez");
				//http://www.jc-mouse.net/java/3-en-raya-java-con-mvc-y-netbeans
	      if (JOptionPane.OK_OPTION != resp)
	      {
	    	   System.exit(ABORT);
	    	   //Termina la ejecucion del programa. 	
	      }
	      else
	    	   if(JOptionPane.OK_OPTION==resp)
	    	   {
	    		   JFrame frame = new JFrame("BALLWIN");
	    		   Juego juego = new Juego();
	    			frame.add(juego);
	    			frame.setSize(300, 400);
	    			//Tamaño de la ventana.
	    			frame.setVisible(true);
	    			//Hace visible la ventana
	    			frame.setResizable(false);
	    			//Evita que la ventana se redimensione
	    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    			//Cierra la ventana al dar clic en la X   
	    		while(true) 
	    		{	
	    			juego.getPuntuacion(); 
	    			juego.mover();
	    			juego.repaint();
	    			// repaint llama de nuevo al metodo pintar (public void paint(Graphics g) 
	    			// lo que hace repaint es llamar a public void paint(Graphics g)  el cual llama a super.paint(g), este llama al padre que es JPanel
	    			Thread.sleep(10);
	    			//Le dice al thread que se esta ejecutando (juego.mover();) que pare (duerma) por 10 milisegundos
	    			//Para darla change a otros thread y con este a la siguiente instruccion (juego.repaint();) de ejecutarse.
	    			//Sin esto, el ciclo while ejecutaria el game.move(); sin darle chance al juego.repaint(); de ejercutarse.
	    		}
	    	   }
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		JFrame frame = new JFrame("BallWin");
		Juego juego = new Juego();
		frame.add(juego);
		frame.setSize(300, 400);
		//Tamaño de la ventana.
		frame.setVisible(true);
		//Hace visible la ventana
		frame.setResizable(false);
		//Evita que la ventana se redimensione
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Cierra la ventana al dar clic en la X	
		
		while (true )
			//hace un ciclo infinito por medio del true.
		{
			juego.mover();
			juego.repaint();
			// repaint llama de nuevo al metodo pintar (public void paint(Graphics g) 
			// lo que hace repaint es llamar a public void paint(Graphics g)  el cual llama a super.paint(g), este llama al padre que es JPanel
			Thread.sleep(10);
			//Le dice al thread que se esta ejecutando (juego.mover();) que pare (duerma) por 10 milisegundos
			//Para darla change a otros thread y con este a la siguiente instruccion (juego.repaint();) de ejecutarse.
			//Sin esto, el ciclo while ejecutaria el game.move(); sin darle chance al juego.repaint(); de ejercutarse.
		}
	}
}