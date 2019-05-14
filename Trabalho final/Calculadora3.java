
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.lang.Math;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;

public class Calculadora3 extends JFrame {

	private JButton[] buttons; // array de botoes
	private JPanel botoes;
	private GridLayout organizaBotoes;
	private JTextField operacao;
	
	private static final String[] names = { "x!", "e^x", "raiz 2", "7", "8", "9", "/", "raiz 3", "%", "x²", "4", "5",
			"6", "*", "x³", "x^y", "pi", "1", "2", "3", "-", "C", "(", ")", "0", ".", "=", "+" };

	ArrayList<Double> resultadoParcial = new ArrayList<Double>();
	String stringParcial = "";
	String direita = "";
	String esquerda = "";
	String esquerdaI = "";
	Double doubleParcial = 0.0;
	int apagador = 0;
	ArrayList<Character> temporario = new ArrayList<Character>();
	
	// variaveis auxiliares
	private String op = "";
	private double resultado, x, y; //B para guardar o resultado das operações "complexas" (x², x!, etc)
	private boolean enable_xy = false, enable_ex = false;
	

	// construtor sem argumentos

	public Calculadora3() {

		super("Calculadora3");

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

					op = op + e.getActionCommand();

					operacao.setText(op);
					
					//B modifiquei esse teste aqui \/ para poder usar o '=' para calcular as potencias (ver onde calcula x^y, para enteder melhor)
					if (e.getActionCommand() == "=" && ( !enable_xy || !enable_ex ) ) { 

						ScriptEngineManager mgr = new ScriptEngineManager(); //aqui comeca o codigo
						ScriptEngine engine = mgr.getEngineByName("JavaScript");

						try {
							System.out.print("Eval = " + engine.eval(op.substring(0, op.length() - 1)) + "\n\n");
							String s = String.valueOf(engine.eval(op.substring(0, op.length() - 1)));
							operacao.setText(s);
							resultado = Double.parseDouble(s);
						} catch (ScriptException e2) {

							e2.printStackTrace();
						}  // aqui acaba

					}
					if (e.getActionCommand() == "x²") {
						op = op.substring(0, op.length() - 2);
						resultado = Math.pow(Double.parseDouble(op), 2);
						op = String.valueOf(resultado);
						operacao.setText(op);
					}
					
					if (e.getActionCommand() == "x³") {
						op = op.substring(0, op.length() - 2);
						resultado = Math.pow(Double.parseDouble(op), 3);
						op = String.valueOf(resultado);
						operacao.setText(op);
					}
					
					if (e.getActionCommand() == "raiz 2") {
						op = op.substring(0, op.length() - 6);
						if (resultado != 0) {
							resultado = Math.sqrt(resultado);
							op = String.valueOf(resultado);
							operacao.setText(op);
						}
						else
							if (Double.parseDouble(op) >= 0) {
								resultado = Math.sqrt(Double.parseDouble(op));
								op = String.valueOf(resultado);
								operacao.setText(op);
							}
							else
								operacao.setText("Erro: raiz de numero negativo!");
					}
					
					if (e.getActionCommand() == "raiz 3") {
						op = op.substring(0, op.length() - 6);
						if ( resultado != 0 )
							resultado = Math.cbrt(resultado);
						else
							resultado = Math.cbrt(Double.parseDouble(op));
						op = String.valueOf(resultado);
						operacao.setText(op);
					}
					
					if (e.getActionCommand() == "pi") {
						op = op.substring(0, op.length() - 2);
						op += String.valueOf(Math.PI);
						operacao.setText(op);
					}
					
					if (e.getActionCommand() == "x!") {
						op = op.substring(0, op.length() - 2);
						long aux = 1, fat;
						try
				         {
							fat = Integer.parseInt(op);
							if (fat > 0) {
								for (int i = 0; i < fat; i++)
									aux *= fat-i;
								op = String.valueOf(aux);
								operacao.setText(op);
							}
							else
								if (fat == 0) {
									op = String.valueOf(1);
									operacao.setText(op);
								}
								else {
									operacao.setText("Erro: Não existe fatorial de número negativo...");
									op = "";
								}						         
				         }
				         catch (NumberFormatException naoInteiro) //Se naum for um numero inteiro entrará aqui
				         {
				      	    operacao.setText("Erro: numero não é inteiro...");
				      	    op = "";
				         }
					}
					
					if (e.getActionCommand() == "x^y" || enable_xy) { // enable do x^y sempre começa como falso
						if (e.getActionCommand() == "x^y") {		// mas se entrar aqui por ter clicado em x^y
							op = op.substring(0, op.length()-3);	// vai pegar o conteudo da string op -("x^y") 
							x = Double.parseDouble(op);				// e jogar o valor numerico na variavel 'x'
							enable_xy = true;						// habilita o enable para continuar caindo nesse if depois
							op = "";
						}
						else if (e.getActionCommand() == "=") {		// aqui ele vai entrar só depois que digitar todos algarismos de y e apertar '='
							op = op.substring(0, op.length()-1);
							y = Double.parseDouble(op);
							resultado = Math.pow(x, y);
							System.out.println(+x+"^"+y+" = "+resultado);
							op = String.valueOf(resultado);
							operacao.setText(op);
							enable_xy = false;						// no fim reseta o enable para não atrapalhar o '=' do evol
						}									
					}
					
					if (e.getActionCommand() == "e^x" || enable_ex) { // mesma logica do x^y, só que não precisa do primeiro valor pq é a constante 'e'
						if (e.getActionCommand() == "e^x") {
							enable_ex = true;
							op = "";
						}
						else if (e.getActionCommand() == "=") {
							op = op.substring(0, op.length()-1);
							x = Double.parseDouble(op);
							resultado = Math.pow( Math.E, x );
							op = String.valueOf(resultado);
							operacao.setText(op);
							enable_ex = false;
						}									
					}
					
					if (e.getActionCommand() == "C") {
						op = "";
						resultado = 0;
						operacao.setText(op);
					}
					
				}
			});

		}

	}
	public static void main(String[] args) {

		Calculadora3 gridLayoutFrame = new Calculadora3();

		gridLayoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gridLayoutFrame.setSize(475, 200);

		gridLayoutFrame.setVisible(true);

	}

}
