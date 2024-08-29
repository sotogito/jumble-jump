### 요구사항 구현 실수
처음 엉망진창코드를 제출할때 생각보다 너무 빨리 끝난 느낌이 들어서 살펴보니  
가장 중요한 로직 오름차순 정렬 알고리즘 @@@!11 로직을 구현하지 않았다.  
구현 순서를 업데이트하고 요구사항 문서화 하는 것에 좀 더 신경 써야할 거 같다!

### for 문에서 continue나 break의 중요성 (else를 쓰지 않을 시)
```java
    private void updateTokenResult(){
        for(Token token : tokens369.getTokens()){
            if(token.getGameElement() == GameElement.CLAP){
                int clapCount = (((Clap) token)).getClap();

                tokenResult.add(clapPrintoutFormat(clapCount));
                continue;
            }
            int number = (((NumberT) token)).getNumber();
            tokenResult.add(String.valueOf(number));
        }
    }
```
       ERROR : toekn을 NumberT으로 변환할 수 없다.
위와 같은 오류의 이유는 위의 코드에서 `continue` 를 적어주지 않아서였다.  
- for문을 빠져나가는 조건이 없을 때
- 로직을 순차적으로 돌려야할때 + 조건이 다른 2개 이상의 로직이 있을 때  

continue와 break를 반드시! 적절하게 사용해야한다. 