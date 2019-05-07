package ExercicioCalculadora;

import java.lang.Math;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora2 extends JFrame { // usa arraylist pra armazenar valores e operaçoes (BUGADO)

	private JButton[] buttons; // array de botoes

	private JTextField expressao;
	
	private String guarda_valores = "", display = "", resposta = "", calcular = "";
	private double result = 0, op1, op2, parcial = 0;
	private int cont_op = 0;
	private boolean eh_negativo;
	
	private ArrayList<Double> valores = new ArrayList<Double>();
	private ArrayList<Character> operacoes = new ArrayList<Character>();
	int n = 0, aux = 0, cv = 0;
	
	private static final String[] names = {"x!","e^x","raiz²","7", "8", "9", "/",
											"raiz³","%","x²", "4","5", "6", "*", 
											"x³","x^y","pi","1", "2", "3", "-", 
											"C","(",")","0", ".", "=","+" };
	
	private JPanel botoes;

	private GridLayout organizaBotoes;

	// construtor sem argumentos

	public Calculadora2() {

		super("Calculadora2");

		setLayout(new BorderLayout());

		expressao = new JTextField("", 15);
		add(expressao, BorderLayout.NORTH);

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
					String operacao;
					while(e.getActionCommand() != "=" && e.getActionCommand() != "C" && cont == 0) {
						guarda_valores = guarda_valores + e.getActionCommand();
						cont ++;
						expressao.setText(guarda_valores);
						System.out.print("\nguarda_valores: "+guarda_valores+"\tcomprimento:"+guarda_valores.length());
					}
					
					if (e.getActionCommand() == "=") {
						for (int i = 0; i < guarda_valores.length(); i++)
							if ( !Character.isDigit( guarda_valores.charAt(i)) && guarda_valores.charAt(i) != '.' )
							{
								operacoes.add(guarda_valores.charAt(i));
								
								valores.add(Double.parseDouble(guarda_valores.substring(aux, i))); 
								System.out.print("\noperacao"+cv+": "+guarda_valores.charAt(i)+"\tvalor("+cv+") = "+valores.get(cv));
								aux = i+1;
								cv++;
							}
						valores.add(Double.parseDouble(guarda_valores.substring(aux,  guarda_valores.length())));
						System.out.print("\n\t\tvalor("+cv+") = "+valores.get(cv)+"\t tamanho do array de valores = "+valores.size());
						for (int i = operacoes.size(); i > 0; i--)
						{
							if (valores.size() > 0) {
								switch (operacoes.get(i-1)) {
								case '+':
									valores.set((cv-1), (valores.get(cv-1) + valores.get(cv) ));
									valores.remove(cv);	
									break;
								case '-':
									valores.set((cv-1), (valores.get(cv-1) - valores.get(cv) ));
									valores.remove(cv);
									break;
								case '*':
									valores.set((cv-1), (valores.get(cv-1) * valores.get(cv) ));
									valores.remove(cv);
									break;
								case '/':
									valores.set((cv-1), (valores.get(cv-1) / valores.get(cv) ));
									valores.remove(cv);
									break;
								default:
								}
								cv--;
							}
						}
						expressao.setText(String.valueOf(valores.get(0)));
						valores.clear();
						operacoes.clear();
						cv = 0;
						aux = 0;
					}
					if (e.getActionCommand() == "C") {
						guarda_valores = "";
						expressao.setText(guarda_valores);
						cv = 0;
						aux = 0;
						valores.clear();
						operacoes.clear();
					}
				}
			});
		}
	}
	public static void main(String[] args) {

		Calculadora2 gridLayoutFrame = new Calculadora2();

		gridLayoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gridLayoutFrame.setSize(475, 200);

		gridLayoutFrame.setVisible(true);

	}

}