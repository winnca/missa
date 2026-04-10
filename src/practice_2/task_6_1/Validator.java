package practice_2.task_6_1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class Validator {
    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                // Проверка @NotEmpty
                if (field.isAnnotationPresent(NotEmpty.class)) {
                    NotEmpty annotation = field.getAnnotation(NotEmpty.class);
                    String value = (String) field.get(obj);

                    if (value == null || value.trim().isEmpty()) {
                        errors.add(annotation.message());
                    }
                }

                // Проверка @Range
                if (field.isAnnotationPresent(Range.class)) {
                    Range annotation = field.getAnnotation(Range.class);
                    int value = field.getInt(obj);

                    if (value < annotation.min() || value > annotation.max()) {
                        errors.add(annotation.message());
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Ошибка доступа к полю: " + field.getName());
            }
        }

        return errors;
    }
}