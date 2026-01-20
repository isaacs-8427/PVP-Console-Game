public abstract class Ranged extends Player {
    public Ranged(String name, int hp, int baseSpeed) {
        super(name, hp, baseSpeed);
    }

    protected boolean safeRange(GameState state) {
        return state.distance >= 2;
    }
}
