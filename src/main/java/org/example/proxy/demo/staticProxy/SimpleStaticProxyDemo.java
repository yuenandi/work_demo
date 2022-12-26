package org.example.proxy.demo.staticProxy;

/**
 * ��̬����
 * �����ʵ�ʶ���һ������ͬ�Ľ�ڡ�
 * ������TraceProxy�� �ڲ���һ��Iservice�ĳ�Ա������ ָ��ʵ�ʶ���
 * �ڹ��췽������ʼ���� ���ڷ���sayhello�ĵ��ã���������ʵ�ʶ����ڵ���ǰ�����һЩ���ٵ�����Ϣ
 * ���������
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
