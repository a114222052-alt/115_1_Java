# 第四週學生實作：火星探測車任務日誌

姓名：杜婉綺　學號：A114222052　實際上課日期：115/10/30　實際 JDK：openjdk version "21.0.13.0.2" 2026-10-22 LTS；javac 21.0.13.0.2

## A｜取回與先備診斷

1. 資料夾：`115_1_Java-main/115_10_30/java-04/src`，以 `javac -encoding UTF-8 -d out` 編譯。
2. 放入 ArrayBasics、ArrayReference、ListBasics、CollectionsDemo、CommandCounter 與 starter/MissionLog.java。
3. `java -version`：openjdk version "21.0.13.0.2" 2026-10-22 LTS；`javac -version`：javac 21.0.13.0.2。
4. 編譯成功；起始 MissionLog 輸出 `counts[0..3]=[0, 0, 0, 0]`、`TODO 4: summary`、`TODO 5: lowest energy`，最終 `final energy=6, samples=5, actionCount=3 -> SUCCESS`，是預期的未完成起始狀態。

診斷：第三週 {1,1,2} 的最終能源/樣本/成功行動次數：6 / 5 / 3（本週實測）。{2,1,1} 是否相同：相同，6 / 5 / 3（推算，未實跑）。只有最終三個數字，能否分辨路線：不能，不同路線可得到相同最終數字，需要逐步日誌。

## B｜固定資料：陣列與計數

| 項目 | 預測 | 本人實際結果 |
|---|---|---|
| script.length | 未事先預測 | 3 |
| new int[4] 內容 | 未事先預測 | [0, 0, 0, 0] |
| new String[2] 內容 | 未事先預測 | [null, null] |
| 逐步指令輸出 | 未事先預測 | step 1: 1；step 2: 1；step 3: 2 |
| 指令數值加總（不是行動數） | 未事先預測 | sum=4（1+1+2；行動數是 3 次，兩者不同） |

1. 陣列合法索引範圍是：0 到 length-1（長度 3 就是 0～2）。
2. 在副本取消越界行註解（scratch 副本，已恢復 src 原檔），編譯並執行，錯誤：`ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3`，位置 ArrayBasics.java:23。
3. CommandCounter 腳本 {3,9,0,1,1,-1}，預測 counts：不知道（當時未能預測）；實際：[1, 2, 0, 1]。9 和 -1 為何不得當索引：counts 只有 4 格，合法索引是 0～3，9 超過範圍、-1 是負數，拿來當索引會越界出錯。
4. CommandCounter 固定資料改為 {0,2,2,3}，預測：[1, 0, 2, 1]；實際：[1, 0, 2, 1]；已恢復原示範 {3,9,0,1,1,-1}。
5. 在 `for (int command : script)` 內寫 command=0，是否改掉 script 的元素：預測「會」；實際「不會」，script 仍為 [3, 9, 0, 1]（用 scratch 副本 LoopCopy 驗證）。command 是元素值的複製，改複製不影響陣列；要改元素需用 script[i]=0。

## C｜參考與方法

| 時間點 | 預測 energy 內容 | 本人實際 |
|---|---|---|
| alias[0]=19 後 | [20, 14, 8]（預測錯誤） | [19, 14, 8] |
| changeFirst 後 | 未能預測 | [18, 14, 8] |
| replaceLocal 內的新陣列 | 未能預測 | [20, 20, 20] |
| 回到 main 後 | 未能預測 | [18, 14, 8] |
| == 與 Arrays.equals 的兩個結果 | 未能預測 | false 與 true |

Java 傳值；陣列參數取得的是：參考值的副本，和呼叫端指向同一個陣列。為何修改元素看得見，重新指定參數卻不改呼叫端：改元素是透過副本改同一個陣列，所以呼叫端看得到；`values = new int[]{...}` 只是讓方法內的變數改指向新陣列，呼叫端變數沒有改。

## D｜筆數會增加：ArrayList

| 項目 | 預測 | 本人實際 |
|---|---|---|
| 原清單/size/first | [1, 1, 2] / 3 / 1 | [1, 1, 2] / 3 / 1 |
| set(1,3) 後 | [1, 3, 2] | [1, 3, 2] |
| remove(0) 後 | [3, 2] | [3, 2] |
| remove(Integer.valueOf(3)) 後 | [2] | [2] |
| isEmpty() | false | false |

int 放入清單會裝箱，讀成 int 會拆箱；ArrayList<int> 為何不能編譯：泛型的型態參數必須是參考型態，int 是基本型態。副本重現訊息：`ListBasics.java:20: error: unexpected type … required: reference, found: int`（scratch 副本，src 原檔未動）。

## E｜新增、回放、計數

{3,9,0} 的逐步追蹤：

| 步 | 指令 | 預測結果/執行後能源 | 本人實際結果/能源 |
|---|---|---|---|
| 1 | 3 | 未事先預測 | ALREADY_FULL / 20 |
| 2 | 9 | 未事先預測 | UNKNOWN_COMMAND / 20 |
| 3 | 0 | 未事先預測 | RETURN / 20 |

此時 commandLog/energyLog/resultLog 各有幾筆：各 3 筆。actionCount 是幾次：0 次。差異原因：日誌記錄每一步嘗試，包含被拒絕、無效與返回；actionCount 只算成功改變狀態的行動，所以筆數與成功行動次數不同。

六條本人測試（初始 20/0；六條放在同一次執行，每條用 `##### script=…` 標示，完整輸出存於 six_tests_output.txt，貼在下方）：

| script | 預期狀態/結果 | 本人實際狀態/結果 | counts、摘要、每筆回放均相符？ |
|---|---|---|---|
| {0} | 20/0/0 RETURNED | 20/0/0 RETURNED；counts=[1,0,0,0]；steps=1, ok=0, rejected=0, ignored=0 | 是 |
| {1,1,2} | 6/5/3 SUCCESS | 6/5/3 SUCCESS；counts=[0,2,1,0]；steps=3, ok=3, rejected=0, ignored=0 | 是 |
| {2,2,2,2,2} | 10/5/5 SUCCESS | 10/5/5 SUCCESS；counts=[0,0,5,0]；steps=5, ok=5, rejected=0, ignored=0 | 是 |
| {3,9,0} | 20/0/0 RETURNED | 20/0/0 RETURNED；counts=[1,0,0,1]；steps=3, ok=0, rejected=2, ignored=0 | 是 |
| {1,1,1,1} | 2/6/3 SUCCESS | 2/6/3 SUCCESS；counts=[0,3,0,0]；steps=3, ok=3, rejected=0, ignored=1 | 是 |
| {2,1} | 12/3/2 UNFINISHED | 12/3/2 UNFINISHED；counts=[0,1,1,0]；steps=2, ok=2, rejected=0, ignored=0 | 是 |

完整輸出：##### script=[0]
=== replay ===
step 1: command=0, result=RETURN, energy=20
counts[0..3]=[1, 0, 0, 0]
steps=1, ok=0, rejected=0, ignored=0
lowest energy=20
final energy=20, samples=0, actionCount=0 -> RETURNED
battery=100.0
##### script=[1, 1, 2]
=== replay ===
step 1: command=1, result=OK, energy=14
step 2: command=1, result=OK, energy=8
step 3: command=2, result=OK, energy=6
counts[0..3]=[0, 2, 1, 0]
steps=3, ok=3, rejected=0, ignored=0
lowest energy=6
final energy=6, samples=5, actionCount=3 -> SUCCESS
battery=30.0
##### script=[2, 2, 2, 2, 2]
=== replay ===
step 1: command=2, result=OK, energy=18
step 2: command=2, result=OK, energy=16
step 3: command=2, result=OK, energy=14
step 4: command=2, result=OK, energy=12
step 5: command=2, result=OK, energy=10
counts[0..3]=[0, 0, 5, 0]
steps=5, ok=5, rejected=0, ignored=0
lowest energy=10
final energy=10, samples=5, actionCount=5 -> SUCCESS
battery=50.0
##### script=[3, 9, 0]
=== replay ===
step 1: command=3, result=ALREADY_FULL, energy=20
step 2: command=9, result=UNKNOWN_COMMAND, energy=20
step 3: command=0, result=RETURN, energy=20
counts[0..3]=[1, 0, 0, 1]
steps=3, ok=0, rejected=2, ignored=0
lowest energy=20
final energy=20, samples=0, actionCount=0 -> RETURNED
battery=100.0
##### script=[1, 1, 1, 1]
=== replay ===
step 1: command=1, result=OK, energy=14
step 2: command=1, result=OK, energy=8
step 3: command=1, result=OK, energy=2
counts[0..3]=[0, 3, 0, 0]
steps=3, ok=3, rejected=0, ignored=1
lowest energy=2
final energy=2, samples=6, actionCount=3 -> SUCCESS
battery=10.0
##### script=[2, 1]
=== replay ===
step 1: command=2, result=OK, energy=18
step 2: command=1, result=OK, energy=12
counts[0..3]=[0, 1, 1, 0]
steps=2, ok=2, rejected=0, ignored=0
lowest energy=12
final energy=12, samples=3, actionCount=2 -> UNFINISHED
battery=60.0
。繳交前已恢復 {1,1,2}（預設輸出存於 final_default_output.txt）。

## F｜摘要、需求與反例

### F1 Collections 與 TODO 4–5

CollectionsDemo 預測 min/max/frequency：6 / 20 / 1；實際：min=6、max=20、frequency(8)=1，與預測一致。排序後 original：[14, 8, 20, 6]；sorted：[6, 8, 14, 20]。為何不能只排序 energyLog 來做回放：三條清單靠同一個編號對應同一步，只排序 energyLog 會讓同一編號的指令、結果與能源對不上，例如第 1 步會顯示 energy=6 而不是 14，所以要複製到另一條清單再排序。

教師示範案例（「事後實跑」是我用完成版 MissionLog 自己跑的結果，不是事先預測；教師實際示範觀察欄未觀察，仍標待補）：

| 初始 energy/samples；script | 預測結果/日誌筆數/最低能源 | 教師實際示範觀察 |
|---|---|---|
| 5/0；{1,3,0} | 未事先預測；事後實跑：RETURNED，日誌 3 筆（NOT_ENOUGH_ENERGY、OK、RETURN），最低能源 5 | 待補 |
| 2/4；{2} | 未事先預測；事後實跑：SUCCESS，日誌 1 筆（OK），最低能源 0 | 待補 |
| 0/0；{3,1} | 未事先預測；事後實跑：EXHAUSTED，日誌 0 筆，ignored=2，最低能源 N/A | 待補 |
| 20/5；{1} | 未事先預測；事後實跑：SUCCESS，日誌 0 筆，ignored=1，最低能源 N/A | 待補 |
| 20/0；{} | 未事先預測；事後實跑：UNFINISHED，日誌 0 筆，ignored=0，最低能源 N/A | 待補 |

### F2 Prompt 設計

| 要交代的需求 | 本人的內容 |
|---|---|
| 欄位與型態 | 每一步有 command（int）、energy（int）、result（String） |
| 筆數是否事先固定 | 否，每執行一步增加一筆，事先不知道會有幾筆 |
| 是否保留順序 | 是，回放要依步驟順序 |
| 是否容許重複 | 是，同一指令、能源值、結果都可能重複 |
| 新增、回放、計數與最低值等操作 | 每步新增一筆；用編號回放；統計指令 0–3 的次數；找每步結束後的最低能源 |
| 允許的技術與驗證案例 | 只用 ArrayList、int[]、Collections，不用 HashSet 或自訂類別；驗證用 {1,1,2} 與 {3,9,0} |

完整 Prompt：我在寫一個火星探測車任務日誌的 Java 程式。每一步要記錄 command（int）、執行後 energy（int）、result（String）。筆數不固定、要保留順序、允許重複。請用 ArrayList 與 int[]（不要用 HashSet 或自訂類別），完成：每步新增、依編號回放、統計指令 0–3 的次數、找出最低能源。請用 {1,1,2} 與 {3,9,0} 說明如何驗證。

| 建議 | 採納/拒絕/補充條件與理由 |
|---|---|
| 歷程最多用 int[100] 保存就好 | 補充條件：可用，但要另外記有效筆數並處理超過 100 筆的情況；日誌會逐步成長、將來可能改回互動輸入，所以本週用 ArrayList 較合適，不能一概說陣列無法記錄歷程 |
| ArrayList 保存日誌，HashSet 保存指令，避免重複 | 拒絕：任務可以重複探索，順序與重複次數都要保留，HashSet 會去掉重複，無法完整回放 |

### F3 模擬 AI 除錯二選一

選建議 1：刪掉 countCommands 的範圍檢查，理由「指令只有 0–3」。

| 證據 | 本人紀錄 |
|---|---|
| 選哪種建議與反例；預期行為 | 選建議 1；反例 {3,9,0}；預期 counts=[1,0,0,1]，steps=3, ok=0, rejected=2, ignored=0，結果 RETURNED |
| 修改前實際結果/錯誤訊息 | 回放 3 步正常印出，統計時 `Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 9 out of bounds for length 4 at MissionLog.countCommands(MissionLog.java:92)` |
| 最小修正與採納/拒絕理由 | 拒絕此建議並恢復 `if (command >= 0 && command < counts.length)`；理由：無效指令 9 仍會被記進日誌，沒有檢查就會用 9 當 counts 索引而越界 |
| 修正後反例實際結果 | counts=[1, 0, 0, 1]；steps=3, ok=0, rejected=2, ignored=0；final energy=20, samples=0, actionCount=0 -> RETURNED |
| 修正後 {1,1,2} | final energy=6, samples=5, actionCount=3 -> SUCCESS；counts=[0,2,1,0]；steps=3, ok=3, rejected=0, ignored=0；lowest energy=6（見 final_default_output.txt） |
| 修正後 {3,9,0} | 同上方「修正後反例實際結果」，亦見 six_tests_output.txt |

（備份：F3 前已把正確版複製為 MissionLog_correct_backup.java。）

## G｜課末檢核與第五週入口

1. length、size()、length() 分別用於什麼：`length`（無括號）用於陣列；`size()` 用於 ArrayList；`length()` 用於字串 String。
2. 三筆日誌是否必定代表三次成功行動：不是。日誌記錄每一步嘗試；例如 {3,9,0} 有 3 筆日誌，但 actionCount 是 0。
3. 三條清單漏加其中一筆會發生什麼：回放時用同一個編號讀三條清單，缺少的那條會在最後一筆越界，丟出 IndexOutOfBoundsException，且編號對應也會錯位。（預測時我選了「讀到空白」，答案是錯的。）
4. 為何固定四種指令計數用陣列，逐步增加的日誌用 ArrayList：counts 的筆數固定為 4，陣列剛好；日誌每一步都會增加，事先不知道筆數，ArrayList 才能一直 add。
5. 下週如何把同一步三個欄位整合在一起：做一個代表「一步」的類別，把 command、energy、result 包在一起，清單只存這個物件；本週不要求寫類別。

## H｜保存、推送與取回

- 恢復預設腳本（{1,1,2}）並重新編譯執行：已完成，`counts[0..3]=[0, 2, 1, 0]`、`final energy=6, samples=5, actionCount=3 -> SUCCESS`、`battery=30.0`。
- LAB_REPORT、AI_USAGE、專案 README：已完成。
- commit、push，核對 GitHub main 最新 commit 與來源檔：待填。

GitHub repo/作業目錄：(https://github.com/a114222052-alt/115_1_Java)；最終 commit：bd145a6。
本人新副本 JDK/編譯/預設輸出：待填，未做標示待補。
