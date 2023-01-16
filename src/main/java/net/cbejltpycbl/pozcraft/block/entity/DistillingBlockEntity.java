package net.cbejltpycbl.pozcraft.block.entity;

import net.cbejltpycbl.pozcraft.block.custom.DistillingBlock;
import net.cbejltpycbl.pozcraft.networking.ModMessages;
import net.cbejltpycbl.pozcraft.recipe.DistillingRecipe;
import net.cbejltpycbl.pozcraft.screen.DistillingBlockScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DistillingBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 6000;

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    private Item currentItem;

    public DistillingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DISTILLATION_APPARATUS, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return DistillingBlockEntity.this.progress;
                    case 1: return DistillingBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: DistillingBlockEntity.this.progress = value; break;
                    case 1: DistillingBlockEntity.this.maxProgress = value; break;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    public ItemStack getRenderStack() {
        return this.getStack(2);
    }

    public void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }

    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        Inventories.writeNbt(nbtCompound, this.inventory, true);
        return nbtCompound;
    }

    @Override
    public void markDirty() {
        if(!world.isClient()) {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeInt(inventory.size());
            for(int i = 0; i < inventory.size(); i++) {
                data.writeItemStack(inventory.get(i));
            }
            data.writeBlockPos(getPos());

            for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(player, ModMessages.ITEM_SYNC, data);
            }
        }

        super.markDirty();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("item.pozcraft.distillation_apparatus");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new DistillingBlockScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("distillation_apparatus.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("distillation_apparatus.progress");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(DistillingBlock.FACING);

        if(side == Direction.UP && slot == 0) {
            return true;
        } else if (side == Direction.DOWN) {
            return false;
        } else {
            return switch (localDir) {
                default -> side.getOpposite() == Direction.NORTH && slot == 2 ||
                        side.getOpposite() == Direction.EAST && slot == 1 ||
                        side.getOpposite() == Direction.WEST && slot == 1;
                case EAST -> side.rotateYClockwise() == Direction.NORTH && slot == 2 ||
                        side.rotateYClockwise() == Direction.EAST && slot == 1 ||
                        side.rotateYClockwise() == Direction.WEST && slot == 1;
                case SOUTH -> side == Direction.NORTH && slot == 2 ||
                        side == Direction.EAST && slot == 1 ||
                        side == Direction.WEST && slot == 1;
                case WEST -> side.rotateYCounterclockwise() == Direction.NORTH && slot == 2 ||
                        side.rotateYCounterclockwise() == Direction.EAST && slot == 1 ||
                        side.rotateYCounterclockwise() == Direction.WEST && slot == 1;
            };
        }
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return slot == 2 && !(stack.getItem() == Items.POTION && !stack.hasGlint());
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, DistillingBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe(entity)) {
            if(!state.get(DistillingBlock.LIT).booleanValue()) {
                    world.setBlockState(blockPos, state.with(DistillingBlock.LIT, true));
            }

            entity.setCraftTime(entity);
            entity.progress++;
            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            if(state.get(DistillingBlock.LIT).booleanValue()) {
                world.setBlockState(blockPos, state.with(DistillingBlock.LIT, false));
            }

            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    public void setCraftTime(DistillingBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<DistillingRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(DistillingRecipe.Type.INSTANCE, inventory, entity.getWorld());

        this.propertyDelegate.set(1, match.get().getTime());
    }

    private static void craftItem(DistillingBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<DistillingRecipe> recipe = entity.getWorld().getRecipeManager()
                .getFirstMatch(DistillingRecipe.Type.INSTANCE, inventory, entity.getWorld());

        if(hasRecipe(entity)) {
            entity.removeStack(0, 1);
            entity.removeStack(1, 1);
            entity.removeStack(2, 1);

            entity.setStack(2, new ItemStack(recipe.get().getOutput().getItem(), 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(DistillingBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<DistillingRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(DistillingRecipe.Type.INSTANCE, inventory, entity.getWorld());

        if(inventory.getStack(0).getItem() != entity.getCurrentItem()) {
            entity.resetProgress();
            entity.setCurrentItem(inventory.getStack(0).getItem());
        }

        return match.isPresent() && canInsertItemIntoOutputSlot(inventory)
                && hasSugar(inventory);
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getItem() == Items.POTION && !inventory.getStack(2).hasGlint();
    }

    private static boolean hasSugar(SimpleInventory inventory) {
        return inventory.getStack(1).getItem() == Items.SUGAR;
    }
}
