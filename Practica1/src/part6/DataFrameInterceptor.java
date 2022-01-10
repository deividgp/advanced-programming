package part6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Dynamic proxy.
 */
public class DataFrameInterceptor implements InvocationHandler {
    private Object target = null;
    private final Subject subject;

    /**
     * Creates a new instance of target.
     * @param target Object.
     * @param subject Observer manager.
     * @return New instance of target.
     */
    public static Object newInstance(Object target, Subject subject){
        Class targetClass = target.getClass();
        Class interfaces[] = targetClass.getInterfaces();
        return Proxy. newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DataFrameInterceptor(target, subject));
    }
    private DataFrameInterceptor(Object target, Subject subject) {
        this.target = target;
        this.subject = subject;
        subject.subscribe(new LogObserver());
        subject.subscribe(new QueryObserver());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        Object invocationResult = null;
        try
        {
            System.out.println("Before method " + method.getName());
            invocationResult = method.invoke(this.target, args);
            System.out.println("After method " + method.getName());
            subject.notify(method.getName());
        }
        catch(InvocationTargetException ite)
        {
            throw ite.getTargetException();
        }
        catch(Exception e)
        {
            System.err.println("Invocation of " + method.getName() + " failed");
        }
        finally{
            return invocationResult;
        }
    }
}
