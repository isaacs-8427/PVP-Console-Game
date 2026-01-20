public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameState state = new GameState();

        Player p1 = choosePlayer(sc, 1);
        Player p2 = choosePlayer(sc, 2);

        while (p1.isAlive() && p2.isAlive()) {
            Player first;
            Player second;

            if (p1.getCurrentSpeed() >= p2.getCurrentSpeed()) {
                first = p1;
                second = p2;
            } else {
                first = p2;
                second = p1;
            }

            takeTurn(first, second, state, sc);
            if (!second.isAlive()) break;
            takeTurn(second, first, state, sc);

            p1.applyStatusEffects();
            p2.applyStatusEffects();

            p1.endTurnCleanup();
            p2.endTurnCleanup();
            state.resetTurn();

            System.out.println("------------------");
            System.out.println(p1.name + " HP: " + p1.hp);
            System.out.println(p2.name + " HP: " + p2.hp);
            System.out.println("Distance: " + state.distance);
        }

        System.out.println("Game Over");
    }

    private static Player choosePlayer(Scanner sc, int num) {
        System.out.println("Choose Player " + num + ":");
        System.out.println("1) Swordsmaster  2) Paladin  3) Archer  4) Fire Mage  5) Ice Mage");
        int c = sc.nextInt();
        if (c == 1) return new Swordsmaster();
        if (c == 2) return new Paladin();
        if (c == 3) return new Archer();
        if (c == 4) return new FireMage();
        return new IceMage();
    }

    private static void takeTurn(Player active, Player enemy, GameState state, Scanner sc) {
        System.out.println(active.name + "'s turn");
        System.out.println("1) Move  2) Attack  3) Ultimate");
        int choice = sc.nextInt();

        if (choice == 1) {
            int move = active.getMoveDistance();
            if (active instanceof Melee) {
                state.distance = Math.max(0, state.distance - move);
            } else {
                state.distance = state.distance + move;
            }
        } else if (choice == 2) {
            active.attack(enemy, state, sc);
        } else if (choice == 3 && active.ultCharge >= 4) {
            active.ultimate(enemy, state);
        }
    }

    public static boolean rollDodge(Player defender) {
        int roll = new Random().nextInt(100);
        if (roll < defender.getDodgeChance()) {
            System.out.println(defender.name + " dodged the attack!");
            return true;
        }
        return false;
    }
}
