### figlet을 이용한 출력
- figlet 기본
```java
String asciiArt = FigletFont.convertOneLine("Hello, World!");
```
- .flf 파일
```java
InputStream fontStream = FigletExample.class.getResourceAsStream("/fonts/ours/term.flf");
String asciiArt = FigletFont.convertOneLine(fontStream, "Hello, World");
```

### 플로우
1. 글씨체 예제를 출력한다.
2. 입력하고 싶은 문장을 입력받는다.
3. 글씨체를 입력받는다 - 숫자(id)
4. 글씨체를 출력한다.
5. [그만하기]를 입력하면 프로그램이 종료된다.

### 주요 기능
- 출력 문구를 글씨체로 출력한다.
- [그만하기]를 입력받을 떄까지 입력받는다.

### 유효검사
- 출력 문구
  - 글자는 공백을 포함하여 1~20자인가?
  - 영어인가?
- 글씨체 - 숫자
  - 존재하는 숫자(id)인가
- 그 외의 오류
  - "예기치 않은 오류가 발생하였습니다." 출력