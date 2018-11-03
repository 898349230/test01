package jvm.ch4;

public class TestDeadLock extends Thread{

    private int a;
    private int b;

    public TestDeadLock(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (Integer.valueOf(a)){
            synchronized (Integer.valueOf(b)){
                System.out.println(a + b);
            }
        }
    }

    /**
     * 测试线程死锁
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            new TestDeadLock(1, 2).start();
            new TestDeadLock(2, 1).start();
        }
    }

}
