package net.cbejltpycbl.pozcraft.networking;

import net.cbejltpycbl.pozcraft.Pozcraft;
import net.cbejltpycbl.pozcraft.networking.packet.ItemStackSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier ITEM_SYNC = new Identifier(Pozcraft.MOD_ID, "item_sync");

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC, ItemStackSyncS2CPacket::receive);
    }
}
