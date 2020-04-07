import com.yaoshuai.tank.tank17_1.net.MsgType;
import com.yaoshuai.tank.tank17_1.net.TankJoinMsg;
import com.yaoshuai.tank.tank17_1.net.TankJoinMsgDecoder;
import com.yaoshuai.tank.tank17_1.net.TankJoinMsgEncoder;
import com.yaoshuai.tank.tank17_1.tank.Dir;
import com.yaoshuai.tank.tank17_1.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

public class TankJoinMsgCodec {

    @Test
    public void testEncoder(){
        EmbeddedChannel ch = new EmbeddedChannel();
        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5,10, Dir.DOWN,true, Group.GOOD,id);
        ch.pipeline()
                .addLast(new TankJoinMsgEncoder());

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)ch.readOutbound();

        MsgType msgType = MsgType.values()[buf.readInt()];
        assertEquals(MsgType.TankJoinMsg,msgType);

        int length = buf.readInt();
        assertEquals(33,length);

        int x = buf.readInt();
        int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        boolean moving = buf.readBoolean();
        Group group = Group.values()[buf.readInt()];
        UUID uuid = new UUID(buf.readLong(),buf.readLong());

        assertEquals(5,x);
        assertEquals(10,y);
        assertEquals(Dir.DOWN,dir);
        assertEquals(true,moving);
        assertEquals(Group.GOOD,group);
        assertEquals(id,uuid);

    }
    @Test
    void testDecoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5,10, Dir.DOWN,true, Group.GOOD,id);
        ch.pipeline()
                .addLast(new TankJoinMsgDecoder());

        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(MsgType.TankJoinMsg.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);

        buf.writeBytes(msg.toBytes());

        ch.writeInbound(buf.duplicate());

        TankJoinMsg msg1 = (TankJoinMsg)ch.readInbound();

        assertEquals(5,msg1.x);
        assertEquals(10,msg1.y);
        assertEquals(Dir.DOWN,msg1.dir);
        assertEquals(true,msg1.moving);
        assertEquals(Group.GOOD,msg1.group);
        assertEquals(id,msg1.id);

    }
}
