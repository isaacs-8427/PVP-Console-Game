public class IceMage extends Ranged {
    public IceMage() {
        super("Ice Mage", 75, 5);
    }

    public void attack(Player enemy, GameState state, Scanner sc) {
        if (!safeRange(state)) {
            System.out.println("Too close!");
            return;
        }

        if (Game.rollDodge(enemy)) return;

        enemy.hp -= 40;
        if (!state.controlUsedThisTurn) {
            enemy.frozen = true;
            state.controlUsedThisTurn = true;
        }
        ultCharge++;
    }

    public void ultimate(Player enemy, GameState state) {
        if (safeRange(state)) {
            int cap = (int)(enemy.maxHP * 0.6);
            enemy.hp -= Math.min(90, cap);
            enemy.frozen = true;
            ultCharge = 0;
        }
    }
}
