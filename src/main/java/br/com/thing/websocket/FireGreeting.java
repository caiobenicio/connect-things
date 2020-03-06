package br.com.thing.websocket;

public class FireGreeting implements Runnable {

    private SensorController listener;

    public FireGreeting( SensorController listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep( 2000 );
                listener.fireGreeting();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
    }
}