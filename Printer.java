import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class Printer extends JFrame 
{ 
	private GridBagLayout layout; 
	private GridBagConstraints constraints; 
	
	public Printer()
	{
		super( "Printer" );
		layout = new GridBagLayout();
		setLayout( layout ); 
		constraints = new GridBagConstraints(); 
		constraints.anchor = GridBagConstraints.WEST;
		
		// coluna 1 (components em ordem de cima p/ baixo)
		JLabel myPrinter = new JLabel (" Printer: MyPrinter", 2);		
		JList listaVazia1 = new JList();
		listaVazia1.setFixedCellWidth(55);
		listaVazia1.setFixedCellHeight(8);
		JLabel printQuality = new JLabel ("    Print Quality:");
		
		constraints.fill = GridBagConstraints.NONE;
		addComponent( myPrinter, 1, 1, 2, 1 ); 
		addComponent( printQuality, 5, 1, 2, 1 ); 
		addComponent( new JScrollPane(listaVazia1), 2, 1, 1, 3 ); 
		
		
		//coluna 2	
		JCheckBox image = new JCheckBox ("Image");
		JCheckBox text = new JCheckBox ("Text");
		JCheckBox code = new JCheckBox ("Code");
		String[] quality = { "High", "Avg", "Low" };
		JComboBox qualityCB = new JComboBox( quality );

		constraints.fill = GridBagConstraints.NONE;
		addComponent( image, 2, 2, 1, 1 ); 
		addComponent( text, 3, 2, 1, 1 ); 
		addComponent( code, 4, 2, 1, 1 );
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		addComponent( qualityCB, 5, 2, 2, 1 );
		constraints.anchor = GridBagConstraints.WEST;
		
		//coluna 3
		JList listaVazia2 = new JList();
		listaVazia2.setFixedCellWidth(35);
		listaVazia2.setFixedCellHeight(8);
		addComponent( new JScrollPane(listaVazia2), 2, 3, 1, 3 ); 
		
		// coluna 4	
		JRadioButton selection = new JRadioButton ("Selection");
		JRadioButton all = new JRadioButton ("All");
		JRadioButton applet = new JRadioButton ("Applet");
		ButtonGroup radio = new ButtonGroup();
		radio.add(selection);
		radio.add(all);
		radio.add(applet);
		JCheckBox ptf = new JCheckBox ("Print to File");
		
		addComponent( selection, 2, 4, 1, 1 ); 
		addComponent( all, 3, 4, 1, 1 ); 
		addComponent( applet, 4, 4, 1, 1 );
		constraints.anchor = GridBagConstraints.CENTER;
		addComponent( ptf, 5, 4, 2, 1 );
		constraints.anchor = GridBagConstraints.WEST;

		//coluna 5		
		JList listaVazia3 = new JList();
		listaVazia3.setFixedCellWidth(55);
		listaVazia3.setFixedCellHeight(8);
		
		addComponent( new JScrollPane(listaVazia3), 2, 5, 1, 3 );
		
		//coluna 6
		JButton okBt = new JButton( "OK" );
		JButton cancelBt = new JButton( "Cancel" );
		JButton setupBt = new JButton( "Setup..." );
		JButton helpBt = new JButton( "Help" );
		
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(0,15,0,0);
		addComponent( okBt,		0, 7, 1, 2 ); 
		addComponent( cancelBt, 2, 7, 1, 2 ); 
		addComponent( setupBt,  4, 7, 1, 1 );
		constraints.insets = new Insets(11,15,0,0);
		addComponent( helpBt,   5, 7, 1, 2 ); 

	}

	private void addComponent( Component component,
			int row, int column, int width, int height )
	{
		constraints.gridx = column; 
		constraints.gridy = row; 
		constraints.gridwidth = width; 
		constraints.gridheight = height;  
		layout.setConstraints( component, constraints ); 
		add( component ); 
	} 


	public static void main( String[] args )
	{ 
		Printer print = new Printer(); 
		print.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		print.setSize( 510, 205 ); // set frame size
		print.setVisible( true ); // display frame
	} // 
} 

