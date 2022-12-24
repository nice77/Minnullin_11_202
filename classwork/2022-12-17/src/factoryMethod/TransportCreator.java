package factoryMethod;

public class TransportCreator {
    public static Transport transportCreator(int code) {
        Transport transport = null;
        if (code == 0) { transport = new SportCar(); }
        else if (code == 1) { transport = new Truck(); }
        return transport;
    }
    public static Transport[] garageCreator(int size) {
        Transport[] garage = new Transport[size];
        for (int i = 0; i < size; i++) {
            garage[i] = transportCreator((int) Math.round(Math.random()));
        }
        return garage;
    }
}
