package dev.cruding.engine.gen;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import dev.cruding.engine.entity.Entity;

public abstract class ViewComposer<T extends Entity> extends BaseComposer {

    private final Class<T> entityType;

    protected ViewComposer() {
        entityType = resolveEntityType();
    }

    public Class<T> entityType() {
        return entityType;
    }

    @SuppressWarnings("unchecked")
    private Class<T> resolveEntityType() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType parameterizedType)) {
            throw new ContextException("ViewComposer subclass must directly declare an entity type: " + getClass().getName());
        }

        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
}
