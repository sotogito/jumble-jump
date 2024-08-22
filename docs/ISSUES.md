### Open NLP 라이브러리 적용
OPennlp는 ~이다.

라이브러리를 추가했는데 안된다. 
문제는..바로... 
1. zip 파일을 풀어버린것.
2. 라이브러리 implemtn를 잘못해준것


gpt가 bin파일이 필요하다는데 도대체 찾을수가 없었다. 그런데... zip을 풀지 않은 상태가 .bin 아니겠느냐.
.bin 파일은 OpenNLP의 훈련된 모델 데이터를 포함하는 이진 파일입니다. 이 파일은 OpenNLP의 내부에서 사용되도록 설계되었으며, 압축 파일처럼 해제하거나 내용을 직접 수정하는 것이 아니라, Java 코드에서 그대로 사용해야 합니다.

주요 이유:
파일 형식: .bin 파일은 내부적으로 OpenNLP가 필요로 하는 데이터 구조와 메타데이터를 포함합니다. 이 파일을 해제하거나 수정하면 데이터 구조가 깨지거나 메타데이터가 손상되어 OpenNLP가 파일을 읽고 처리하는 데 실패하게 됩니다.
이진 데이터: 이 파일은 기계 학습 모델의 가중치, 설정, 그리고 기타 데이터들이 이진 형식으로 포함되어 있습니다. 이 데이터를 텍스트로 열거나 수정할 경우 파일이 손상될 수 있습니다.
따라서 .bin 파일은 그대로 사용해야 하며, Java 코드에서 직접 로드하여 사용하는 것이 올바른 접근입니다.

라이브러리 implem를 해주는데 계속 안되길래 gpt에대한 신뢰를 풀고 공홈에서 찾아보았다.
```text
OpenNLP Tools Dependency
To use the OpenNLP Tools define the following dependency:

dependencies {
  implementation group: 'org.apache.opennlp', name: 'opennlp-tools', version: '2.4.0'
}
```
[Gradle Integration](https://opennlp.apache.org/gradle-dependency.html)로 이동해서 코드를 찾아 붙였더니 잘 됐다

### DeepL

https://support.deepl.com/hc/en-us/articles/9773964275868-DeepL-API-error-messages  

오류 403
인증에 실패했습니다. 유효한 auth_key 매개변수를 제공하세요.

이 오류는 요청의 API 키가 누락되었거나 올바르지 않을 때 발생합니다.

API 키가 유효하려면 구독이 활성화되어 있어야 합니다. DeepL 계정 의 API 키 에서 API 키와 구독 상태를 확인할 수 있습니다 .

또한 필요한 사용량에 맞는 올바른 구독 플랜이 있는지 확인하세요. DeepL의 API에 액세스하려면  DeepL API 플랜 을 구독해야 합니다 . CAT 도구의 인증 키는  DeepL Pro Advanced 및 DeepL Pro Ultimate  구독 플랜에 포함되어 있습니다.

#### 해결
api 뒤에 :fix라고 되어있는걸 무슨 배짱으로 삭제하고 넣었다..ㅎㅎ 다시 넣어보니까 제대로 동작한다!!!!!!!!