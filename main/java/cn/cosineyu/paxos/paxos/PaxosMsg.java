package cn.cosineyu.paxos.paxos;

/**
 * Created by cosineyu on 2016/11/17.
 */
public class PaxosMsg {
    public static final int CMD_PREPARE_REQUEST = 1;
    public static final int CMD_PREPARE_RESPONSE = 2;
    public static final int CMD_ACCEPT_REQUEST = 3;
    public static final int CMD_ACCEPT_RESPONSE = 4;

    private int cmd;
    private int sender;
    private long instanceID;
    private long proposalID;
    private boolean reject = false;
    private long maxProposalID;
    private String value;

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public long getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(long instanceID) {
        this.instanceID = instanceID;
    }

    public long getProposalID() {
        return proposalID;
    }

    public void setProposalID(long proposalID) {
        this.proposalID = proposalID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isReject() {
        return reject;
    }

    public void setReject(boolean reject) {
        this.reject = reject;
    }

    public long getMaxProposalID() {
        return maxProposalID;
    }

    public void setMaxProposalID(long maxProposalID) {
        this.maxProposalID = maxProposalID;
    }
}
