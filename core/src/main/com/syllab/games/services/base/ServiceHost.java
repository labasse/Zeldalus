package com.syllab.games.services.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class ServiceHost {
    private final HashMap<Class, ArrayList<Object>> services = new HashMap<>();

    public void register(Object instance) {
        Class<?> type = instance.getClass();

        for(Class<?> implemented : type.getInterfaces()) {
            if(!services.containsKey(implemented)) {
                services.put(implemented, new ArrayList<>());
            }
            services.get(implemented).add(instance);
        }
        services.put(type, new ArrayList<>());
        services.get(type).add(instance);
    }
    public <T> T getInstance(Class<T> type) {
        List<Object> l = services.get(type);

        return (T)l.get(l.size()-1);
    }
    public <T> Stream<T> getServices(Class<T> type) {
        return (Stream<T>)(services.containsKey(type) ? services.get(type).stream() : Stream.empty());
    }
}
