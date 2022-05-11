
import java.util.*;

public class Cordon extends Obstaculo {

    public Cordon() {
    }

    public void explotarAuto(Auto auto, double deltaTime) {
        auto.explotar(deltaTime);
    }

}