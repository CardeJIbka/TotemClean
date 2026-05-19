package cardejibka.totemclean.client.mixin;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(
            method = "displayItemActivation(Lnet/minecraft/world/item/ItemStack;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelTotemAnimation(ItemStack floatingItem, CallbackInfo ci) {
        if (floatingItem.is(Items.TOTEM_OF_UNDYING)) {
            ci.cancel();
        }
    }
}