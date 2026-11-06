import java.util.ArrayList;
import java.util.Arrays;

// 第五週只改日誌表示；行動規則與第四週完整輸出保持相同。
public class MissionLog {
    public static void main(String[] args) {
        int[] script = {1, 1, 2};
        runMission(script, 20, 0);
    }

    // 教師測試入口；script 非 null，初始 energy 為 0..20、samples 非負。
    static void runMission(int[] script, int energy, int samples) {
        int actionCount = 0;
        boolean running = true;
        ArrayList<MissionStep> history = new ArrayList<>();

        // 先判斷狀態，能源已耗盡或樣本已達標時，不再執行第一筆指令。
        for (int i = 0; i < script.length && running && energy > 0 && samples < 5; i++) {
            int command = script[i];
            String result;
            switch (command) {
                case 1:
                    if (energy >= 6) {
                        energy -= 6;
                        samples += 2;
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "NOT_ENOUGH_ENERGY";
                    }
                    break;
                case 2:
                    if (energy >= 2) {
                        energy -= 2;
                        samples++;
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "NOT_ENOUGH_ENERGY";
                    }
                    break;
                case 3:
                    if (energy < 20) {
                        energy += 5;
                        if (energy > 20) {
                            energy = 20;
                        }
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "ALREADY_FULL";
                    }
                    break;
                case 0:
                    running = false;
                    result = "RETURN";
                    break;
                default:
                    result = "UNKNOWN_COMMAND";
                    break;
            }
            // 每讀一筆就建立一個新物件，記錄行動執行後的快照。
            history.add(new MissionStep(command, energy, result));
        }

        String outcome;
        if (samples >= 5) {
            outcome = "SUCCESS";
        } else if (energy == 0) {
            outcome = "EXHAUSTED";
        } else if (!running) {
            outcome = "RETURNED";
        } else {
            // 固定腳本用完不等於返回；保留尚未完成這個新結果。
            outcome = "UNFINISHED";
        }
        printReplay(history);
        System.out.println("counts[0..3]=" + Arrays.toString(countCommands(history)));
        printSummary(script.length, history);
        System.out.println("final energy=" + energy + ", samples=" + samples
                + ", actionCount=" + actionCount + " -> " + outcome);
        System.out.println("battery=" + calculateBatteryPercent(energy));
    }

    static int[] countCommands(ArrayList<MissionStep> history) {
        int[] counts = new int[4];
        for (MissionStep step : history) {
            int command = step.getCommand();
            if (command >= 0 && command < counts.length) {
                counts[command]++;
            }
        }
        return counts;
    }

    static void printReplay(ArrayList<MissionStep> history) {
        System.out.println("=== replay ===");
        for (int i = 0; i < history.size(); i++) {
            MissionStep step = history.get(i);
            System.out.println("step " + (i + 1) + ": command=" + step.getCommand()
                    + ", result=" + step.getResult() + ", energy=" + step.getEnergy());
        }
    }

    static void printSummary(int scriptLength, ArrayList<MissionStep> history) {
        int ok = 0;
        int returned = 0;
        for (MissionStep step : history) {
            if (step.isSuccessful()) {
                ok++;
            }
            if ("RETURN".equals(step.getResult())) {
                returned++;
            }
        }
        int steps = history.size();
        int rejected = steps - ok - returned;
        int ignored = scriptLength - steps;
        System.out.println("steps=" + steps + ", ok=" + ok
                + ", rejected=" + rejected + ", ignored=" + ignored);
        if (history.isEmpty()) {
            System.out.println("lowest energy=N/A");
        } else {
            // MissionStep 不是 Integer；讀能源欄位，以已教迴圈求最小值。
            int lowest = history.get(0).getEnergy();
            for (MissionStep step : history) {
                if (step.getEnergy() < lowest) {
                    lowest = step.getEnergy();
                }
            }
            System.out.println("lowest energy=" + lowest);
        }
    }

    static double calculateBatteryPercent(int energy) {
        return energy / 20.0 * 100;
    }
}
