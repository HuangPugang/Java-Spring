import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Paul on 2018/8/5
 */
public class ProxyTicketCenter implements InvocationHandler {
    RealTicketCenter realTicketCenter;

    public ProxyTicketCenter(RealTicketCenter realTicketCenter) {
        this.realTicketCenter = realTicketCenter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before");
        Object object = null;
        object=method.invoke(realTicketCenter,args);
        System.out.println("after");
        return object;
    }
}
