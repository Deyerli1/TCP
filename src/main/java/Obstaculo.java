import java.util.Random;

public abstract class Obstaculo {
	protected final int x,y;
	protected String sprite;
	
    public Obstaculo(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public static Obstaculo crearObstaculo() {
		int min = 1;
        int max = 2;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        
        if(value == 1) {
        	return new Pozo();
        }else {
        	return new ManchaAceite();
        }
	}
}