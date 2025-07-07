package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Points {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    EQUALS("="),
    ADVANTAGE(">");

    private final String value;

    public Points next() {
        return switch (this) {
            case ZERO -> FIFTEEN;
            case FIFTEEN -> THIRTY;
            case THIRTY -> FORTY;
            case FORTY, ADVANTAGE -> EQUALS;
            case EQUALS -> ADVANTAGE;
        };
    }
}
