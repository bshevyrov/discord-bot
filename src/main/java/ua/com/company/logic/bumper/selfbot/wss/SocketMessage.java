package ua.com.company.logic.bumper.selfbot.wss;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SocketMessage  implements Future<SocketMessage> {
    private String t;
    private String s;
    private int op;
    private D d;
private boolean done;
    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        if(done){
            done=false;
            return true;
        }
        return false;
    }

    @Override
    public SocketMessage get() throws InterruptedException, ExecutionException {
        return this;
    }

    @Override
    public SocketMessage get(long timeout, @NotNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    public void setDone(boolean b) {
    }


    class D{
        private int heartbeat_interval;

        public int getHeartbeat_interval() {
            return heartbeat_interval;
        }

        public void setHeartbeat_interval(int heartbeat_interval) {
            this.heartbeat_interval = heartbeat_interval;
        }
    }
}
