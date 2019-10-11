package realmayus.youmatter.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import realmayus.youmatter.replicator.ContainerReplicator;
import realmayus.youmatter.util.IReplicatorStateContainer;

public class PacketShowPrevious implements IMessage{

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public PacketShowPrevious() {
    }

    public static class Handler implements IMessageHandler<PacketShowPrevious, IMessage> {

        @Override
        public IMessage onMessage(PacketShowPrevious message, MessageContext ctx) {
            // This is the player the packet was sent to the server from
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            serverPlayer.getServerWorld().addScheduledTask(() -> {
                if (serverPlayer.openContainer instanceof ContainerReplicator) {
                    ContainerReplicator openContainer = (ContainerReplicator) serverPlayer.openContainer;
                    openContainer.te.renderPrevious();
                }
            });
            // No response packet
            return null;
        }
    }

}