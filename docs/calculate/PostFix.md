### 숫자 + 연산자
숫자와 연산자만 있는 사칙연신 의 후위 표기법에서 주의할 점은 연산자의 우선 순위이다.
- output에 우선순위가 높은 연산자가 앞에 있어야한다.
- stack에 우선순위가 높은 우선순위가 뒤에 있어야한다. - 먼저 나오기 때문
  - - + *를 넣는다고하면 - + * 로 들어가고 나로때는 * + -순으로 나온다.

#### 예시
```text
3 + 4 * 2 - 1
```
초기 설정:
output: []
operatorStack: []

토큰 3 처리:
output: [3]
operatorStack: []

토큰 + 처리:
output: [3]
operatorStack: [+]

토큰 4 처리:
output: [3, 4]
operatorStack: [+]

토큰 * 처리:
output: [3, 4]
operatorStack: [+, *]

토큰 2 처리:
output: [3, 4, 2]
operatorStack: [+, *]

토큰 - 처리:
output: [3, 4, 2, *]
operatorStack: [-]

토큰 1 처리:
output: [3, 4, 2, *, +, 1]
operatorStack: [-]

마지막 단계:
output: [3, 4, 2, *, +, 1, -]
operatorStack: []

---
여기서 주의해야될 구간은 바로 -이다.
- 자신보다 우선순위가 높은 연산자가 있을 때
- 자신과 같은 우선순위 연산자가 있을 때 (먼저 계산되는 연산자)

1. -의 우선순위는 *보다 낮으므로 operatorStack에서 *를 꺼내어 output에 추가
2. -의 우선순위는 +와 같으므로 operatorStack에서 +를 꺼내어 output에 추가
3. -를 operatorStack에 추가
4. 나머지 로직 후위표기법으로 처리
5. 끝났으니 operatorStack연산자를 pop해서 output에 순서대로 저장