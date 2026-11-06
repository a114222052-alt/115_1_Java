import java.util.ArrayList;

public class SnapshotDemo {
    public static void main(String[] args) {
        RoverBattery battery = new RoverBattery(20);
        ArrayList<StepCard> history = new ArrayList<>();
        battery.explore();
        history.add(new StepCard(1, battery.getEnergy()));
        battery.charge();
        history.add(new StepCard(3, battery.getEnergy()));
        for (StepCard step : history) {
            System.out.println("command=" + step.getCommand() + ", energy=" + step.getEnergy());
        }
        System.out.println("current=" + battery.getEnergy());
        System.out.println("separate records=" + (history.get(0) != history.get(1)));
    }
}
