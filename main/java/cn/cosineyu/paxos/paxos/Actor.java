package cn.cosineyu.paxos.paxos;

/**
 * Created by cosineyu on 2016/11/17.
 */
public class Actor {
    private PaxosInstance instance;

    public PaxosInstance getInstance() {
        return instance;
    }

    public void setInstance(PaxosInstance instance) {
        this.instance = instance;
    }

    public long getInstanceID() {
        return instance.getInstanceID();
    }


    public void sendMsg(PaxosMsg msg) {

    }

    public void broadcastMsg(PaxosMsg msg) {

    }
}
