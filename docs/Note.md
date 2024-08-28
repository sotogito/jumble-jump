





---
- Number : 숫자
- Clap : 박수 : int 박수 몇개인지 숫자로 표시
- Token(i) : 숫자와 박수에 적용 - 하나로 묶음

- Numbers : List<Number(Token)>
- 369Tokens : List<Token> : 박수 변환 결과


- InputNumberParser
- ClapMaker :ClapFormatter, :  박수 생성
  - 여기서 Token 객체를 생성

- 369GameService : Numbers, 369Tokens, ClapMaker

- ClapFormatter : '짝'으로 변환해주고 뒤에 !