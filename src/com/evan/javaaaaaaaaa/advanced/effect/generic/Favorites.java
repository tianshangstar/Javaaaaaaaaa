package com.evan.javaaaaaaaaa.advanced.effect.generic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by evan01.zhang on 2018/9/4.
 * <p>
 * 类型安全的异构容器示例
 * 需要明确
 * 1、何为类型安全 ---- 通过泛型进行编译期类型检查，视为类型安全
 * 2、何为异构 ---- 对于Favorites的数据Map favorites,他的所有key都是不同类型的。
 */
public class Favorites {

    private Map<Class<?>, Object> favorites = new HashMap<>();

    /**
     * 如果instance不能具体化，这里将无法保存对象，
     * 如：尝试放一个List<String>将会报错
     * 由于泛型的运行时类型擦除，在运行时，List<String>和List<Integer>没有区别
     * 所以如果尝试放入List<String>第一个参数需要传List.class，这样其他list对象放入会覆盖
     * 这就是所谓的，只能放入-----------可具体化的类型
     *
     * @param clazz
     * @param instance
     * @param <T>
     */
    public <T> void putFavorties(Class<T> clazz, T instance) {
        if (clazz == null)
            throw new NullPointerException("class can not be null");
        favorites.put(clazz, instance);
//        性能换安全的办法，如果类型不匹配，会报classCast
//        favorites.put(clazz, clazz.cast(instance));
    }

    public <T> T getFavorties(Class<T> clazz) {
        return clazz.cast(favorites.get(clazz));
    }

    public static void main(String[] args) {

        Favorites favorites = new Favorites();
        favorites.putFavorties(String.class, "string");
        favorites.putFavorties(String.class, "string1111");
        favorites.putFavorties(String.class, "string2222");
        // 如果使用原生态形式，这里会有编译警告：
        Class clazz = Integer.class;
        Class<String> strClaz = clazz;
        System.out.println(favorites.getFavorties(strClaz));

        List<String> stringList = Arrays.asList("abc", "def");
        favorites.putFavorties(List.class, stringList);

//        Class mapClz = AbstractMap.class;
//        HashMap hashMap = new HashMap();
//        System.out.println(mapClz.isInstance(hashMap));
//        System.out.println(hashMap instanceof AbstractMap);
    }
}
