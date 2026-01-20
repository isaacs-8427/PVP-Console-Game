public class FireMage extends Ranged {
    private int buffTurns = 0;

    public FireMage() {
        super("Fire Mage", 50, 5);
    }

    public void attack(Player enemy, GameState state, Scanner sc) {
        if (!safeRange(state)) {
            System.out.println("Too close!");
            return;
        }

        if (Game.rollDodge(enemy)) return;

        int dmg = 45;
        if (buffTurns > 0) {
            dmg = dmg * 2;
            buffTurns--;
        }

        enemy.hp -= dmg;
        enemy.burning = true;
        ultCharge++;
    }

    public void ultimate(Player enemy, GameState state) {
        if (safeRange(state)) {
            int cap = (int)(enemy.maxHP * 0.6);
            enemy.hp -= Math.min(120, cap);
            enemy.burning = true;
            ultCharge = 0;
        }
    }
}
