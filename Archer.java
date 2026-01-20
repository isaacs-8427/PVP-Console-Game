public class Archer extends Ranged {
    public Archer() {
        super("Archer", 90, 6);
    }

    public void attack(Player enemy, GameState state, Scanner sc) {
        if (!safeRange(state)) {
            System.out.println("Too close!");
            return;
        }

        System.out.println("1) Quickshot  2) Chargeshot");
        int choice = sc.nextInt();

        if (Game.rollDodge(enemy)) return;

        if (choice == 1) enemy.hp -= 18;
        else enemy.hp -= 45;

        ultCharge++;
    }

    public void ultimate(Player enemy, GameState state) {
        if (safeRange(state)) {
            int cap = (int)(enemy.maxHP * 0.6);
            enemy.hp -= Math.min(80, cap);
            ultCharge = 0;
        }
    }
}
