package net.cbejltpycbl.pozcraft.mixin;

import net.cbejltpycbl.pozcraft.screen.PozpunkSplash;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashOverlay.class)
@Environment(EnvType.CLIENT)
public class PozcraftScreen {

    @Shadow
    @Final
    static Identifier LOGO;

    @Inject(method = "init(Lnet/minecraft/client/MinecraftClient;)V", at = @At("HEAD"), cancellable=true)
    private static void init(MinecraftClient client, CallbackInfo ci) {
        client.getTextureManager().registerTexture(LOGO, new PozpunkSplash(LOGO));
        ci.cancel();
    }

    @Shadow
    @Final
    static int MOJANG_RED = ColorHelper.Argb.getArgb(255, 18, 18, 18);
}
