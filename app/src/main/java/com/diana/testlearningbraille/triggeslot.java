package com.diana.testlearningbraille;

public class triggeslot extends Thread {
    public boolean start = true;

    public void run() {
        start = false;
        this.wait(6);
        start = true;

    }


    private void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
