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

재귀에서 기저조건이 있어야한다는건 알고있었다.  
나름 `throw new IllegalArgumentException("계산할 수 없습니다.")`; 이 기저조건이 된다고 생각했는데... StackOverflowError가 일어난다.

#### 기저조건이 뭔데???????
기저조건이 뭘까??? 

    기저 조건 :  재귀 함수가 더 이상 재귀적으로 호출되지 않고 멈추는 조건

기저 조건의 특징은 정상적인 실행 흐름에서 동작해야 된다는 것이다.  
하지만 내가 구현한건 예외를 던지는 코드이다.
#### 예외는 재귀를 멈추는 올바른 조건, 즉 기저 조건이 될 수 없다.

그럼 예외를 던지지 않고` return -1;` 을 던진다면? 그건 기저조건이 될까?  
안되네.  
기저 조건은 논리적인 기준에 의해 일어나야한다.  
근데 위의 코드는 단순히 '정상 조건을 만족하지 못할때' 일어나고 있다.  

그럼 기저조건은 뭘까????
```java
if (start > end) {
    return -1; 
}
```
start가 end보다 커질 경우는 완전히 탐색이 끝났을때를 말한다.  
두 point가 교차가 되었을 때, 재귀는 완전히 끝내면 된다.  
만약 -1을 반환했을 경우는 탐색의 결과가 없는 것이다!.



### 최적의 절단기 높이
만약 입려된 떡이 10 15 17 19이고,  
길이가 5라고 입력되면  
오류가 났다!!!!!!

이유가 뭘까,  
바로 mid 절단기로 계산한 떡의 길이가 5로 딱 나누어 떨어지지 않는 경우였기 때문이다.  
그러니 당연히 결과가 없다고 뜬다.  

요구사항은 떡의 길이가 되게하는 최대의 절단기 길이를 요구한다.  
즉 절단기의 길이를 반환하는 로직이 [target == mid 절단기로 자른 떡의 길이]이면 안된다는 말이다 !  

위의 입력된 떡에서  
절단기의 길이가 15이면 잘린 떡의 길이는 6이다,  
절단기의 길이가 16이면 잘린 떡의 길이는 4이다.  

적어도 5개의 떡의 길이가 나와야하니까, 절단기의 최대 높이는 15가 되어야한다.

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
그래서 임시 절단기의 길이를 저장할 수 있는 `bestHeight` 변수를 생성해주었다.  

인수를 추가해주니 재귀로 구현했을때 너무 복잡해 보였다.  
그래서 반복문으로 변경해주었다.  

```java
    private int findCutterHeight(List<RiceCake> riceCakeList, int target) {
        int result = NOTHING;

        while (isContinueCondition()) {
            mid = calculateMidPoint();
            int cutRiceCakeHeight = cutterController.calculateCutRiceCakeTotalHeight(riceCakeList, mid);
            CutterLength midCutterLength = cutterController.getCutterLengthStatus(target, cutRiceCakeHeight);

            if(whenCutterHeightTooShort(midCutterLength)){
                result = mid; 
                continue;
            } else if (whenCutterHeightTooLong(midCutterLength)) {
                continue;
            }
            return mid;
        }
        return result;
    }
```

근데 왜 절단기의 길이가 길때 임시 길이를 저장해주어야할까?

요구 떡 길이가 5일때, 원라대로라면 15가 맞는 정답이지만, 절단기의 길이가 짧을때 임시 길이를 저장해주면 16이 나왔다.  
10 15 17 19

만약 절단기의 길이가 너무 짧을 경우는 자른 떡의 길이가 target보다 작아진다.  
이렇게 되면 최소한의 떡 길이를 얻을 수 없다.  
 
때문에 가장 높은 길이에서 result를 저장해주는게 맞다. 