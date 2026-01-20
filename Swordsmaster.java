public class Swordsmaster extends Melee {
    public Swordsmaster() {
        super("Swordsmaster", 100, 7);
    }

    public void attack(Player enemy, GameState state, Scanner sc) {
        System.out.println("1) Slash  2) Thrust");
        int choice = sc.nextInt();

        if (choice == 1 && inRange(state, 1)) {
            if (Game.rollDodge(enemy)) return;
            enemy.hp -= 25;
            enemy.bleeding = true;
            ultCharge++;
        } else if (choice == 2 && inRange(state, 2)) {
            if (Game.rollDodge(enemy)) return;
            enemy.hp -= 35;
            ultCharge++;
        } else {
            System.out.println("Out of range!");
        }
    }

    public void ultimate(Player enemy, GameState state) {
        if (inRange(state, 2)) {
            int cap = (int)(enemy.maxHP * 0.6);
            enemy.hp -= Math.min(60, cap);
            enemy.bleeding = true;
            ultCharge = 0;
        }
    }
}
