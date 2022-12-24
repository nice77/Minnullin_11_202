package Delegate;

public class Rifle extends Weapon {
    private boolean modeAuto;
    public Rifle(Shooter rifleShooter) {
        super(rifleShooter, 45, 5.45);
        this.modeAuto = true;
    }
    public void shoot() {
        this.shooter.shoot();
    }
    public void setMode() {
        this.modeAuto = !this.modeAuto;
    }
}
