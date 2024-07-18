### List<Token> 을 만들기 위한
- 계산을 위해 필요한 요소들 = 숫자 + 연산자 + 괄호
  - 이것들을 하나의 Token에 implements해야함

#### 헌데!! 인터페이스로 선언하면 그 인터페이스에 정의된 메서드만 사용할 수 있음 
-> 그래서 각각 요소에 맞춤화된(필요한 행동을 시킬 수 있는) Token의 하뉘 인터페이스를 하나 더 정의해야됨

- 숫자 : NumberToken - `getNumber` 숫자를 가쟈옴
- 연산자 : OperatorToken - `calculate` num1, num2를 받아서 각자 연산을 수행함
- 괄호 : ParenthesisToken - `getPriority` 소,중,대괄호의 우선순위를 받아옴
