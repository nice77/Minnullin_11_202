package Delegate;

public class PistolShooter implements Shooter{
    @Override
    public void shoot() {
        System.out.println("Pistol shoots! Pew-pew!");
    }
}
