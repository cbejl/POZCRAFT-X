package net.cbejltpycbl.pozcraft.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BundleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;


public class CigaretteCase extends BundleItem {

    public CigaretteCase(Settings settings) {
        super(settings);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType == ClickType.RIGHT) {
            ItemStack itemStack = slot.getStack();
            if (itemStack.isOf(Items.ROTTEN_FLESH)) {
                return super.onStackClicked(stack, slot, clickType, player);
            }
        }
        return false;
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT && slot.canTakePartial(player)) {
            if (otherStack.isEmpty()) {
                return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
            } else if (otherStack.isOf(Items.ROTTEN_FLESH)) {
                return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
