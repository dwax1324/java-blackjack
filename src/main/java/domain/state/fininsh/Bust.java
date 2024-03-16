package domain.state.fininsh;

import domain.card.Hands;
import domain.player.Result;
import domain.state.State;

public final class Bust extends Finished {
    public Bust(final Hands hands) {
        super(hands);
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public Result compareWith(final State otherState) {
        if (!otherState.isFinished()) {
            throw new IllegalStateException();
        }
        final Finished otherFinished = (Finished) otherState;

        if (otherFinished.isBust()) {
            return Result.WIN;
        }
        return Result.LOSE;
    }
}
