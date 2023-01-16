package net.cbejltpycbl.pozcraft.world.dimension;

import net.cbejltpycbl.pozcraft.Pozcraft;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
public class ModDimensions {
    public static final RegistryKey<World> TOSK_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(Pozcraft.MOD_ID, "tosk"));

    public static final RegistryKey<DimensionType> TOSK_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            TOSK_DIMENSION_KEY.getValue());

    public static void register() {
        Pozcraft.LOGGER.debug(Pozcraft.MOD_ID + ": регистрация измерений");
    }
}
