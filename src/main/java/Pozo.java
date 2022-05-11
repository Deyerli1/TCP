
import java.util.*;

public class Pozo extends Obstaculo {

    public Pozo() {
    }

    public void desestabilizarAuto(Auto auto, double deltaTime) {
        auto.desestabilizar(deltaTime);
    }

}