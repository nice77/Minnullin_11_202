package Delegate;

public abstract class Weapon {
    Shooter shooter;
    private int clipSize;
    private double caliber;
    public Weapon(Shooter shooter, int clipSize, double caliber) {
        this.shooter = shooter;
        this.clipSize = clipSize;
        this.caliber = caliber;
    }
}
