package road_fighter.objects;

public class LataCerveza extends Obstaculo{
    public LataCerveza(double x, double y) { 
    	super((int)y, "file:src/main/resources/img/obstaculos/DuffSixPack.png");
    	this.x = x;
    	this.y = y+50;
    }
}
