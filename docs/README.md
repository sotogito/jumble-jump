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