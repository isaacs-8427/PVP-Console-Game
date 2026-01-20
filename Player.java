import java.util.*;

public abstract class Player {
    protected String name;
    protected int maxHP;
    protected int hp;
    protected int baseSpeed;
    protected int ultCharge;

    protected boolean bleeding;
    protected boolean burning;
    protected boolean frozen;
    protected boolean dazed;

    public Player(String name, int maxHP, int baseSpeed) {
        this.name = name;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.baseSpeed = baseSpeed;
        this.ultCharge = 0;
    }

    public abstract void attack(Player enemy, GameState state, Scanner sc);
    public abstract void ultimate(Player enemy, GameState state);

    public int getCurrentSpeed() {
        int speed = baseSpeed;
        if (dazed) {
            speed = baseSpeed - 3;
            if (speed < 0) speed = 0;
        }
        return speed;
    }

    public int getMoveDistance() {
        if (frozen) return 0;
        if (getCurrentSpeed() >= 7) return 2;
        return 1;
    }

    public int getDodgeChance() {
        return getCurrentSpeed() * 3;
    }

    public void applyStatusEffects() {
        if (bleeding) hp -= 3;
        if (burning) hp -= 5;
    }

    public void endTurnCleanup() {
        frozen = false;
        dazed = false;
    }

    public boolean isAlive() {
        return hp > 0;
    }
}
