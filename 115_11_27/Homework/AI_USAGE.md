# 第八週AI使用與驗證紀錄

學生姓名／學號：杜婉綺 A114222052。一次主要活動填一列即可；沒有真實AI也能用課堂模擬完成。不要上傳帳密、個資或未授權內容。

| 使用目的與來源 | Prompt／建議摘要 | 採納或拒絕及理由 | 人工驗證方式與結果 | 最終修改 |
|---|---|---|---|---|
| 真實AI：Claude（claude.ai），2026/11/27，逐步完成week08 starter的TODO 1～4 | 上傳week08教材，請它一步一步帶我完成TODO 1～4。建議：Rover.explore改用exploreYield()；HeavyRover覆寫protected int exploreYield()回傳3；CompareMissions各建一台Rover型態的車與新Mission；HeavyRover覆寫execute，指令4交給drill()，其餘用super.execute | 採納：四項都符合講義規格，且不改Mission、不重抄父類別switch、不用if判斷車種。本週沒有拒絕AI建議 | 預期與實際：HeavyRoverTry十五行全相符；DrillMissionTry十四行全相符；MissionLog仍為最終能源6、樣本5、行動次數3、任務成功；CompareMissions一般車6/5/3、電量30.0，重型車8/6/2、步數2、略過1、電量40.0。證據：終端機輸出（以Select-String數相符行數，得15與14） | Rover.java：addSamples(exploreYield())；HeavyRover.java：新增exploreYield覆寫與execute覆寫；CompareMissions.java：建立兩台車、兩個Mission，各show一次 |

本人E1預測：本週未做E段（模擬AI審查），待補。
修正前commit／證據：本週未做E段，待補。
修正後commit／相同案例重測：本週未做E段，待補。
三至五句補強Prompt：本週未做E段，待補。

若另用真實AI，新增一列，記工具及日期，分開說明哪些建議採納、哪些由本人修改。AI宣稱通過不算人工驗證。
