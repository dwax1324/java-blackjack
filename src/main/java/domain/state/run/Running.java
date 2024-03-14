package domain.state.run;

import domain.player.Hands;
import domain.state.Started;

public abstract class Running extends Started {
    public Running(final Hands hands) {
        super(hands);
    }

    @Override
    public double profit() {
        return 0;
    }
}