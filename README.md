#### lean-proxy

项目源码 https://github.com/HuangPugang/Java-lean
- lean-proxy模块

###导读
代理模式就是自己做不了或不想做的事情找别人做，比如我们买不到票，找黄牛买，这就是代理模式。

代理模式分为调用方、代理、目标三部分。

我们常用的Java代理模式主要有两种:

- 静态代理

- 动态代理

静态代理是设计模式中的一种，也就是硬编码，一旦需要代理的类或方法多了，操作使用很不方便。今天主要讲动态代理 。

### 基本用法
场景，普通粉丝通过黄牛购买演唱会的门票，
先看一下代码实现
#### 接口类 TicketCenter.java
```
public interface TicketCenter {
    void buyTicket(Integer amount);
    void refundTicket();
    void sellTicket();
}
```
#### 接口实现类 RealTicketCenter.java
```
public class RealTicketCenter implements TicketCenter {

    @Override
    public void buyTicket(Integer amount) {
        System.out.println("buyTicket,need ￥" + amount);
    }

    @Override
    public void refundTicket() {
        System.out.println("refundTicket");
    }

    @Override
    public void sellTicket() {
        System.out.println("sellTicket");
    }
}
```
#### 代理类 ProxyTicketCenter.java
```
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

```

#### 使用 Main.java
```
public class Main {
    public static void main(String args[]){
        //保留生成的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        TicketCenter ticketCenter = (TicketCenter) Proxy.newProxyInstance(Main.class.getClassLoader(),new Class[]{TicketCenter.class},new ProxyTicketCenter(new RealTicketCenter()));
        ticketCenter.buyTicket(10);
    }
}
```
#### 执行结果如下
```
before
buyTicket,need ￥10
after
```

### 源码分析
我们可以看到在执行buyTicket方法前后执行了我们代理需要做的事情。
我们进入到newProxyInstance方法
```

        /*
         * Look up or generate the designated proxy class.
         */
        Class<?> cl = getProxyClass0(loader, intfs);
```
这里是找到或生成指定的代理类，
进入到getProxyClass0方法
```
   /**
     * Generate a proxy class.  Must call the checkProxyAccess method
     * to perform permission checks before calling this.
     */
    private static Class<?> getProxyClass0(ClassLoader loader,
                                           Class<?>... interfaces) {
        if (interfaces.length > 65535) {
            throw new IllegalArgumentException("interface limit exceeded");
        }

        // If the proxy class defined by the given loader implementing
        // the given interfaces exists, this will simply return the cached copy;
        // otherwise, it will create the proxy class via the ProxyClassFactory
        return proxyClassCache.get(loader, interfaces);
    }
```
从缓存中获取，如果没有则生成，我们看一下proxyClassCache这个对象，存的是代理类缓存
```

    /**
     * a cache of proxy classes
     */
    private static final WeakCache<ClassLoader, Class<?>[], Class<?>>
        proxyClassCache = new WeakCache<>(new KeyFactory(), new ProxyClassFactory());
```
这里重点要看下ProxyClassFactory，代理类工厂，是如何生成的
```
            /*
             * Generate the specified proxy class.
             */
            byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);
```
看到这段代码，就豁然开朗了，生成代理类的字节码，那么一个代理类就生成了，有兴趣的同学可以进入generateProxyClass方法看他是怎么生成的。
那生成的代码是如何的呢？
我们在Main类有一段代码就起作用了
```
System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
```
这段代码会将生成的字节码保存到本地
一起看下生成的关键代码
```

public final class $Proxy0 extends Proxy implements TicketCenter {
    private static Method m1;
    private static Method m5;
    private static Method m2;
    private static Method m3;
    private static Method m4;
    private static Method m0;

    public $Proxy0(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void refundTicket() throws  {
        try {
            super.h.invoke(this, m5, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final void buyTicket(Integer var1) throws  {
        try {
            super.h.invoke(this, m3, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void sellTicket() throws  {
        try {
            super.h.invoke(this, m4, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() throws  {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m5 = Class.forName("TicketCenter").getMethod("refundTicket");
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m3 = Class.forName("TicketCenter").getMethod("buyTicket", Class.forName("java.lang.Integer"));
            m4 = Class.forName("TicketCenter").getMethod("sellTicket");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}
```
我们在Main方法调用的 ticketCenter.buyTicket(10);
其实就是调用了这个类里面的buyTicket方法，在buyTicket方法里调用了InvocationHandler 的invoke方法。这样一来，我们就明白了是动态代理是如何实现的了。

