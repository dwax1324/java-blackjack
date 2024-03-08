package domain;

import java.util.List;

public class Blackjack {
    private final Players players;
    private final Deck deck;

    public Blackjack(final Players players) {
        this.players = players;
        this.deck = new Deck();
        players.getAllPlayers().add(new Dealer());
        dealCardsToPlayers();
    }

    public Blackjack(final Players players, final Player dealer) {
        this.players = players;
        this.deck = new Deck();
        players.getAllPlayers().add(dealer);
    }

    public void dealCard(final Player player) {
        player.hit(deck.draw());
    }

    private void dealCardsToPlayers() {
        players.getAllPlayers().forEach(this::dealInitialCards);
    }

    public BlackjackResult finishGame() {
        final BlackjackRule blackjackResult = new BlackjackRule();
        return blackjackResult.finishGame(getParticipants(), players.getDealer());
    }

    private void dealInitialCards(final Player player) {
        player.hit(deck.draw());
        player.hit(deck.draw());
    }

    public List<Player> getPlayers() {
        return players.getAllPlayers();
    }

    public Player getDealer() {
        return players.getDealer();
    }

    public List<Player> getParticipants() {
        return players.getParticipants();
    }
}
