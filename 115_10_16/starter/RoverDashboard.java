public class RoverDashboard {
    public static void main(String[] args) {
        String roverName = "赤砂號"; // 可自訂名稱
        char zone = 'A';
        int energy = 7;
        boolean locked = false;

        double batteryPercent = calculateBatteryPercent(energy);
        int explorationCount = 0; // TODO 1：可完成幾次 6 格能源的探索？
        int remainingEnergy = 0; // TODO 2：完成上述探索後剩餘能源。
        boolean validEnergy = false; // TODO 3：0 至 20（包含端點）。
        boolean canExplore = false; // TODO 4：至少 6 格且未鎖定。

        System.out.println("探測車：" + roverName + "；區域：" + zone);
        System.out.println("能源：" + energy + "；電量百分比：" + batteryPercent);
        System.out.println("完整探索次數：" + explorationCount + "；剩餘能源：" + remainingEnergy);
        System.out.println("有效能源：" + validEnergy + "；能否探索：" + canExplore);
    }

    static double calculateBatteryPercent(int energy) {
        // TODO 5：傳回百分比；輸出仍由 main 負責。
        return 0.0;
    }
}
