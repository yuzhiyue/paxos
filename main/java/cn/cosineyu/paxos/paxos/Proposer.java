package cn.cosineyu.paxos.paxos;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cosineyu on 2016/11/17.
 */

enum ProposerStatus {
    PREPARE,
    ACCEPT,
    CHOSEN
}

public class Proposer extends Actor {
    private long proposalID;
    private String proposalValue;
    ProposerStatus status;
    private Set<String> acceptSet = new HashSet<>();
    private long maxOtherAcceptProposalID = 0;
    private String maxOtherAcceptValue = null;

    public int prepare(String value) {
        status = ProposerStatus.PREPARE;
        proposalValue = value;
        PaxosMsg paxosMsg = new PaxosMsg();
        paxosMsg.setCmd(PaxosMsg.CMD_PREPARE_REQUEST);
        paxosMsg.setInstanceID(getInstanceID());
        paxosMsg.setProposalID(proposalID);
        broadcastMsg(paxosMsg);
        return 0;
    }

    public int onPrepareResponse(PaxosMsg paxosMsg) {
        if (status != ProposerStatus.PREPARE) {
            return 0;
        }
        if (paxosMsg.getInstanceID() != getInstanceID()) {
            return 0;
        }
        if (paxosMsg.getProposalID() != proposalID) {
            return 0;
        }
        if (paxosMsg.isReject()) {

        } else {
            acceptSet.add(paxosMsg.getValue());
            if (maxOtherAcceptProposalID < paxosMsg.getMaxProposalID()) {
                maxOtherAcceptProposalID = paxosMsg.getMaxProposalID();
                maxOtherAcceptValue = paxosMsg.getValue();
            }
        }

        if (acceptSet.size() >= 2) {
            if (maxOtherAcceptValue != null) {
                proposalValue = maxOtherAcceptValue;
            }
            accept();
        }

        return 0;
    }

    public int accept() {
        status = ProposerStatus.ACCEPT;
        acceptSet.clear();

        PaxosMsg paxosMsg = new PaxosMsg();
        paxosMsg.setCmd(PaxosMsg.CMD_ACCEPT_REQUEST);
        paxosMsg.setInstanceID(getInstanceID());
        paxosMsg.setProposalID(proposalID);
        paxosMsg.setValue(proposalValue);
        broadcastMsg(paxosMsg);
        return 0;
    }

    public int onAcceptResponse(PaxosMsg paxosMsg) {
        if (status != ProposerStatus.ACCEPT) {
            return 0;
        }
        if (paxosMsg.getInstanceID() != getInstanceID()) {
            return 0;
        }
        if (paxosMsg.getProposalID() != proposalID) {
            return 0;
        }
        if (paxosMsg.isReject()) {

        } else {
            acceptSet.add(paxosMsg.getValue());
        }

        if (acceptSet.size() >= 2) {
            onChosen();
        }
        return 0;
    }

    private void onChosen() {
        status = ProposerStatus.CHOSEN;
        PaxosMsg paxosMsg = new PaxosMsg();
        paxosMsg.setInstanceID(getInstanceID());
        paxosMsg.setProposalID(proposalID);
        broadcastMsg(paxosMsg);
    }

    private void newProposalID() {
        proposalID++;
    }
}
