- **_난이도 : ⭐️⭐️️⭐️️️_**
- **_엉망친창 완성 시간 : 최소 9시간 _**
- **_커밋 : 52회_**

### 주요 기능
1. 한국어를 입력받아서 영어로 번역하기
2. 메서드에 생성에 필요한 품사 구별 및 단어 변환하기 - List<String> 으로 저장
3. 카멜 형식으로 변환 - List<String>의 인덱스 0은 소문자로, 1부터는 첫글자만 대문자로 변환, 공백 없이 join

## 신경쓴 점
### 1. 메서드 이름 품사 순서
보통 메서드 이름의 품사의 순서는 다음과 같다
1. 동사
2. 형용사
3. 명사
4. 전치사
5. 명사
문장을 번역하면 먼저 위의 품사들만 추려서 저장했다.

### 2. 명명에 필요없는 관사, 접속사 생략하기
a, the, that, if와 같은 필요없는 단어는 과감히 삭제했다.

### 3. 동사는 원형 사용
대부분 명명을 할때 동사는 원형을 사용한다.  
stanfordNLP로 동사 품사를 추려낸 후, 동사원형을 가져와서 저장했다.

### 4. boolean형 메서드 ; be동사나 have,has,had가 있는 문장
동사가 앞에 가게 로직을 세웠는데, 문제는 일반동사 + is,have 즉 동사가 2개 있을때 였다.

    사용자가 관리자인지 확인하기 : Verify that the user is an admin

- 일반동 : Verify
- that절 안의 동사 : is

이를 고려하지 않은 로직에서 결과는 다음과 같았다.
```text
수정 전 : verify Is User Admin
```
동사를 처음에 집어 넣는데 동사가 2개이니 2개 다 들어가는게 당연했다.  
위의 문장들을 파악했을 때 is, have형태가 들아간 문장은 boolean 형태의 문장이였다.  

동사의 원형이 be, have인 경우에 기존의 동사List를 초기화하고 해당 동사만 담았다.
1. 동사 원형으로 변경
2. 원형이 일반 동사인 경우 동사List에 바로 추가
3. 원형이 be거나 have인 경우 저장된 동사list 비우고, is, have 추가

더 복잡한 문장의 경우 위의 방법이 통하지 않지만 , 메서드 명명에 사용되는 문장은 매우 간단한 문장들이라 가능했다.  
수정된 로직에서의 결과는 다음과 같다.
```text
수정 후 : is User Admin
```

### 5. 수동태 문장의 p.p동사 구분해 나누기
근데 또 문제가 하나 있었다.. 그건 바로 수동태 문장!
예를들어보자
- 사용자에의해 책이 제출 되었는지 확인하기 : Checking to see if a book has been submitted by a user

위는 현재완료 수동태 문장이다. 수정 전 로직에서 결과는 다음과 같다.
```text
수정 전 : have user book
```
완전히 이상하다! 제출했는지 확인하는 의도가 전혀 담겨있지 않다.
문제는 동사의 원형이 be동사 have인 경우 모든 동사를 무시했기 때문이다.  
그럼 당연히 현재완료 수동태 _been과 submitted같은 중요한 행동의 의도를 담고있는 단어도 삭제되어버린다!_

- 수동태의 형태 : be 동사 + 과거분사 (p.p.)
- 현재 완료 수동태 : has/have + been + 과거분사(p.p.)

그럼 지켜야할 형태는 다음과 같다.
1. be 동사 또는 has/have
2. 형용사 + 명사
3. 과거분사(p.p.)
4. 전치사
5. 명사

그럼 동사 원형이 be동사,have 일때 바로 뒤에 과거분사가 나오는지 확인만 해주면 되는거였다.
주의할 점은 바로 뒤에만 확인할 경우 현재완료 수동태의 been 동사만 들어가기 때문에, p.p가 아닐때까지 집어 넣기 -> 행동이 필요했다.
ppVerbs라는 리스트를 하나 더 만들었다.

로직을 아래와 같이 변경했다.
1. 동사 원형으로 변경
2. 원형이 일반 동사인 경우 동사List에 바로 추가
3. 원형이 be거나 have인 경우 저장된 동사list 비우고, is, have 추가
4. 바로 뒤에 p.p가 오는지 확인
5. p.p가 아닐때까지 ppVerbs에 넣기

문장을 완성할때 일반동사 넣고 그 다음 ppVerbs넣으면 해결 되었다.
```text
수정 후 : have Book Been Submitted By User
```

### 6. 전치사가 있는데 명사가 연달아 2개 이상인 경우
만약 전치사가 있는 경우 명사를 하나 넣고 나머지를 집어넣는 식의 로직이였다.  
하지만 만약 전치사 앞에 연결 명사가 2개인 경우 두 연결 명사가 전치사를 기준으로 갈라져버렸다.
- 책장에 사용자 책 유효검사 : Validate user books on the bookshelf

```text
수정 전 : validate user on books bookshelf
```

그래서 전치사를 기준으로 전치사보다 앞에 있는 동사들을 preNouns에 담았다.
문장을 완성할때는 다음과 같이 동작했다.
1. 동사
2. 형용사
3. pre명사
4. pp동사
4. 전치사
5. 명사

```text
수정 후 : validate user books on bookshelf
```

### 7. 의존성 주입을 레퍼지토리에서 해당 서비스에서 get 해오지 않고 외부에서 데이터로 넣어 테스트가 용이하게 했다.
가장 상위 서비스에는 큰 기능을 하는 서비스 3개가 선언되어있다.
- 상위 서비스 : MethodNameGeneratorService
  - EnglishTranslationService : 번역
  - NLPpProcessingService : 품사 분류
  - CamelFormattingService : 카멜케이스로 변경

문제는 NLPpProcessingService의 영어 문장 의존성 주입이였다.  
NLPpProcessingService 내부에서는 번역된 영어 string을 받아 단어별로 품사를 token화 한다.
수정 전에는 메서드 내부에서 레퍼지토리에서 번역된 영어를 get해와서 토큰화했다.  
하지만 이때의 문제는 테스트였다. 품사가 잘 분류되는지 확인하려면 번역에 필요한 한국어와, 번역된 영어, 즉 EnglishTranslationService의 역할이 개입됐다.  

그래서 영어String을 인수로 받게 변경하였다.
```java
    public void generate(String inputKorean) throws Exception {
        String english = translationService.translate(inputKorean);
        nlpService.handlePos(english);
        camelFormattingService.formatToCamelCase();
    }
```
```java
    public CoreDocument initCoreDocumentation(String english) {
        Properties props = new Properties();

        props.setProperty(ANNOTATORS_KEY, ANNOTATORS);
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument document = new CoreDocument(english);
        pipeline.annotate(document);
        return document;
    }
```
translationService에서 바로 번역된 영어를 반환하고 그걸 NLP에 바로 넘겼다.  
번역 서비스와 품사 구분 서비스의 의존성을 조금이라도 줄일 수 있었다.


### 고민한 점
#### 로직에 필요한 TranslationEntryRepository,EnglishPosEntry, MethodName의 의존성 주입을 가장 상위 서비스에 해줄까? 각각 서비스에 단독으로 주입해줄까?
각 서비스에 필요한 클래스는 다르다. 만약 상위 서비스에서 위의 클래스의 의존성을 주입 받은 후, 하위에 넣어줄 경우 상위 서비스의 역할이 너무 커진다고 생각했다.  
만약 하위 클래스의 의존성을 변경하려면 상위, 하위에서 둘 다 수정해야하기에, 각각 독립적으로 의존성을 주입하는게 맞다고 판단하였다.  
가장 상위 서비스에서는 하위 서비스의 중요 "행동"만 지시하도록 초점을 두었다.


### 부족한 점
- 간단한 문장의 경우 동작이 되지만 너무 길고 복자한 문장은 불가능하다.
- NLP를 통해 문장을 조작하는 방식이 미숙하다. 언어를 다루는 것이 너무 어려웠다. 단어나 문법이 추가되면 해결 로직 경우의 수가 몇배로 늘어났다. 
아쉽지만 내가 할 수 있는 선에서 최선을 다했다. 흡사 두꺼비가 항아리 구멍을 막는 자세로 임하였다!!

---
#### 사용 라이브러리 및 API
- 번역 : DeepL
- 품사 구별 : StanfordNLP