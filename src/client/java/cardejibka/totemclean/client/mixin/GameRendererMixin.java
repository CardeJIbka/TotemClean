package cardejibka.totemclean.client.mixin;

import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(
            method = "renderItemActivationAnimation",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelTotemAnimation(int width, int height, float partialTick, CallbackInfo ci) {
        ci.cancel();
    }
}