비교는 타겟하고 계산 결과가 해야되고, cutter의 길이는 그냥 정답닏.

- result = 임의의 cutter 절단기 길이(mid) 값으로 구한 떡의 총 길이
- target : 사용자가 요구한 떡의 길이
#### result > target
cutter의 길이가 짧아서 떡의 최종 길이가 target에 비해 비교적 길다.
- cutter의 길이 : short
- 떡의 길이 : long

-> mid 값의 아래 값은 볼 필요 없다.  
-> StartPoint 조정



필요 없는 array의 역할.
알아서 꼐산해주고 stat,end값도 따로 계산을 해주기 떄문에 필요없다.
다만 최대값은 알아야한다