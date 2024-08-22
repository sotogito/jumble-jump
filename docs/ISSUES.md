## Open NLP 라이브러리 적용
OPennlp는 는 자연어 처리(Natural Language Processing, NLP)를 위한 자바 기반의 라이브러리로, 
아파치 소프트웨어 재단에서 관리하는 오픈 소스 프로젝트이다. 
이 라이브러리는 텍스트 분석을 위한 다양한 도구를 제공하며, 
특히 자연어 텍스트의 토큰화, 문장 분리, 품사 태깅, 구문 분석, 개체명 인식, 
그리고 의존성 파싱 등 여러 NLP 작업을 수행할 수 있도록 지원한다.

라이브러리를 추가했는데 안된다. 
문제는..바로... 
1. zip 파일을 풀어버린것.
2. 라이브러리 implementation를 잘못해준것

gpt가 bin파일이 필요하다는데 도대체 찾을수가 없었다. 그런데... zip을 풀지 않은 상태가 .bin 아니겠느냐.

    GPT : .bin 파일은 OpenNLP의 훈련된 모델 데이터를 포함하는 이진 파일입니다. 이 파일은 OpenNLP의 내부에서 사용되도록 설계되었으며, 압축 파일처럼 해제하거나 내용을 직접 수정하는 것이 아니라, Java 코드에서 그대로 사용해야 합니다.

#### 왜 압축을 출면 작동이 안될까??
- .bin 파일은 내부적으로 OpenNLP가 필요로 하는 데이터 구조와 메타데이터를 포함한다. 이 파일을 해제하거나 수정하면 데이터 구조가 깨지거나 메타데이터가 손상되어 OpenNLP가 파일을 읽고 처리하는 데 문제가 생기는것이다.
- 파일은 데이터들이 이진 형식으로 저장되어있다. 때문에 이 데이터를 텍스트로 열 경우 파일이 손상될 수 있다.

이러한 이유로 .bin 파일은 그대로 사용해야 하며, Java 코드에서 직접 로드하여 사용하는 것이 올바른 접근법이다.

라이브러리 implementation를 해주는데 계속 안되길래 공홈에서 찾아보았다.
```text
OpenNLP Tools Dependency
To use the OpenNLP Tools define the following dependency:

dependencies {
  implementation group: 'org.apache.opennlp', name: 'opennlp-tools', version: '2.4.0'
}
```
[Gradle Integration](https://opennlp.apache.org/gradle-dependency.html)로 이동해서 코드를 찾아 붙였더니 잘 됐다


## openNLP에서 stanfordNLP로 라이브러리 변경
openNLP를 열심이 희존성으 주입했더니, 문제가 하나 발생했다.  
내가 기획한 로직에 의하면 번역된 문장의 동사가 동사원형이 아닐경우는 원형으로 변경해서 메서드를 명명해야하는데,  
openNLP에는 동사의 시제를 구분할줄만 알고 동사 원혀응로 변경하는 기능은 제공하지 않았다.  

#### 그래서 찾아본 새로운 라이브러리 stanfordNLP
stanfordNLPsms 스탠퍼드 대학의 자연어 처리 그룹에서 개발한 자연어 처리를 위한 강력한 툴킷이다.  
이 라이브러리는 동사 시재를 구분하고 그 동사의 원형값을 가져오는 기능이 포함되어있었다.

    implementation 'edu.stanford.nlp:stanford-corenlp:4.5.4'    
    implementation 'edu.stanford.nlp:stanford-corenlp:4.5.4:models'

라이브러리 의존성을 주입해주었다.


## DeepL 403 오류
DeepL의 변역 코드를 다 작성하고 Run을 해보니 오류403이 뜬다. 
아래 DeepL 에러메시 페이지에 들어가서 찾아보았다.
https://support.deepl.com/hc/en-us/articles/9773964275868-DeepL-API-error-messages  

    오류 403
    인증에 실패했습니다. 
    유효한 auth_key 매개변수를 제공하세요.

나는 분명히 key값을 제대로 집어 넣은거 같은데 key의 문제라고한다.
혹시나 그럴리는 없겠지만 api를 다시 발급받아도 똑같았다.

#### 해결
api 뒤에 :fix라고 되어있는걸 무슨 배짱으로 삭제하고 넣었다..ㅎㅎ 다시 넣어보니까 제대로 동작한다!!!!!!!!

