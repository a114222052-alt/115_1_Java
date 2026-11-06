# 第五週 AI 使用紀錄

| 欄位 | 本人紀錄 |
|---|---|
| 使用工具／方式 | 真實 AI：Claude（Anthropic）。未使用教師模擬建議做除錯題。 |
| 目的與提問摘要 | 請 Claude 說明第五週作業要求，並一步一步帶我完成 `MissionStep.java` 的 TODO 1–3 與 `MissionLog.java` 的 TODO 4–6。我自己建立 `java-05/src`、複製老師的 8 個檔案，並在自己的電腦編譯、執行。 |
| 建議與本人處理 | Claude 提供六個 TODO 的程式碼，我把它們貼進對應位置並存檔，內容如下。**TODO 1**：`MissionStep` 建構子用 `this.command = command;` 等三行，把參數存進欄位。**TODO 2**：三個 getter 改成回傳對應欄位，取代佔位值 0 和 "PENDING"。**TODO 3**：`isSuccessful()` 用 `"OK".equals(result)`，比較內容而不是用 `==` 比較參考。**TODO 4**：在 `MissionLog` 的 switch 結束之後，用 `history.add(new MissionStep(command, energy, result))` 保存這一步，因為這時 `result` 才有值，`energy` 才是行動後的能源；拒絕、無效、返回也都記錄。**TODO 5**：`printReplay` 用索引 for 迴圈，`history.get(i)` 取出物件，再用 getter 印出 `step N: command=…, result=…, energy=…`。**TODO 6**：`printSummary` 走訪 history，用 `isSuccessful()` 計算 `ok`，用 `"RETURN".equals(...)` 計算 `returned`。我採納全部建議，未拒絕。 |
| 本人實際驗證 | 以下為我在自己電腦實際編譯執行的結果：`CreateStepDemo` 輸出 `first=1/14`、`second=2/12`、`same object=false`。`MissionLog` 腳本 {1,1,2}：回放能源 14、8、6，`final energy=6, samples=5, actionCount=3 -> SUCCESS`，`battery=30.0`。腳本 {3,9,0}：回放為 ALREADY_FULL、UNKNOWN_COMMAND、RETURN，`steps=3, ok=0, rejected=2`，`final energy=20, samples=0, actionCount=0 -> RETURNED`，`battery=100.0`。測完後 `main` 已改回 {1,1,2}。 |

說明：AI 說程式正確不能代替編譯執行，上面的結果都是我自己跑出來的。F1、F2、F3 等其他課堂活動，我沒有在這份作業中完成，也沒有記錄為已完成。
