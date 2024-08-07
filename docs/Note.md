- Map : List<List<Integer>>
- MoveChecker(s) - 이동할 수 있는지, 뒤로갈 수 있는지
- GameServer : 좌표값 저장????????????? - Map, UserMoveManager, GameManager
- UserMoveManager : 이동한 곳 저장, count, 좌표, 방향 impl MoveCalculator
- GameManager : 4면이 다 막혔을대 
- MoveCalculator : dx,dy,좌표 왼쪽으로 이동하는 계산 로직

#### Enum
- 북,동,남,서 - 0,1,2,3
- 육지 바다 - 0, 1


---
1. Map 크기 생성
2. UserMoveManager 좌표, 방향 생성
3. Map List<List<Integer>> 업데이트

4. UserMoveManager 방향 왼쪽으로 회전 업데이트
5. GameServer에서 다음 좌표 계산 후 MoveChecker로 이동핤 있는지 판단
6. 이동할 수 있으면 UserMoveManager 위치 업데이트
7. 아니면 그냥 회전만

8. 이동할 수 없다면 뒤로이동 좌표 구한후 MoveChecker로 이동핤 있는지 판단
9. 게임 반복