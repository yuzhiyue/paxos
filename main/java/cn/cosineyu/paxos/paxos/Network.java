package cn.cosineyu.paxos.paxos;

/**
 * Created by cosineyu on 2016/11/21.
 */
public interface Network {
    void broadcastMsg(PaxosMsg paxosMsg);
    void sendMsg(int nodeID, PaxosMsg paxosMsg);
}
