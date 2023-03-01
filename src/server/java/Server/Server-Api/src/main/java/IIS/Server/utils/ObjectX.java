package IIS.Server.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class ObjectX {
    private ObjectX() {
    }

    public static <TResult> TResult createFrom(Object source, Class<TResult> resultClass) {
        
        try {
            List<Pair<Method, Method>> mappings = Arrays.stream(source.getClass().getMethods())
                .filter(m -> m.getName().startsWith("get"))
                .map(getter -> {
                    try {
                        Method setter = resultClass.getMethod("set" + getter.getName().substring(3), getter.getReturnType());
                        return new Pair<Method, Method>(getter, setter);
                    } catch (NoSuchMethodException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
        
            Constructor<TResult> resultConstructor = resultClass.getDeclaredConstructor();
            TResult result = resultConstructor.newInstance();
            for (Pair<Method, Method> mapping : mappings) {
                mapping.getTargetSetter().invoke(result, mapping.getSourceGetter().invoke(source));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static <TSource, TResult> Collection<TResult> createFromMany(Collection<TSource> sources, Class<TResult> resultClass)
    {
        return sources.stream().map(source -> createFrom(source, resultClass)).toList();
    }
}

class Pair<T, U> {
    private final T sourceGetter;
    private final U targetSetter;

    public Pair(T source, U target) {
        this.sourceGetter = source;
        this.targetSetter = target;
    }

    public T getSourceGetter() {
        return sourceGetter;
    }

    public U getTargetSetter() {
        return targetSetter;
    }
}