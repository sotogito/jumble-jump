package domain;

import java.util.EnumMap;

public class ChangeCalculator {

    public EnumMap<Bill, Integer> calculateChange(long balance) {
        EnumMap<Bill, Integer> result = new EnumMap<>(Bill.class);

        for (Bill coin : Bill.values()) {
            int coinValue = coin.getValue();
            int count = (int) balance / coinValue;  // 각 동전의 개수를 계산
            balance %= coinValue;             // 잔액을 업데이트
            result.put(coin, count);
        }
        return result;
    }

}
