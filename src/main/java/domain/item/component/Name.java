package domain.item.component;

import util.validator.ItemNameValidator;

public class Name {
    private final String name;

    public Name(String name) {
        ItemNameValidator.validate(name);
        //TODO 중복 검사
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
