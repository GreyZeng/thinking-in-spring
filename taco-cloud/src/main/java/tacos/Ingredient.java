package tacos;

import lombok.Data;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/12/22
 * @since 11
 */
@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
