### 리팩토링
1. calculate 기능 분리
2. 숫자 포맷팅
3. 계산 중간 식 출력
3. output 요구사항
4. 계산 알고리즘 이해하기
5. 



---
8. Number : 정수인지, 숫자 범위
2. Operator
3. Parentheses
4. Count

5. Problem
6. Tokenizer

7. CalculatorService
8. Calculator



---
- Number : 숫자 객체
- Operator  : 연산자 객체
- Parentheses : 괄호 객체
- Count : 계산 횟수

- Problem : 초기 문제를 저장
- Tokenizer : 입력값을 토큰으로 나눔 - List<T>로 반환

- Calculator : 계산하기
- CalculatorService :
  - 중간 문제 누적
  - 횟수
  - Calculator 선언

#### Enum
- 연산자 - OperatorType
- 괄호 - ParenthesisType

### 인터페이스
- CalculatorService 주요 기능 추상화 - 출력을 위한 get기능과 계산 로직 두개
- 

---
### 토큰화 하기
1. 숫자인지, 연산자인지, 괄호인지부터 나누기
   - 숫자 : Number
   - 연산자 : OperatorMatcher에서 Operator(i) = new 연산자(); 객체 받기
   - 괄호 : 객체생성
- Token에 상속된 Operator인터페이스에 적용된 메서드는 사용

- Enum 상수를 반환하게 되면 List<T>에 담지 못해서 토큰화를 하지 못하고 하나로 집합을 시킬 수 없다.
- 그래서 객체로 반환해야 한다.
- 객체를 List<Token>에 저장할 때 Token으로써 저장하는게 아니라 위에 저 3 인터페이스로 저장해야되는거 아니야?

### 계산하기
1. 괄호 찾기 - 괄호 없어질때까지 하기
2. number1, number2 숫자면 저장해두기
3. 연산자 나오면 이 둘 calculate 메서드로 넘겨버리기
4. number1+연산자+number2 자리에 값 넣기
5. 이 과정을 연산자가 없어질때까지 하기