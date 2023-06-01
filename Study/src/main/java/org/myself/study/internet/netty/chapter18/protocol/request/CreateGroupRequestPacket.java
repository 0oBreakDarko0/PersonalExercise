package org.myself.study.internet.netty.chapter18.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.myself.study.internet.netty.chapter18.protocol.Packet;

import java.util.List;

import static org.myself.study.internet.netty.chapter18.protocol.command.Command.CREATE_GROUP_REQUEST;


@Data
@NoArgsConstructor
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
