
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorSelect extends JPanel {

	private BorderLayout border_Layout; 
	private JButton changeColorJButton, okBotao, cancelBt, apagarBt, aumentarBt, diminuirBt;
	private Color color = UIManager.getColor ( "Panel.background" ), corPincel;
	private PaintPanel colorJPanel;
	private JPanel botaoJPanel, btsPaintJPanel, vazioSouth, vazioEast;
		
	private GridLayout g1;
	
	private int tamanhoPincel = 7;
	private int pointCount = 0; 	
	private Point[] points = new Point[10000]; 
	private boolean apagaTudo = false;

	public ColorSelect() {
		
		border_Layout = new BorderLayout(5,5); 
		setLayout(border_Layout);
		
		colorJPanel = new PaintPanel();	
		colorJPanel.setBackground(Color.WHITE);
		
		colorJPanel.addMouseMotionListener(				
					new MouseMotionAdapter() { 
						public void mouseDragged(MouseEvent event) { // armazena coordenadas de arrastar e repinta
							if (pointCount < points.length) {								
								points[pointCount] = event.getPoint(); // localiza o ponto
								pointCount++; 
								repaint(); // repinta JFrame	
							}											
						}
					}
				);
		
		changeColorJButton = new JButton("Cor");
		changeColorJButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent arg0) {
				color = JColorChooser.showDialog(ColorSelect.this, "Escolhe uma cor", color);				
				if (color == null) // configura a cor padrão, se nenhuma cor for retornada
					color = UIManager.getColor ( "Panel.background" );	
				corPincel = color;
			} 
		} 
		); 

		okBotao = new JButton("Ok");
		okBotao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//pintar tudo menos o fundo da area de desenhar
				setBackground(color);	
				vazioSouth.setBackground(color);
				vazioEast.setBackground(color);
				btsPaintJPanel.setBackground(color);
				botaoJPanel.setBackground(color);
			}
		});
		
		cancelBt = new JButton("Cancel");
		cancelBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color padrao = UIManager.getColor ( "Panel.background" );
				setBackground(padrao); 
				vazioSouth.setBackground(padrao);
				vazioEast.setBackground(padrao);
				btsPaintJPanel.setBackground(padrao);
				botaoJPanel.setBackground(padrao);
			}
		});	
		
		apagarBt = new JButton("Apagar tudo");
		apagarBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				apagaTudo = true;
				repaint();
			}
		});	
		
		aumentarBt = new JButton("Aumentar pincel");
		aumentarBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tamanhoPincel++;
			}
		});
		
		diminuirBt = new JButton("Diminuir pincel");
		diminuirBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tamanhoPincel--;
			}
		});
		
		g1 = new GridLayout(3,1, 20, 20);			
		
		botaoJPanel = new JPanel();
		botaoJPanel.setLayout(g1);
		
		botaoJPanel.add(cancelBt);
		botaoJPanel.add(okBotao);
		botaoJPanel.add(changeColorJButton);
		
		btsPaintJPanel = new JPanel( new FlowLayout());
		btsPaintJPanel.add(apagarBt);
		btsPaintJPanel.add(aumentarBt);
		btsPaintJPanel.add(diminuirBt);
		
		vazioSouth = new JPanel(); // só para poder pintar todo o fundo do frame
		vazioEast = new JPanel();
		
		add(btsPaintJPanel, BorderLayout.NORTH);
		add(botaoJPanel, BorderLayout.WEST);		
		add(colorJPanel, BorderLayout.CENTER);	
		add(vazioSouth, BorderLayout.SOUTH);
		add(vazioEast, BorderLayout.EAST);
	}
	
	public class PaintPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // limpa area de desenho
			
			// desenha todos os pontos no array			
			for (int i = 0; i < pointCount; i++) {
				g.setColor(corPincel);
				g.fillOval(points[i].x, points[i].y, tamanhoPincel, tamanhoPincel);
				if (apagaTudo) {	//logica para apagar o desenho usando o botao que ativa um boolean, para interromper esse laço
					super.paintComponent(g);
					pointCount = 0;
					apagaTudo = false;
					break;					
				}
			}
		}		
	}
	
	public static void main(String[] args) {
		JFrame oFrame = new JFrame("ColorSelect");
		ColorSelect colorSel = new ColorSelect();
		oFrame.setSize(800, 600);
		oFrame.setVisible(true);
		oFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oFrame.add(colorSel);
	}
}