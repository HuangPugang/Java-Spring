import java.lang.reflect.Proxy;

/**
 * Created by Paul on 2018/8/5
 */
public class Main {
    public static void main(String args[]){
        //保留生成的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        TicketCenter ticketCenter = (TicketCenter) Proxy.newProxyInstance(Main.class.getClassLoader(),new Class[]{TicketCenter.class},new ProxyTicketCenter(new RealTicketCenter()));
        ticketCenter.buyTicket(10);
    }
}
