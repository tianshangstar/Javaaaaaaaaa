package com.evan.javaaaaaaaaa.advanced.effect.staticfactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * service provider framework sketch
 * <p>
 * 服务提供者框架xxxx
 * 1、服务接口
 * 2、提供者注册API
 * 3、服务访问API
 * 4、服务提供者接口 --- 非必须
 * <p>
 * 服务注册与提供
 */
public class Services {

    private Services() {
    }

    public static final Map<String, Provider> providers = new ConcurrentHashMap<>();

    public static final String DEFAULT_PROVIDER_NAME = "<def>";

    // Provider register API
    public static void registerDefaultProvider(Provider p){
        registerProvider(DEFAULT_PROVIDER_NAME, p);
    }

    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }

    // Service access API
    public static Service newInstance(){
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    public static Service newInstance(String name){
        Provider p = providers.get(name);
        if (p == null) {
            throw  new IllegalArgumentException("No provider register with name " + name);
        }

        return p.newService();
    }
}
