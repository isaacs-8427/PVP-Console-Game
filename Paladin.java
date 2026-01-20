public class Paladin extends Melee {
    public Paladin() {
        super("Paladin", 125, 4);
    }

    public void attack(Player enemy, GameState state, Scanner sc) {
        if (inRange(state, 2)) {
            if (Game.rollDodge(enemy)) return;
            enemy.hp -= 40;
            if (!state.controlUsedThisTurn) {
                enemy.dazed = true;
                state.controlUsedThisTurn = true;
            }
            ultCharge++;
        } else {
            System.out.println("Out of range!");
        }
    }

    public void ultimate(Player enemy, GameState state) {
        if (inRange(state, 3)) {
            int cap = (int)(enemy.maxHP * 0.6);
            enemy.hp -= Math.min(70, cap);
            enemy.dazed = true;
            ultCharge = 0;
        }
    }
}
