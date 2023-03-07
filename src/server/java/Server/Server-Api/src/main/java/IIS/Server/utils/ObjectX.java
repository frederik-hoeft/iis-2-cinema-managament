package IIS.Server.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.bestvike.linq.Linq;

public abstract class ObjectX {
    private ObjectX() {
    }

    public static <TResult> TResult createFromChecked(Object source, Class<TResult> resultClass, boolean disableChecks)
    {
        try 
        {
            List<DeepCopyMapping<Method, Method>> mappings = Arrays.stream(source.getClass().getMethods())
                .filter(m -> m.getName().startsWith("get"))
                .map(getter -> 
                {
                    try 
                    {
                        Method setter = resultClass.getMethod("set" + getter.getName().substring(3), getter.getReturnType());
                        return new DeepCopyMapping<Method, Method>(getter, setter);
                    } catch (NoSuchMethodException e) 
                    {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(m -> m.getSourceGetter().getReturnType() == Linq.of(m.getTargetSetter().getParameterTypes()).firstOrDefault())
                .toList();
            
            if (!disableChecks)
            {
                mappings.stream().filter(m -> m.getSourceGetter().getReturnType() != m.getTargetSetter().getParameterTypes()[0]).toList().forEach(m -> 
                {
                    System.out.println("My dude you're doing weird stuff: source " + m.getSourceGetter().getName() 
                        + " is of type " + m.getSourceGetter().getName() + " but target " + m.getTargetSetter().getName() 
                        + " is of type " + m.getTargetSetter().getParameterTypes()[0].getName());
                });
            }
        
            Constructor<TResult> resultConstructor = resultClass.getDeclaredConstructor();
            TResult result = resultConstructor.newInstance();
            for (DeepCopyMapping<Method, Method> mapping : mappings) {
                mapping.getTargetSetter().invoke(result, mapping.getSourceGetter().invoke(source));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <TResult> TResult createFrom(Object source, Class<TResult> resultClass) {
        return createFromChecked(source, resultClass, false);
    }

    public static <TSource, TResult> Collection<TResult> createFromMany(Collection<TSource> sources, Class<TResult> resultClass)
    {
        return createFromManyChecked(sources, resultClass, false);
    }

    public static <TSource, TResult> Collection<TResult> createFromManyChecked(Collection<TSource> sources, Class<TResult> resultClass, boolean disableChecks)
    {
        return sources.stream().map(source -> createFromChecked(source, resultClass, disableChecks)).toList();
    }
}