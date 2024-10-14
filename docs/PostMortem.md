- **_난이도 : ⭐️️️️⭐️️️️️_**
- **_엉망친창 완성 시간 : 3시간 16분_**
- **_커밋 : 약 40회_**

## 공부가 된 내용
- 재귀
- 이진 탐색


## 신경쓴 점
### 1. Enum으로 절단기 길이 조절시 경우의 수 상수로 정의
Enum으로 절단기 상태를 상수로 정의하였다.  
start,end point의 예산 상수 또한 필드로 정리하여 한눈에 볼 수 있게 하였다.


### 2. 이진 탐색 로직
로직 플로우는 다음과 같다.
1. start, end, mid 값을 설정한다.
2. mid값을 절단기의 높이로 가정한다..
3. mid절단기시 잘린 떡의 길이를 구한다.
4. 요구 떡 길이와 비교한다.
5. 이진탐색으로 최적의 절단기 높이를 구한다.

Enum에 정의된 SAME, SHORT, LONG은 절단기의 기준이다.  
재귀 로직에서 Enum의 상수를 받아오는 교군은 다음 두개이다.
- target : 사용자가 입력한 최종 떡 길이.
- result : mid 절단기 길이로 자른 떡의 길이.

이 두개를 비교하면 3가지의 경우의 수가 나온다.

#### 1. result == target
설정한 mid절단기 길이가 요구사항에 맞는 경우다.  
바로 mid값을 반환하면 된다.

#### _2. result > target_
- mid 절단기로 자른 떡의 길이가 길다. 
- 절단기 상태 : SHORT
- 잘린 쩍의 길이 상태 : LONG

이 경우는 절단기의 길이가 너무 짧기 때문에 나오는 결과이다.  
만약 떡의 길이가 [10 9 14 17] 이라고 가정해보자,  
- 1cm 절단기 : 9 + 8 + 13 + 16 = 46
- 16cm 절단기 : 0 + 0 + 0 + 1 = 1  

절단기의 길이가 짧을때 잘린 떡의 길이가 더 길다.
즉, 절단기의 높이와, 잘린 떡의 길이의 관계는 반비례 관계이다.

        절단기 높이 ∝ (1/떡의 길이)

절단기의 길이가 너무 짧다는 것은, 해당 mid 값보다 작은 값을 볼 필요가 없다는 말이다.
그럼 start point를 mid 값까지 떙겨와 범위를 반으로 줄인다.
        new start point = mid + 1

+1을 해주는 이유는 mid값이 정답이 아닌것을 확인했으니 바로 그 다름부터 탐색을 이어나가기 위해서이다.

#### _3. result < target_
- mid 절단기로 자른 떡의 길이가 짧다
- 절단기 상태 : LONG
- 잘린 떡의 길이 상태 : SHORT

절단기의 길이가 너무 길어 갈린 떡의 길이가 짧다.
이는 mid 값의 이상은 볼 필요가 없다는 것으로, end 값을 좁히면 된다.
        new end point = mid -1

-1 은 mid 값보다 한 index 앞으로 탐색 범위를 줄이기 위해서이다.


#### 헷갈렸던 부분
이 과정에서 LONG과 SHORT 로직을 새우는것을 어려워했는데...  
그 이유는 ***mid절단기의 길이와, mid절단기로 자른 떡의 길이를 혼동***했기 때문이였다.

이 둘은 반비례 관계이다.  
자른 떡의 길이가 길어지면 절단기의 길이가 짧기 떄문이다.  
CutterControlUnit에서 CutterLengthStatus로 상태를 받아올때 나는 mid 절단기로 자른 떡의 길이 상태로 인지하고 로직을 새웠다.
```java
    @Override
    public CutterLength getCutterLengthStatus(int target, int result) {
        if (target < result) {
            return CutterLength.SHORT;
        } else if (target > result) {
            return CutterLength.LONG;
        }
        return CutterLength.SAME;
    }
```
target하고 비교하는 값이 result여서 헷갈렸던 모양이다.  
그러니 완전히 반대로 풀고있는 셈이 되었다.  

해당 메서드에서는 '절단기의 높이 상태를 반환'하는 역할임을 다시 인지하고 로직을 변경하였다.

### 사실 array가 필요가 없었다?!
이진탐색을 접한지 3일째, 나는 배운 정석대로 아래의 요소가 필요하다고 생각했다.
- array - 0~입력받은 떡의 최대 갈이값
- target
- start = 0
- end = max(RiceCakes)
- (mid)  

헌데 로직은 새우다보니 array의 존재가 의심되었다.  
왜있지?!!

그렇다. 필요 없었다.
특정한 값의 array에서 찾아내는게 아닌  
0에서부터 최대 떡 길이값까지 규칙적인 정수면 더더욱 array를 생성할 이유가 없었다.  
mid의 index는 즉 절단기의 높이이다.  
array(mid)로 계산해줄 필요가 없었다.  

처음 시작에서 start와 end값만 잘 설정해준다면,  
startPoint와 endPoint를 새로 선언해주는 과정으로 충분히 풀 수 있는 로직이다.  

만약 절단기의 높이를 입력된 떡의 길이 중에서 찾아야한다면, 그건 필요했겠지만 이 로직에서는 필요 없다!


### 재귀 -> 반복문으로 이진탐색 로직 변경
잘린 떡의 길이를 만들기 위한 최대의 절단기 길이  
를 생성하기 위해서는 반드시 result == target 의 로직으로 정답이 나오지 않는다.  
그래서 일단 중간 값을 저장할 변수가 하나 더 필요했는데  , 재귀로 구현했을 시 인수가 무려 6개가 되었다.
- riceCakeList
- array
- target
- start
- end
- temporaryLength  
재귀로 구현하고싶어 구현했지만 코드가 너무 복잡해져 반복문으로 변경하였다.
```java
    private int findCutterHeight(List<RiceCake> riceCakeList, int target) {
        int result = NOTHING;

        while (isContinueCondition()) {
            mid = calculateMidPoint();
            int cutRiceCakeHeight = cutterController.calculateCutRiceCakeTotalHeight(riceCakeList, mid);
            CutterLength midCutterLength = cutterController.getCutterLengthStatus(target, cutRiceCakeHeight);

            if(whenCutterHeightTooShort(midCutterLength)){
                result = mid; //note 아래에사면 5일때 16이나옴
                continue;
            } else if (whenCutterHeightTooLong(midCutterLength)) {
                continue;
            }
            return mid;
        }
        return result;
    }
```