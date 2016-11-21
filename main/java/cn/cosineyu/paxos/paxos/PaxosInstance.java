package cn.cosineyu.paxos.paxos;


import com.sun.org.apache.bcel.internal.generic.NEW;

import static cn.cosineyu.paxos.paxos.PaxosMsg.*;

/**
 * Created by cosineyu on 2016/11/21.
 */
public class PaxosInstance {
    private long instanceID;
    private Proposer proposer;
    private Acceptor acceptor;
    private Network network;

    public int onPaxosMsg(PaxosMsg paxosMsg) {
        switch (paxosMsg.getCmd()) {
            case CMD_PREPARE_REQUEST: {
                acceptor.onPrepare(paxosMsg);
                break;
            }
            case CMD_PREPARE_RESPONSE: {
                proposer.onPrepareResponse(paxosMsg);
                break;
            }
            case CMD_ACCEPT_REQUEST: {
                acceptor.onAccept(paxosMsg);
                break;
            }
            case CMD_ACCEPT_RESPONSE: {
                proposer.onAcceptResponse(paxosMsg);
                break;
            }
            default:{}
        }
        return 0;
    }

    public PaxosInstance(Network network) {
        instanceID = 0;
        proposer = new Proposer();
        acceptor = new Acceptor();
        this.network = network;
    }

    public long getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(long instanceID) {
        this.instanceID = instanceID;
    }

    public Proposer getProposer() {
        return proposer;
    }

    public void setProposer(Proposer proposer) {
        this.proposer = proposer;
    }

    public Acceptor getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(Acceptor acceptor) {
        this.acceptor = acceptor;
    }
}
