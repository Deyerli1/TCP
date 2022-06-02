package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import road_fighter.Config;

public class Cordon extends Obstaculo {
        
    public Cordon(int x) {
        super(Config.baseHeight, "file:src/main/resources/img/cordon.png");
        Image caminoImage = new Image("file:src/main/resources/img/cordon.png", 800, 20, false, false);
        render = new ImageView(caminoImage);
        render.relocate(x,Config.baseHeight);
        render.setViewOrder(9);
        
        collider = new Rectangle(x,-Config.baseHeight*15,20, 80000);
        collider.setFill(null);
        collider.setStroke(Color.FUCHSIA);
        collider.setStrokeWidth(2);
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