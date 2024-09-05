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