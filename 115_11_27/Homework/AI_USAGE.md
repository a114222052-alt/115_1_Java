# 第八週AI使用與驗證紀錄

學生姓名／學號：杜婉綺/A114222052。一次主要活動填一列即可；沒有真實AI也能用課堂模擬完成。不要上傳帳密、個資或未授權內容。

| 使用目的與來源 | Prompt／建議摘要 | 採納或拒絕及理由 | 人工驗證方式與結果 | 最終修改 |
|---|---|---|---|---|
| 課堂Code Review，教師設計的模擬AI（week08/ai/HeavyRover.java） | 讓重型車探索得3份；建議在子類別新增同名private explore，宣稱execute會自動使用它 | 拒絕此做法；父類別private方法不會被子類別覆寫，execute仍走Rover自己的explore，產量仍為2 | 先預測可編譯但產量不對；HeavyRoverTry的「探索後」與「完成後狀態維持」顯示「不符」（6/5/2/1，預期6/6/2/1），共2行不符；加@Override編譯失敗（method does not override or implement a method from a supertype）；修正後同入口十五行全相符、不符0行。證據：Homework/ai-check/ai_check_output.txt、ai_check_e3_error.txt、ai_check_fixed_output.txt | 移除無效的private explore，改覆寫protected int exploreYield()回傳3，保留drill |
| 真實AI：Claude（claude.ai），2026/11/27，逐步完成week08 starter的TODO 1～4 | 上傳week08教材，請它一步一步帶我完成TODO 1～4。建議：Rover.explore改用exploreYield()；HeavyRover覆寫protected int exploreYield()回傳3；CompareMissions各建一台Rover型態的車與新Mission；HeavyRover覆寫execute，指令4交給drill()，其餘用super.execute | 採納：四項都符合講義規格，且不改Mission、不重抄父類別switch、不用if判斷車種。本週沒有拒絕Claude的建議 | 預期與實際：HeavyRoverTry十五行全相符；DrillMissionTry十四行全相符；MissionLog仍為最終能源6、樣本5、行動次數3、任務成功；CompareMissions一般車6/5/3、電量30.0，重型車8/6/2、步數2、略過1、電量40.0。證據：終端機輸出（以Select-String數相符行數，得15與14） | Rover.java：addSamples(exploreYield())；HeavyRover.java：新增exploreYield覆寫與execute覆寫；CompareMissions.java：建立兩台車、兩個Mission，各show一次。commit：fcf6598 |

本人E1預測：能編譯；drill()後再execute(1)，預測samples為2；預測execute(1)走HeavyRover的explore()。實際：能編譯；samples為5；走的是Rover的explore()，第2、3題預測不對。
修正前commit／證據：e9b4eb7；證據：Homework/ai-check/ai_check_output.txt（兩行不符）、ai_check_e3_error.txt（@Override編譯錯誤）。
修正後commit／相同案例重測：41c7f5c；同一份HeavyRoverTry重測，十五行全相符、不符0行，證據：Homework/ai-check/ai_check_fixed_output.txt。
三至五句補強Prompt：請以Rover現有契約為準，需求是HeavyRover探索一次得3份樣本，成本與停止規則不變。請先檢查父類別是否提供可覆寫的protected方法，只能覆寫該方法，不得新增同名private方法。每個覆寫方法都必須加@Override，並說明為什麼它會被父類別流程呼叫。請提供預期的HeavyRoverTry結果，例如探索後應為6/6/2/1，讓我可以自行驗證。若不確定父類別是否有該方法，請先問我，不要猜。

若另用真實AI，新增一列，記工具及日期，分開說明哪些建議採納、哪些由本人修改。AI宣稱通過不算人工驗證。
