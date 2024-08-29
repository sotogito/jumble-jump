- **_난이도 : ⭐️️️️️_**
- **_엉망친창 완성 시간 : 2시간 25분_**
- **_커밋 : 약 50회_**

## 공부가 된 부분
- 추상화 계층 분리 연습 - ClapMaker,서비스
- 모듈화 - ClapCounter
- 인터페이스 다향성 - Clap, NumberT, Token

## 주요 기능 플로우
1. 입력받은 String을 List<숫자>로 분리
2. List<숫자> 를 오름차순으로 정렬
3. 각 List<숫자>의 숫자를 분리해서 일의자리로 나눔
4. 3,6,9 Enum 상수가 들어가이는 List에 분리한 일의자리 숫자가 포함되어있는지 확인하고 박수 개수를 업데이트
5. 박수 숫자가 1개 이상이면 Clap Token으로 분류, 아니면 NumberT Token으로분류 후 List<Token>으로 반환
6. List<Token>을 for문으로 돌려서 출력물 List 생성 - NumberT는 바로넣음, Clap은 박수 개수 만큼 formatting
7. 출력

## 신경쓴 점
### 1. 숫자, 박수 인터페이스 다향성  
게임은 크게 숫자와 박수 타입으로 나뉜다.  
이들을 하나의 인터페이스로 묶어 동일한 리스트에 담을 수 있도록 설계했다.  
또한,Enum으로 숫자, 박수 타입을 상수로 정의하고 각 클래스에서 타입을 get 해올 수 있게 하였다.  
그 결과 동일한 리스트에서 각 타입을 구분 가능하도록 설계했다. 
- 부모 인터페이스 : Token
  - 자식1 인터페이스 : NumberT -> NumberImpl
  - 자식2 인터페이스 : Clap -> ClapImpl

### 2. 오류는 빠르게 던져라
숫자를 입력받고 확인해야하는 사항은 다음과 같다.
- 숫자인지
- 숫자범위
- 입력된 숫자 개수
- 중복 확인  

항상 유효검사의 위치에 대해서 고민이 많았다.

        객체 생성자에서 할 것인가(객체 or 레퍼지토리) vs 객체 생성 전에 할 것인가(InputNumberParser)

지금은 후자로 마음을 정했다.
만약 데이터가 여러 방향에서 들어오면 객체에 가깝게 유효검사를 처리한느게 맞지만,  
데이터가 input에서만 들어온다면 input에서 가깝게 처리하는게 불필요한 처리를 피한다고 생각한다.
- 숫자인지 : input
- 숫자범위 : InputNumberParser
- 입력된 숫자 개수 : InputNumberParser
- 중복 확인 : InputNumberParser

### 3. 주석이 아닌 메서드로 설명하라
아래는 수정 전 코드이다.
```java
    public List<Token> makeClapList(Numbers numbers) {
        List<Token> result = new ArrayList<>();

        for (NumberT numberToken : numbers.getNumbers()) {
            int clapCount = getClapCount(numberToken);

            if (clapCount > 0) {
                result.add(new ClapImpl(clapCount)); //note 박수로 넣음
                continue;
            }
            result.add(numberToken); //note 그냥 숫자로 넣음
        }
        return result;
    }
```
박수, 숫자 Token을 구분해서 List<Token>에 추가하는 로직이다.  
각 추가 로직을 주석으로 설명했다.  
  

갑자기 떠오른 생각은 코드를 설명하는 일차적인 '`메시지`'란 무엇인가였다.  
        **_그건 바로 `메서드`!! 였다._**  
지금까지 메시지를 전달하기 위해 직관적인 메서드 명명에 진심이였는데, 저렇게 주석을 달아놨던거다.  

!!! 그래서 주석 대신에 메서드로 따로 분리해서 코드를 설명했다.

```java
    public List<Token> makeClapList(Numbers numbers) {
        List<Token> result = new ArrayList<>();

        for (NumberT numberToken : numbers.getNumbers()) {
            int clapCount = getClapCount(numberToken);

            if (isClap(clapCount)) {
                handleClapToken(result,clapCount);
                continue;
            }
            handleNumberToken(result,numberToken);
        }
        return result;
    }

    private void handleClapToken(List<Token> result, int clapCount){
        result.add(new ClapImpl(clapCount));
    }

    private void handleNumberToken(List<Token> result,NumberT numberToken){
        result.add(numberToken);
    }

    private boolean isClap(int clapCount){
        return clapCount > 0;
    }
```
주석 없이 메서드만으로 코드를 설명할 수 있게 되었다.

+. 문제가 쉬우니 커밋 횟수도 늘어났다. 예상하는대로 구현할 수 있으니 구현 순서에 꼬임이 없고,  
특정 로직 구현을 바로 끝낼 수 있어 그런 거 같다.  
복잡한 로직에서 커밋메시지를 남기는 연습이 필요하다.

## 의문점
- 바로 위 코드에서, new ClapImpl()으로 박수 객체를 생성하여 List<Token.>에 넣으면 그건 Clap이 아니라 Token 인터페이스 implement 클래스 아닐까?  
아니다.  

        public class ClapImpl implements Clap {
ClapImpl을 보면 Token의 자식 인터페이스, Clap으로 implement 되어있다.  
때문에 List<Token.>에 객체를 생성해서 넣는다고 부모 인터페이스(Token)으로써 들어가는게 아니다.
자식 인터페이스(Clap)으로 들어가기 때문에 자식 인터페이스에 정의된 메서드를 사용할 수 있다.
