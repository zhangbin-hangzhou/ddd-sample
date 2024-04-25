package shopping.infrastructure.common;

import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class ObjectConverter {
    public ObjectConverter(){

    }

    public static <T,R> List<R> convert(List<T> sourceList, Function<T,R> function){
        Objects.requireNonNull(sourceList,"sourceList should not be null");
        Objects.requireNonNull(function,"function  should not be null");

        List<R> targetList = new ArrayList<>();
        Iterator<T> iterator = sourceList.iterator();

        while (iterator.hasNext()){
            targetList.add(function.apply(iterator.next()));
        }
        return targetList;
    }

    /**
     * 转换一个对象，需要自定义对象的转换过程
     * @param source
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R convert(T source, Function<T, R> function){
        Objects.requireNonNull(source, "source should not be null");
        Objects.requireNonNull(function, "function should not be null");
        return convert(Arrays.asList(source), function).get(0);
    }

    /**
     * 转换一个对象列表成目标对象列表，采用BeanUtils.copyProperties()拷贝
     * @param sourceList
     * @param clz
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> defaultConvert(List<T> sourceList, Class<R> clz){
        List<R> targetList = new ArrayList<>();
        Objects.requireNonNull(sourceList, "sourceList should not be null");
        Objects.requireNonNull(clz, "Class should not be null");
        for (T source : sourceList){
            R r;
            try {
                r = clz.newInstance();
                BeanUtils.copyProperties(source,r);
                targetList.add(r);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return targetList;
    }

    public static <T,R> R defaultConvert(T source,Class<R> clz){
        return defaultConvert(Arrays.asList(source),clz).get(0);
    }

    public static <T, R> List<R> defaultConvertAndThen(List<T> sourceList, Class<R> clz, Consumer<R> consumer){
        List<R> list = defaultConvert(sourceList, clz);
        return andThen(list,consumer);
    }

    public static <T,R> R defaultConvertAndThen(T source,Class<R> clz,Consumer<R> consumer){
        R r = defaultConvert(source, clz);
        return andThen(r,consumer);
    }

    public  static <T> T andThen(T t,Consumer<T> consumer){
         return andThen(Arrays.asList(t),consumer).get(0);
    }

    private static <T> List<T> andThen(List<T> list, Consumer<T> consumer) {
        Objects.requireNonNull(list,"list should not be null");
        Objects.requireNonNull(consumer,"consumer should not be null");
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()){
            T t = iterator.next();
            consumer.accept(t);
        }
        return list;
    }
}
