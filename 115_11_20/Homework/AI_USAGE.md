# 第七週AI使用紀錄

本範本由Codex生成。另存當日日期目錄AI_USAGE.md，刪除虛構範例後填本人紀錄。沒有使用真實AI也交，來源註明教師模擬，並填本人驗證；未完成就寫待補。

姓名／學號：杜婉綺/A114222052
工具及來源：Claude（Anthropic，claude.ai對話，真實AI）

## 精簡紀錄

| 使用目的與Prompt摘要（註明來源） | 採納／拒絕與理由 | 本人驗證：預期、實際、證據位置 | 最終修改與commit |
|---|---|---|---|
| 【真實AI：Claude】請它逐步帶我完成TODO 1～3：Rover的spendEnergy並重構explore、scan；HeavyRover的drillCount與drill | 採納：spendEnergy先檢查再扣，drill先isStopped再spendEnergy、成功才更新，皆符合講義規格。我的scan原本保留舊寫法且成本寫成1，經指出後改成spendEnergy(2)、addSamples(1)、countAction() | 預期：HeavyRoverTry十五行全相符、六條舊路線與講義表格一致。實際：全部相符，script已恢復{1,1,2}。自設反例HeavyRover(16,0)連續drill三次，得成功8/3/1/1、成功0/6/2/2、任務已停止0/6/2/2，與預期相同。證據：終端機輸出、heavy_try_output.txt、my_try_output.txt | 待填（尚未commit） |

真實AI對話另列一行，寫工具與採納理由，不把模擬回覆當成真實對話。記錄可引用LAB_REPORT的完整輸出，不重抄十五行。不得上傳個資、帳號密碼或未授權程式。

## 反思

- 哪個案例讓我發現「回成功卻沒更新正確狀態」：自設的HeavyRover(16, 0)連續drill三次。只看回傳「成功」不夠，還要同時檢查能源、樣本、行動次數、鑽探次數是否一起更新；若drill只回成功卻沒更新，第二次鑽探後鑽探次數不會是2，第三次也不會被擋下。實際結果為成功8/3/1/1、成功0/6/2/2、任務已停止0/6/2/2，與預期相同。
- 我保留或拒絕AI建議的理由：保留Claude的寫法，因為符合講義規格，且我用HeavyRoverTry、六條舊路線與自設反例驗證過；自己寫的scan曾出錯，所以不能只看能不能編譯。本次沒有拒絕AI建議。