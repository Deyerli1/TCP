package road_fighter.servidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente{

	String nombre;
	private Socket socket;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Lobby lobby;
	
	public Cliente(String nombre,String ip, int puerto,Lobby lobby) {
		this.nombre=nombre;
		this.lobby=lobby;
		try {
			socket = new Socket(ip, puerto);
			salida=new ObjectOutputStream(socket.getOutputStream());
			salida.flush();
			MensajeAServidor msj= new MensajeAServidor(nombre,null,1);
			enviarMensaje(msj); 

		} catch (UnknownHostException e) {
			System.out.println("Error host desconocido");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error en conexion con el servidor");
			e.printStackTrace();
		}
	}

	public void enviarMensaje(MensajeAServidor mensaje) {
		if(!socket.isClosed()) {
			try {
				salida.reset();
				salida.writeObject(mensaje);
				if(mensaje.getTipo() == 0) {
					System.exit(0);
				}
			} catch (IOException e) {
				System.out.println("Error en envio de mensaje cliente");
				e.printStackTrace();
			}			
		}
	}

	public void inicializarHiloCliente(Lobby ventana) {
		new HiloCliente(socket,entrada, ventana).start();
	}

	public static void main(String[] args) {
		new Lobby();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Lobby getLobby() {
		return lobby;
	}

}

