import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {

	private JButton[] buttons; // array de botoes

	private JTextField operacao;
	String op = "";
	private static final String[] names = {"x!","e^x","raiz 2","7", "8", "9", "/",
			"raiz 3","%","x²", "4","5", "6", "*", 
			"x³","x^y","pi","1", "2", "3", "-", 
			"C","(",")","0", ".", "=","+" };

	private JPanel botoes;

	private GridLayout organizaBotoes;

	// construtor sem argumentos

	public Calculadora() {

		super("Calculadora");

		setLayout(new BorderLayout());

		operacao = new JTextField("", 15);
		add(operacao, BorderLayout.NORTH);

		botoes = new JPanel();
		organizaBotoes = new GridLayout(4, 4); // botoes calculadora
		botoes.setLayout(organizaBotoes);
		add(botoes);

		buttons = new JButton[names.length]; // cria array de JButtons

		for (int count = 0; count < names.length; count++) {

			buttons[count] = new JButton(names[count]);

			botoes.add(buttons[count]);// adiciona botao ao JFrame
			
			buttons[count].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					int cont = 0;
					while(e.getActionCommand() != "=" && cont == 0) {
						op = op + e.getActionCommand();
						cont ++;
					}
					operacao.setText(op);
					
					if(e.getActionCommand() == "=") {
						
					}
					
				}
			});

		}
		
		

	}
}
