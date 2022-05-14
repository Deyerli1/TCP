import java.util.Random;

public abstract class AutoNpc extends Auto {
	
	protected String nombreNpc;
	
    public AutoNpc(String nombreNpc, double[] posicion) {
    	super(posicion, (double)150);
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
        	return new Fijo("Pepito", new double[] {50,50});
        }else {
        	return new Movil("Pepito", new double[] {50,50});
        }
	}
	
	
	
}



















