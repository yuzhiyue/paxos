package cn.cosineyu.paxos.paxos;

/**
 * Created by cosineyu on 2016/11/18.
 */


public class Acceptor extends Actor {
    private long maxPromisedProposalID;
    private long maxAcceptProposalID;
    private String maxAcceptValue;
    public int onPrepare(PaxosMsg paxosMsg) {
        if (getInstanceID() != paxosMsg.getInstanceID()) {
            return 0;
        }

        PaxosMsg rspPaxosMsg = new PaxosMsg();
        rspPaxosMsg.setInstanceID(getInstanceID());
        rspPaxosMsg.setProposalID(paxosMsg.getProposalID());
        if (maxPromisedProposalID > paxosMsg.getProposalID()) {
            rspPaxosMsg.setReject(true);
            rspPaxosMsg.setMaxProposalID(maxPromisedProposalID);
        } else {
            maxPromisedProposalID = paxosMsg.getProposalID();
            rspPaxosMsg.setMaxProposalID(maxAcceptProposalID);
            rspPaxosMsg.setValue(maxAcceptValue);
        }
        sendMsg(rspPaxosMsg);
        return 0;
    }

    public int onAccept(PaxosMsg paxosMsg) {
        if (getInstanceID() != paxosMsg.getInstanceID()) {
            return 0;
        }

        PaxosMsg rspPaxosMsg = new PaxosMsg();
        rspPaxosMsg.setCmd(PaxosMsg.CMD_ACCEPT_RESPONSE);
        rspPaxosMsg.setInstanceID(getInstanceID());
        rspPaxosMsg.setProposalID(paxosMsg.getProposalID());
        if (maxPromisedProposalID > paxosMsg.getProposalID()) {
            rspPaxosMsg.setReject(true);
            rspPaxosMsg.setMaxProposalID(maxPromisedProposalID);
        } else {
            maxPromisedProposalID = paxosMsg.getProposalID();
            maxAcceptProposalID = paxosMsg.getMaxProposalID();
            maxAcceptValue = paxosMsg.getValue();
        }
        sendMsg(rspPaxosMsg);
        return 0;
    }
}
