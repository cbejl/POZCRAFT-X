package net.cbejltpycbl.pozcraft.items.kalzak.common;

import net.cbejltpycbl.pozcraft.Main;
import net.cbejltpycbl.pozcraft.items.kalzak.common.init.BackpackScreenHandlers;
import net.cbejltpycbl.pozcraft.items.kalzak.common.item.BackpackItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public final class BackpackMod implements ModInitializer {

    public static final String NAME = Main.MOD_ID;
    public static final String MODID = NAME.toLowerCase();

    public static final Identifier PACKET_RENAME_BACKPACK = identify("packet_rename_backpack");

    @Override
    public void onInitialize() {
        BackpackScreenHandlers.register();

        ServerPlayNetworking.registerGlobalReceiver(PACKET_RENAME_BACKPACK, (server, player, handler, buf, responseSender) -> {
            final boolean def = buf.readBoolean();
            final Hand hand = buf.readEnumConstant(Hand.class);
            final ItemStack stack = player.getStackInHand(hand);

            if (!stack.isEmpty() && stack.getItem() instanceof BackpackItem) {
                if (def) {
                    stack.removeCustomName();
                } else {
                    final String name = buf.readString(32);
                    stack.setCustomName(Text.of(name));
                }
            }
        });
    }

    public static final MutableText translate(String key, Object... params) {
        return Text.translatable(MODID + "." + key, params);
    }

    public static final Identifier identify(String name) {
        return new Identifier(MODID, name);
    }
}
