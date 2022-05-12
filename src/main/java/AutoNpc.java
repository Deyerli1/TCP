import java.util.Random;

public class AutoNpc extends Auto {
	
	protected String nombreNpc;
	
    public AutoNpc(String nombreNpc, int[] posicion) {
    	super(posicion);
    	this.nombreNpc = nombreNpc;	
    }
    
    public String getNombreNpc() {
    	return nombreNpc;
    }

	public static AutoNpc crearAutoNpc() {
		int min = 1;
        int max = 10;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        
        if(value < 7) {
        	return new Fijo();
        }else {
        	return new Movil();
        }
        
	}
    
	
}