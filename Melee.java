public abstract class Melee extends Player {
    public Melee(String name, int hp, int baseSpeed) {
        super(name, hp, baseSpeed);
    }

    protected boolean inRange(GameState state, int maxRange) {
        return state.distance <= maxRange;
    }
}
