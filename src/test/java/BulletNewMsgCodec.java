import com.yaoshuai.tank.tank17_1.net.BulletNewMsg;
import com.yaoshuai.tank.tank17_1.net.MsgDecoder;
import com.yaoshuai.tank.tank17_1.net.MsgEncoder;
import com.yaoshuai.tank.tank17_1.net.MsgType;
import com.yaoshuai.tank.tank17_1.tank.Dir;
import com.yaoshuai.tank.tank17_1.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BulletNewMsgCodec {
    @Test
    public void testEncoder(){
        EmbeddedChannel ch = new EmbeddedChannel();
        UUID playerID = UUID.randomUUID();
        UUID id = UUID.randomUUID();
        BulletNewMsg msg = new BulletNewMsg(playerID,id, Dir.DOWN,5,10, Group.GOOD);
        ch.pipeline().addLast(new MsgEncoder());

        ch.writeOutbound(msg);

        ByteBuf buf = ch.readOutbound();

        MsgType msgType = MsgType.values()[buf.readInt()];
        assertEquals(MsgType.BulletNew,msgType);

        int length = buf.readInt();
        assertEquals(48,length);

        UUID playerUUID = new UUID(buf.readLong(),buf.readLong());
        UUID uuid = new UUID(buf.readLong(),buf.readLong());
        int x = buf.readInt();
        int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        Group group = Group.values()[buf.readInt()];

        assertEquals(playerID,playerUUID);
        assertEquals(id,uuid);
        assertEquals(5,x);
        assertEquals(10,y);
        assertEquals(Dir.DOWN,dir);
        assertEquals(Group.GOOD,group);

    }
    @Test
    public void testDecoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID playerID = UUID.randomUUID();
        UUID id = UUID.randomUUID();
        BulletNewMsg msg = new BulletNewMsg(playerID,id,Dir.DOWN,5,10,Group.GOOD);

        ch.pipeline().addLast(new MsgDecoder());

        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(MsgType.BulletNew.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ch.writeInbound(buf.duplicate());
        BulletNewMsg msg1 = ch.readInbound();

        assertEquals(playerID,msg1.playerID);
        assertEquals(id,msg1.id);
        assertEquals(5,msg1.x);
        assertEquals(10,msg1.y);
        assertEquals(Dir.DOWN,msg1.dir);
        assertEquals(Group.GOOD,msg1.group);


    }
}
