package road_fighter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.stage.Stage;

public class MainMenu extends JFrame{
	
	private JButton btnJugar;
	private JPanel contentPane;
	private RoadFighterGame r;

	MainMenu(){
		final JFrame f = new JFrame("Text Area Test");

		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("RoadFighter");
		//this.setIconImage(new ImageIcon("src/logo.png").getImage());
				
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarJuego();
			}
		});
		panel.add(btnJugar, BorderLayout.NORTH);
		
		
	}
	
	public void a() {
		setVisible(true);
	}
	
	public void iniciarJuego() {
		r = new RoadFighterGame();
		r.start(new Stage());
	}
}
