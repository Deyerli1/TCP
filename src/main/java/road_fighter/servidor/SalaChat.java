package road_fighter.servidor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import road_fighter.RoadFighterGame;
import road_fighter.objects.Reproductor;
import road_fighter.servidor.Cliente;
import road_fighter.servidor.MensajeAServidor;
import road_fighter.servidor.Sala;
import road_fighter.utils.MyTextArea;

public class SalaChat extends JFrame {

	private static final long serialVersionUID = -4289720049025252601L;
	private JPanel contentPane;
	private JTextField textFieldEscrituraMensaje;
	private JTextArea textArea;
	private Cliente cliente;
	private String nombre;
	private JButton btnEnviar;
	private JButton btnJugar;
	private Sala sala;
	private boolean salaPrivada;
	

	public SalaChat(Sala sala, Cliente cliente) {
		this.sala = sala;
		this.cliente = cliente;
		this.salaPrivada = sala.isPrivada();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				cerrarSala();
			}
		});
		if (salaPrivada) {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		} else {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		setBounds(100, 100, 450, 300);
		this.nombre = sala.getNombreSala();
		this.setTitle("Usuario: " + cliente.getNombre() + " - Nombre de la sala: " + nombre);
		this.setIconImage(new ImageIcon("src/logo.png").getImage());
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuListaConexion = new JMenu("Opciones");
		menuBar.add(menuListaConexion);

		JMenuItem menuItemSalirSala = new JMenuItem("Salir de la sala");
		menuItemSalirSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JMenuItem mntmNewMenuItem = new JMenuItem("Ver tiempos de sesion");
		menuListaConexion.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verTiemposSesion();
			}
		});

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Descargar conversacion");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarConversacion();
			}
		});
		menuListaConexion.add(mntmNewMenuItem_1);
		if (salaPrivada == false) {
			JMenuItem mntmNewMenuItem_2 = new JMenuItem("Crear sala privada...");
			mntmNewMenuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//crearSalaPrivada();
				}
			});
			menuListaConexion.add(mntmNewMenuItem_2);

		}
		menuListaConexion.add(menuItemSalirSala);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		textFieldEscrituraMensaje = new JTextField();
		textFieldEscrituraMensaje.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					enviarMensaje();
				}
			}
		});
		panel.add(textFieldEscrituraMensaje);
		textFieldEscrituraMensaje.setColumns(10);
		
		
		btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarJuego();
			}
		});
		panel.add(btnJugar, BorderLayout.NORTH);
		

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMensaje();
			}

		});
		panel.add(btnEnviar, BorderLayout.EAST);

		textArea = new MyTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setText("Has ingresado a la sala"
				+ (salaPrivada ? "\nEsta sala se cerrara si uno de los dos usuarios sale de ella." : ""));
		mostrarHistorial(textArea);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));
		JScrollPane scroll = new JScrollPane(textArea);
		contentPane.add(scroll, BorderLayout.CENTER);
		setLocationRelativeTo(cliente.getLobby());
		setVisible(true);
	}

	protected void iniciarJuego() {
		// mensaje tipo 8:pide la lista de usuarios al servidor
		MensajeAServidor msj = new MensajeAServidor(cliente.getNombre(), sala, 9);
		cliente.enviarMensaje(msj);
	}
	


	protected void guardarConversacion() {
		JFileChooser fileChooser = new JFileChooser();
		int seleccion = fileChooser.showOpenDialog(this);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File ruta = fileChooser.getSelectedFile();
			String texto = textArea.getText();

			FileWriter fr = null;
			PrintWriter pw = null;

			try {
				fr = new FileWriter(ruta + ".txt");
				pw = new PrintWriter(fr);
				pw.print(texto);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fr != null) {
					try {
						fr.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	protected void verTiemposSesion() {
		// mensaje tipo 7:pide la lista de tiempos al servidor
		MensajeAServidor msj = new MensajeAServidor(cliente.getNombre(), sala, 7);
		cliente.enviarMensaje(msj);
	}

	protected void cerrarSala() {
		// mensaje tipo 5:salir de sala
		// mensaje tipo 10:salir de sala privada
		int tipoMensaje = salaPrivada ? 10 : 5;
		MensajeAServidor msj = new MensajeAServidor(cliente.getNombre(), sala, tipoMensaje);
		cliente.enviarMensaje(msj);
	}

	private void enviarMensaje() {
		String msj = textFieldEscrituraMensaje.getText();
		if(msj.equals("/salir")) {
			MensajeAServidor msjAServidor = new MensajeAServidor(msj, sala, 0);
			cliente.enviarMensaje(msjAServidor);
			textFieldEscrituraMensaje.setText("");
		}
		
		if (!msj.equals("")) {
			msj = cliente.getNombre() + ": " + textFieldEscrituraMensaje.getText();
			// mensaje tipo 6:envio de mensaje
			MensajeAServidor msjAServidor = new MensajeAServidor(msj, sala, 6);
			cliente.enviarMensaje(msjAServidor);
			textFieldEscrituraMensaje.setText("");
		}

	}
	
	public String getNombreSala() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalaChat other = (SalaChat) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalaChat [ nombre=" + nombre + "]";
	}

	public void mostrarMensaje(String mensaje) {
		textArea.setText(textArea.getText() + "\n" + mensaje);
	}

	public void mostrarTiempos(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Tiempos de sesion", JOptionPane.INFORMATION_MESSAGE);
	}

	public void mostrarListaUsuarios(String mensaje) {
		Lobby lobby = cliente.getLobby();
		boolean puedeCrear = lobby.verificarCantidadSalas();

		if (!puedeCrear) {
			return;
		}

		String[] opciones = mensaje.split("\n");
		if (opciones.length > 1) {
			List<String> listaOpciones = new LinkedList<String>(Arrays.asList(opciones));
			String nombreCliente = cliente.getNombre();
			listaOpciones.remove(nombreCliente);
			opciones = listaOpciones.toArray(new String[listaOpciones.size()]);
			String resp = (String) JOptionPane.showInputDialog(null, "Seleccione usuario:", "Crear sala privada",
					JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);
			if (resp != null) {
				String[] participantes = { cliente.getNombre(), resp };
				String nombreSalaACrear = "Sala privada (" + participantes[0] + ";" + participantes[1] + ")";
				String nombreSalaACrearAlternativo = "Sala privada (" + participantes[1] + ";" + participantes[0] + ")";
				boolean salaAbierta1 = lobby.isSalaAbierta(nombreSalaACrear);
				boolean salaAbierta2 = lobby.isSalaAbierta(nombreSalaACrearAlternativo);

				if (salaAbierta1 || salaAbierta2) {
					return;
				}
				// mensaje tipo 9:creacion de sala privada
				Sala salaPrivada = new Sala(nombreSalaACrear, true);
				long tiempoInicioSesion = System.currentTimeMillis();
				salaPrivada.agregarUsuario(participantes[0], tiempoInicioSesion);
				salaPrivada.agregarUsuario(participantes[1], tiempoInicioSesion);
				MensajeAServidor msj = new MensajeAServidor(null, salaPrivada, 9);
				cliente.enviarMensaje(msj);
			}
		} else {
			JOptionPane.showMessageDialog(this, "No hay mas usuarios en la sala", "No se puede crear sala privada",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void mostrarHistorial(JTextArea textArea) {
		List<String> historial = sala.getHistorialMensajes();
		if(historial.isEmpty())
			return;
		textArea.setText(textArea.getText() + "\n");
		for (String msj : historial) {
			
			textArea.setText(textArea.getText() + msj);	
			if(!msj.equals(historial.get(historial.size() - 1))) {
				textArea.setText(textArea.getText() + "\n");				
			}
		}
	}
	
	
}

