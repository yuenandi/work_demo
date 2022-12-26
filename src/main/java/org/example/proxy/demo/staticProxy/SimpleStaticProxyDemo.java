package org.example.proxy.demo.staticProxy;

/**
 * 静态代理
 * 代理和实际对象一般有相同的借口。
 * 代理是TraceProxy， 内部有一个Iservice的成员变量， 指向实际对象
 * 在构造方法被初始化， 对于方法sayhello的调用，它传给了实际对象，在调用前后输出一些跟踪调用信息
 * 程序输出：
 * entering sayHello
 * hello
 * leaving sayHello
 */
public class SimpleStaticProxyDemo {
    static interface Iservice{
        public void sayHello();
    }
    static class  RealService implements Iservice{
        @Override
        public void sayHello(){
            System.out.println("hello");
        }
    }

    static class TraceProxy implements Iservice {
        private Iservice realService;
        public TraceProxy(Iservice realService){
            this.realService = realService;
        }
        @Override
        public void sayHello() {
            System.out.println("entering sayHello");
            this.realService.sayHello();
            System.out.println("leaving sayHello");
        }
    }

    public static void main(String[] args) {
        Iservice realService = new RealService();
        Iservice proxyService = new TraceProxy(realService);
        proxyService.sayHello();
    }
}
