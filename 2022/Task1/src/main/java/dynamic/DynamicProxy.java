package dynamic;

import actor.ActorProxy;

import java.lang.reflect.*;

public class DynamicProxy implements InvocationHandler {
    Object target;

    public static Object intercept(Object target, ActorProxy actor){
        Class targetClass = target.getClass();
        Class interfaces[] = targetClass.getInterfaces();
        return Proxy. newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DynamicProxy(target));
    }

    private DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        if(Object.class  == method.getDeclaringClass()) {
            String name = method.getName();
            if("equals".equals(name)) {
                return proxy == args[0];
            } else if("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            } else if("toString".equals(name)) {
                return proxy.getClass().getName() + "@" +
                        Integer.toHexString(System.identityHashCode(proxy)) +
                        ", with InvocationHandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }
        return method.invoke(target, args);
    }
}
