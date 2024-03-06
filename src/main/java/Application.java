import domain.Blackjack;
import domain.Deck;
import domain.Player;
import domain.Players;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {

        String[] names = InputView.inputPlayers();

        Players players = Players.from(names);
        Player dealer = new Player("딜러");
        Deck deck = new Deck();
        Blackjack blackjack = new Blackjack(players, dealer, deck);

        blackjack.initializePlayers();
        blackjack.initializeDealer();
        OutputView.printPlayersStatus(blackjack.getPlayers().getPlayers());

        // 참가자 게임진행
        for (var player : blackjack.getPlayers().getPlayers()) {
            while (player.alive() && InputView.tryPoll(player.getName())) {
                blackjack.dealCardsToPlayer(player);
            }
            OutputView.printPlayerStatus(player);
        }

        Player dealer1 = blackjack.getDealer();
        blackjack.dealCardsToPlayer(dealer1);

        OutputView.printPlayerStatus(dealer1);

        // 결과 출력
        List<Player> players1 = blackjack.getPlayers().getPlayers();
        Player dealer2 = blackjack.getDealer();
        players1.add(dealer2);
        OutputView.printResults(players1);

        // 승패 출력 맨~
        Map<Player, Entry<Integer, Integer>> playerIntegerMap = blackjack.finishGame();
        OutputView.printGameResults(playerIntegerMap);
    }
}
