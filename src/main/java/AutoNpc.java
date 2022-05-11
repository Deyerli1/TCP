
import java.util.*;

public class AutoNpc extends Auto {
	
	protected String nombreNpc;
	
    public AutoNpc(String nombreNpc) {
    	this.nombreNpc = nombreNpc;
    }
    
    public String getNombreNpc() {
    	return nombreNpc;
    }
    
}