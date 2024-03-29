package kr.or.ddit.basic;

public class T02ThreadTest {
    public static void main(String[] args) {
        /*
        멀티스레드 프로그램 방식

        방법 1. Thread클래스를 상속한 클래스의 인스턴스를 생성한 후
                이때 생성된 Thread객체의 start()메서드를 호출하여 실행시킨다.
         */
        Thread th1 = new MyThread1();
        th1.start();

        /*
        방법 2. Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 후
                이 인스턴스를 Thread 클래스의 생성자를 호출하면서 매개변수로 넘겨준다.
                이때 생성된 Thread객체의 start()메서드를 호출하여 실행시킨다. <<-자주 쓸 경우
         */
        Runnable r = new MyThread2();
        Thread th2 = new Thread(r);
        th2.start();

        /*
        방법 3. 익명클래스를 이용하는 방법 <<-일회성 사용
         */
        Thread th3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=200; i++) {
                    System.out.print("^");

                    try {
                        //Thread.sleep(시간) -> 주어진 시간동안 작업을 잠시 멈춘다.
                        //시간은 밀리세컨드 단위를 사용함. 즉, 1000은 1초
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                 }
               }
           }
       });
        th3.start();

    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        for(int i=1; i<=200; i++) {
            System.out.print("*");

            try {
                //Thread.sleep(시간) -> 주어진 시간동안 작업을 잠시 멈춘다.
                //시간은 밀리세컨드 단위를 사용함. 즉, 1000은 1초
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {
        for(int i=1; i<=200; i++) {
            System.out.print("@");

            try {
                //Thread.sleep(시간) -> 주어진 시간동안 작업을 잠시 멈춘다.
                //시간은 밀리세컨드 단위를 사용함. 즉, 1000은 1초
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}