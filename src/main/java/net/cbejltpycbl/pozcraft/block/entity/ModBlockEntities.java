package net.cbejltpycbl.pozcraft.block.entity;

import net.cbejltpycbl.pozcraft.Pozcraft;
import net.cbejltpycbl.pozcraft.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<DistillingBlockEntity> DISTILLATION_APPARATUS;

    public static void registerBlockEntities(){
        DISTILLATION_APPARATUS = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Pozcraft.MOD_ID, "distillation_apparatus"),
                FabricBlockEntityTypeBuilder.create(DistillingBlockEntity::new,
                        ModBlocks.DISTILLATION_APPARATUS).build(null));
    }
}
