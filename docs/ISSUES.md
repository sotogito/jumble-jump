헐랭옴마미갓이럴수가이루러수강ㅂ다이럴수다 sort 하는 기능을 까먹고 좋아라했다.

for 문에서 else를 쓰지 않을거면 continue나 break가 얼마나 중요한지
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