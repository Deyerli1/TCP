import java.util.Random;

public abstract class Obstaculo {
	protected final double x,y;
	protected String sprite;
	

	public Obstaculo(double x, double y) {
    	this.x = x;
    	this.y = y;
    }
    
    public static Obstaculo crearObstaculo(double x, double y) {
		int min = 1;
        int max = 2;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        
        if(value == 1) {
        	return new Pozo(x,y);
        }else {
        	return new ManchaAceite(x,y);
        }
	}
    public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}