#### ooooo(oo)ooo
1. 소괄호 start인지
2. index 등록
2. start와 똑같은 종류의 괄호의 end인지
3. index등록
3. for문 index 변경
4. 괄호 안의 siez가 0이 될때 까지;


할라했는데.. 알고리즘이따로 있다.

### 셋이 정리 알고리즘과 후위 표기법
1. 수식 토큰화 : 크게 숫자, 연산자, 괄호로 데이터를 분리한다.
2. 셋이 정리 알고리즘을 사용하여 중위 표기법을 후위 표기법으로 변환
3. 스택을 사용하여 후위 표기법으로 표현된 수식을 계산

### 후위 표기법
- 8+7(8-7)/2
1. 8+7*87-/2
2. 8+87-7*/2
3. 8+87-7*2/
4. 87-7*2/8+

### 스택 큐
- 스택 : 후입선출 (LIFO,Last-In-First-Out)
  - 1,2,3 -(pop)> 3,2,1
- 큐 : 선입선출 (FIFO, First-In-First_Out)
  - 1,2,3 -(poll)> 1,2,3

### 사칙연산 계산하기
- 위에 식을 후위 표기법으로 최종 변환하여 계산의 우선순위를 따져 보면 으래와 같은 식이 나온다
```text
[8, 7, 8, 7, -, 2, /, +]
```
- 그럼 이 식으로 계산을 이어 나가면 된다. operator를 만나면 스택으로 꺼낸 첫번째가 num2, 그 다음이 num1이 된다.

### 괄호의 처리
- 만약에 괄호start가 나오면 중간저장List에 담아둔다.
- 괄호 end가 나오면 중간저장 List에 저장된 요소들을 괄호 start가 나올때까지 후위저장List에 담는다.

### 괄호 유효검사
#### 괄호도 stack 으로 관리하면?
- open은 담고 close는 leftParenthesisCount만큰 count해서 확인?

- 처음
  - open인지
  
- before = open : 담아야됨
  - open -> open일때
    - isNextOpend인지 = 우선순위가 -1인지
  - open ->  close일때
    - before(가장 최근의 open) 하고 종류가 같은 괄호인지 - 옳바른 식이라면 우선순위가 높은 괄호가 먼저 닫힌다.
    - rightParenthesisCount가 1인데 우선순위가 1이 아니면 - 중첩되지 않은 괄호인데 소괄호가 아닐때
    
- before = close 일떄 : 빼내야됨
  - close -> close 일때
    - inNextClose인지 = 우선순위가 +1인지
    - 충첩이 끝났는지 확인하고 초기화하기
  - close -> open
    - rightParenthesisCount하고 leftParenthesisCount이 다른데(괄호 섹션이 끝나지 않았는데)
      - 괄호의 우선순위가 다를 때
    
- 끝나고
  - 첫 괄호인데 우선순위가 1이 아닐 때
  - 마지막에 Open괄호의 수와 close 괄호의 수가 다를 때
    - 만약 같은게 확인되면 초기화하기 - 중첩 괄호는 끝남