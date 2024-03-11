package domain.player;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    public static Players from(final String[] names) {
        return new Players(Arrays.stream(names)
                .map(name -> new Participant(new Name(name)))
                .collect(Collectors.toList()));
    }

    private void validate(final List<Player> players) {
        validatePlayerNumbers(players);
        validateDuplicate(players);
        validateDealerName(players);
    }

    private void validateDealerName(final List<Player> players) {
        if (players.stream().anyMatch(r -> r.getName().equals(Dealer.getInstance().getName()))) {
            throw new IllegalArgumentException("딜러와 중복되는 이름을 가질 수 없습니다");
        }
    }

    private void validatePlayerNumbers(final List<Player> players) {
        if (isInvalidPlayersNumber(players)) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private boolean isInvalidPlayersNumber(final List<Player> players) {
        return players.size() > 8;
    }

    private void validateDuplicate(final List<Player> players) {
        if (hasDuplicatePlayers(players)) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다");
        }
    }

    public void register(final Player player) {
        getAllPlayers().add(player);
    }

    private boolean hasDuplicatePlayers(final List<Player> players) {
        return Set.copyOf(players).size() != players.size();
    }


    public Player getDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("플레이어 목록에 딜러가 존재하지 않습니다."));
    }

    public List<Player> getParticipants() {
        return players.stream()
                .filter(Player::isParticipant)
                .collect(Collectors.toList());
    }

    public List<Player> getAllPlayers() {
        return players;
    }
}