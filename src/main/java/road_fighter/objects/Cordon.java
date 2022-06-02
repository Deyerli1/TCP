package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.shape.Shape;
import road_fighter.Config;

public class Cordon extends Obstaculo {
        
    public Cordon(int x) {
        super(-(int)(Config.baseHeight*24.3), x, "file:src/main/resources/img/cordon.png", 20000, 0);
    }

    @Override
    public void update(double deltaTime) {
        // TODO Auto-generated method stub
    }

    @Override
    public Node getRender() {
        return render;
    }

    @Override
    public Shape getCollider() {
        return collider;
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }
}