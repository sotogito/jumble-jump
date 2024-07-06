package domain;

public enum Bill {
    BILL_50000(50000),
    BILL_10000(10000),
    BILL_5000(5000),
    BILL_1000(1000),
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10),
    COIN_1(1);

    private int value;

    Bill(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
