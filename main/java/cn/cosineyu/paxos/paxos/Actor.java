package cn.cosineyu.paxos.paxos;

/**
 * Created by cosineyu on 2016/11/17.
 */
public class Actor {
    private String id;
    private long instanceID;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(long instanceID) {
        this.instanceID = instanceID;
    }

    public void broadcastMsg(PaxosMsg msg) {

    }
}
