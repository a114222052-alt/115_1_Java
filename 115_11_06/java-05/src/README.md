# 四個核心示範

學生複製本目錄六個 Java 檔到同一 src。StepCard、RoverBattery 是資料／行為類別；其餘四檔含 main。不要直接執行不含 main 的類別。

| 順序 | 入口與相依檔案 | 觀察重點 |
|---|---|---|
| B | CreateStepDemo + StepCard | 兩個 new、獨立欄位、有參數建構子與 getter |
| C | BatteryDemo + RoverBattery | 私有能源、成功與拒絕、充電封頂 |
| D | ReferenceDemo + RoverBattery | 共用參考、傳值、修改物件與重新指定參數 |
| D | SnapshotDemo + StepCard + RoverBattery | 行動後擷取 int 快照，每步建立新記錄 |

在學生 java-05 內編譯 `javac -encoding UTF-8 -d out src/*.java`，再依序執行上述四個入口。單獨測試 examples 時，在本週教材目錄使用 `javac -encoding UTF-8 -d out-examples examples/*.java`，然後以 out-examples 為 classpath。

## 預期輸出

CreateStepDemo：

```text
first=1/14
second=2/12
same object=false
```

BatteryDemo：

```text
explore=false, energy=5
charge=true, energy=10
explore=true, energy=4
cap=true, energy=20
full=false, energy=20
```

ReferenceDemo：

```text
after alias=14/20
same reference=true
after spend=8
inside replacement=1
after replace=8
```

SnapshotDemo：

```text
command=1, energy=14
command=3, energy=19
current=19
separate records=true
```

RoverBattery 建構前提為 0..20；未提供契約外參數的防護，不宣稱 private 自動檢查數值。其 explore 只示範能源變動，不保存樣本或任務是否結束；getEnergy 是查詢，不會扣電。StepCard 沒有 setter，對外只讀；這不代表清單本身不能增刪。
