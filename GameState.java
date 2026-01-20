public class GameState {
    int distance = 3;
    boolean controlUsedThisTurn = false;

    public void resetTurn() {
        controlUsedThisTurn = false;
    }
}
