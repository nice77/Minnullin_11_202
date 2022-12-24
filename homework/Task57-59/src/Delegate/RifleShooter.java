package Delegate;

public class RifleShooter implements Shooter{
    @Override
    public void shoot() {
        System.out.println("Rifle shoots! Pew-pew!");
    }
}
