// 最小建構示範；先只帶兩個資料，完整三欄設計留給 MissionStep 作業。
public class StepCard {
    private int command;
    private int energy;

    public StepCard(int command, int energy) {
        this.command = command;
        this.energy = energy;
    }

    public int getCommand() {
        return command;
    }

    public int getEnergy() {
        return energy;
    }
}
