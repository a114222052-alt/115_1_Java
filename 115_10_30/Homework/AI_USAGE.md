# 第四週 AI 使用紀錄

| 欄位 | 記錄 |
|---|---|
| 使用工具 | 真實 AI：Claude（Anthropic）。 |
| 使用目的與提問摘要 | 請 Claude 說明第四週作業要求，並帶我一步一步完成 `MissionLog.java` 的 TODO 1–5：用 `ArrayList` 逐步保存指令、能源、結果，並完成回放、計數與摘要。 |
| AI 建議與本人處理 | Claude 提供五個 TODO 的程式碼，我把它們貼進對應位置並存檔，內容如下。**TODO 2**：在 switch 之後，把 `command`、`energy`、`result` 各 `add` 到三條清單，拒絕、無效與返回也記錄。**TODO 3**：`printReplay` 用同一個索引 `i` 讀三條清單，印出 `step N: command=…, result=…, energy=…`。**TODO 1**：`countCommands` 走訪 `commandLog`，只有指令在 0..3 才增加 `counts[command]`，避免 9 或 -1 造成越界。**TODO 4**：用 `Collections.frequency` 算出 `ok` 與 `returned`，`steps` 是清單筆數，`rejected = steps - ok - returned`，`ignored = scriptLength - steps`。**TODO 5**：先用 `isEmpty()` 判斷，空清單印 `N/A`，否則用 `Collections.min(energyLog)`。我採納全部建議，未拒絕。 |
| 實際驗證結果 | 以下為我在自己電腦實際編譯執行的結果（初始 energy=20、samples=0；狀態順序為 energy／samples／actionCount）：{0} → 20/0/0 RETURNED；{1,1,2} → 6/5/3 SUCCESS，回放能源 14、8、6，counts=[0,2,1,0]；{2,2,2,2,2} → 10/5/5 SUCCESS，counts=[0,0,5,0]；{3,9,0} → 20/0/0 RETURNED，counts=[1,0,0,1]，rejected=2，指令 9 沒有造成越界；{1,1,1,1} → 2/6/3 SUCCESS，ignored=1；{2,1} → 12/3/2 UNFINISHED。測完後 `main` 已改回 {1,1,2}，重新編譯執行仍得 6/5/3 SUCCESS。 |

AI 說程式正確不能代替編譯執行，上面的結果都是我自己跑出來的。
