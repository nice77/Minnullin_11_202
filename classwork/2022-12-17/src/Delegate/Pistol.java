package Delegate;

public class Pistol extends Weapon {
    public Pistol(Shooter pistolShooter) {
        super(pistolShooter, 7, 9);
    }
    public void shoot() {
        this.shooter.shoot();
    }
}
