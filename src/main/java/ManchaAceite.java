
import java.util.*;

public class ManchaAceite extends Obstaculo {

    public ManchaAceite() {
    }

    public void desestabilizarAuto(Auto auto, double deltaTime) {
        auto.desestabilizar(deltaTime);
    }

}