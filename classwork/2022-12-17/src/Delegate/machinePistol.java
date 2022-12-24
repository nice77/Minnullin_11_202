package Delegate;

public class machinePistol extends Rifle {
    public machinePistol(Shooter pistolShooter) {
        super(pistolShooter);
    }
    public void shoot() {
        this.shooter.shoot();
    }
}
