package net.cbejltpycbl.pozcraft.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;

import java.util.Random;

public class PozcraftFoodComponents {
    public static final FoodComponent BEER = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 14400, 0), 1)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 2400, 0), 1)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 0), 0.95f).build();

    public static final FoodComponent MOONSHINE = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 14400, 1), 0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 9600, 0), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 9600, 0), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 10, 2), 0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 9600, 2), 1f)
            .build();

    public static final FoodComponent ICE_CIGARETTE = new FoodComponent.Builder().hunger(4).saturationModifier(0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0), 0.5f)
            .build();

}
