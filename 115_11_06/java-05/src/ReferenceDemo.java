public class ReferenceDemo {
    public static void main(String[] args) {
        RoverBattery first = new RoverBattery(20);
        RoverBattery alias = first;
        RoverBattery other = new RoverBattery(20);
        alias.explore();
        System.out.println("after alias=" + first.getEnergy() + "/" + other.getEnergy());
        System.out.println("same reference=" + (first == alias));
        spend(first);
        System.out.println("after spend=" + first.getEnergy());
        replaceLocal(first);
        System.out.println("after replace=" + first.getEnergy());
    }

    static void spend(RoverBattery battery) {
        battery.explore();
    }

    static void replaceLocal(RoverBattery battery) {
        battery = new RoverBattery(1);
        System.out.println("inside replacement=" + battery.getEnergy());
    }
}
