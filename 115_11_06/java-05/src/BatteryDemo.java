public class BatteryDemo {
    public static void main(String[] args) {
        RoverBattery low = new RoverBattery(5);
        System.out.println("explore=" + low.explore() + ", energy=" + low.getEnergy());
        System.out.println("charge=" + low.charge() + ", energy=" + low.getEnergy());
        System.out.println("explore=" + low.explore() + ", energy=" + low.getEnergy());
        RoverBattery edge = new RoverBattery(19);
        System.out.println("cap=" + edge.charge() + ", energy=" + edge.getEnergy());
        System.out.println("full=" + edge.charge() + ", energy=" + edge.getEnergy());
    }
}
