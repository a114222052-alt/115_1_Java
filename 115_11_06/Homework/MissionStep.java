// 契約與教師版相同。
public class MissionStep {
    private int command;
    private int energy;
    private String result;

    public MissionStep(int command, int energy, String result) {
        this.command = command;
        this.energy = energy;
        this.result = result;
    }

    public int getCommand() {
        return command;
    }

    public int getEnergy() {
        return energy;
    }

    public String getResult() {
        return result;
    }

    public boolean isSuccessful() {
        return "OK".equals(result);
    }
}