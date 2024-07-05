package domain.item.component;

import util.validator.ItemStockValidator;

public class Stock {
    private int stock;

    public Stock(int stock) {
        ItemStockValidator.validate(stock);
        this.stock = stock;
    }

    //TODO 예외 잡아서 Store에서 출력하기
    public void decreaseStock(int amount) {
        if(stock < amount){
            throw new IllegalArgumentException("구매하신 상품의 수량만큼의 재고가 존재하지 않아요.");
        }
        stock -= amount;
    }

    public int getStock() {
        return stock;
    }
}
