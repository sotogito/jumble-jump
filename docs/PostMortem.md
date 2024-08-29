2시간 25분


- 전체 리스트에는 숫자_박수가 포함되어있는데 이것을 인터페이스로 하나로 묶고 각자 구분이 간으하게 했다.
- 구분은 Enum으로 숫자, 박수를 정의해서 각 클래스에서 Enum상수를 get해올수 있게 했다.
- 상위 Token 인터페이스에 숫자, 박수 인터페이스를 자식으로 선언했다.

1. String을 List<숫자>로 분리함
2. List<숫자>를 오름차순으로 정렬함
3. List<숫자>를 일의자리로 나눔
3. 3,6,9를 Enum으로 정의하고 List로 반환하여 해당 일의자리 숫자가 포함되는지 확인하고 박수 숫자 업데이트
4. 박수 숫자가 0개 이상이면 박수 횟수 Clap Token으로 넣음, 아닌경우 숫자로 취급하고 Number Token으로 넣어서 List<token>반환
5. List<Token>을 for 무느 돌려서 박수Token인지 숫자Token인지 확인
6. 숫자면 String으로 변환해서 넣고, 박수면 박수 횟수만큼 박 수침

주석을 달지말고 메서드로 설명하라ㅓ
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
주석으로 설명하는것보다는 메시지, 코드에서의 일차적인 메시지를 생각했을때 그건 바로 메서드가 아닐까 생각했다.
그래서 주석을 달아서 구분하기 쉽게하기 보다는 메서드로 따로 분히새ㅓ 메시지를 전하는 방안으로 해결했다.