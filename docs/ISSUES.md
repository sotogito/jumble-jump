### 재귀의 기저조건
```java
    private int findCutterHeight(List<RiceCake> array, int target, int start, int end){
        int mid = (start + end) / 2;
        int midHeight = array.get(mid).getHeight();
        int cutRiceCakeHeight = calculateCutRiceCake(array,midHeight);


        CutterLength midCutterLength = cutterController.calculate(target, cutRiceCakeHeight);

        if (midCutterLength.equals(CutterLength.SAME)){
            return midHeight;

        } else if (midCutterLength.equals(CutterLength.LONG)){
            int endPoint = mid + (midCutterLength.getValue());
            return findCutterHeight(array, target, start, endPoint);

        }else if (midCutterLength.equals(CutterLength.SHORT)){
            int startPoint = mid + (midCutterLength.getValue());
            return findCutterHeight(array, target, startPoint, end);
        }

        throw new IllegalArgumentException("계산할 수 없습니다.");
    }
```

```text
Exception in thread "main" java.lang.StackOverflowError
	at jumble_jump.serive.CutterHeightSettingService.calculateCutRiceCake(CutterHeightSettingService.java:70)
	at jumble_jump.serive.CutterHeightSettingService.findCutterHeight(CutterHeightSettingService.java:47)
	at jumble_jump.serive.CutterHeightSettingService.findCutterHeight(CutterHeightSettingService.java:57)
	at jumble_jump.serive.CutterHeightSettingService.findCutterHeight(CutterHeightSettingService.java:57)
	at jumble_jump.serive.CutterHeightSettingService.findCutterHeight(CutterHeightSettingService.java:57)
```
throw new IllegalArgumentException("계산할 수 없습니다.");이 지거고건이 될 수 없을까?


### 조건문의 오류
```java
CutterLength midCutterLength = cutterController.calculate(target, cutRiceCakeHeight);

if (midCutterLength.equals(CutterLength.SAME)){
        return midHeight;

    } else if (midCutterLength.equals(CutterLength.LONG)){
int endPoint = mid + (midCutterLength.getValue());
        return findCutterHeight(riceCakeList,array, target,mid + 1, end); //짧게

    }else if (midCutterLength.equals(CutterLength.SHORT)){
int startPoint = mid + (midCutterLength.getValue());
        return findCutterHeight(riceCakeList, array, target, start, mid - 1);
    }
```

- 계산 결과 > 요구 길이
- cutterLength가 짧다는 의미
- mid 이하 버려야됨.
  midCutterLength를 계산하는 과정이 계신 결과로 비교하는건데 cutterLength로 착ㄱ가했다.

---
요구 떡 길이가, 계산 결과과 딱 맞아 떨어지지 않을 경우
10 15 17 19
에서 길이가 5만큼
딱 떨아지지 않기떄문에 꼐산할수 없다고 뜬다.

만약 길이가 5이면 적어도 가장 높은 cutter의 길이를 설정해야한다.
높이가 15일때 6개이고, 16일때 4개이니, 값은 15가 되어야한다.

이걸 고려안해주었다.
정답을 반환한는 코드를 오직 결과와 요청 길이가 같다고만 여겼기 때문에 끝나지 않는 스택 오버ㅗ플로우가 발생하였다,.

해결법은 일단 이전의 mid 값을 저장하고 재귀로 같이 넘기는것이다.

- cutter의 높이가 커질수록, 잘린 떡의 길이는 작아진다.

떄문에 앞의 로직에서 bestHeight값을 설정해줄 수 있었다.
```java
    private int findCutterHeight(List<RiceCake> riceCakeList, int target, int start, int end, int bestHeight) {
        if (isUnderlyingCondition(start, end)) {
            return bestHeight;
        }

        int mid = (start + end) / 2;
        int cutRiceCakeHeight = cutterController.calculateCutRiceCakeTotalHeight(riceCakeList, mid);
        CutterLength midCutterLength = cutterController.getCutterLengthStatus(target, cutRiceCakeHeight);

        return adjustingCutterLength(midCutterLength, riceCakeList, target, start, end, mid, bestHeight);
    }

    private int adjustingCutterLength(CutterLength midCutterLength, List<RiceCake> riceCakeList,
                                      int target, int start, int end, int mid, int bestHeight) {
        if (midCutterLength.equals(CutterLength.LONG)) {
            int startPoint = cutterController.adjustingLength(midCutterLength,mid);
            bestHeight = mid;
            return findCutterHeight(riceCakeList, target, startPoint, end,bestHeight);

        } else if (midCutterLength.equals(CutterLength.SHORT)) {
            int endPoint = cutterController.adjustingLength(midCutterLength,mid);
            return findCutterHeight(riceCakeList, target, start, endPoint,bestHeight);
        }
        return mid;
    }
```

근대ㅔ.. 또다른 문제가 있다.
인수가 너무많다!

target에 reuslt를 맞추는데에 집중하기보다는
최대값을 구하느것에 집중해야한다.,
비교해야할 데이터가 start, end 외에도 많기떄문에?
bestHeight의 존재로 재귀보다 반복문이 더 낫다.