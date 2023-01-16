package net.cbejltpycbl.pozcraft.items;

import net.cbejltpycbl.pozcraft.items.kalzak.common.inventory.BackpackInventory;
import net.cbejltpycbl.pozcraft.items.kalzak.common.inventory.BackpackScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class KalzakItem extends Item {

    private final int width;
    private final int height;

    public KalzakItem(int width, int height, Settings settings) {
        super(settings);
        this.width = width;
        this.height = height;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        final ItemStack stack = user.getStackInHand(hand);

        if (!user.isSneaking()) {
            if (!world.isClient()) {
                openScreen(user, stack);
            } else {
                user.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 1.0F);
            }

            return TypedActionResult.consume(stack);
        } else if (world.isClient()) {
            return TypedActionResult.pass(stack);
        }

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.pozcraft.kalzak"));
    }

    public static final void openScreen(PlayerEntity user, ItemStack stack) {
        final KalzakItem bp = (KalzakItem) stack.getItem();

        user.openHandledScreen(new ExtendedScreenHandlerFactory() {
            @Override
            public Text getDisplayName() {
                return stack.getName();
            }

            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                return new BackpackScreenHandler(inv, syncId, new BackpackInventory(bp.width, bp.height, stack));
            }

            @Override
            public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf buf) {
                buf.writeInt(bp.width);
                buf.writeInt(bp.height);
            }
        });
    }
    @Override
    public boolean canBeNested() {
        return false;
    }
}
