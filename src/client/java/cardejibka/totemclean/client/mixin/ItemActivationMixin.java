package cardejibka.totemclean.client.mixin;

import net.minecraft.client.player.ItemActivation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemActivation.class)
public class ItemActivationMixin {

    @Inject(
            method = "activate(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/util/RandomSource;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelTotemAnimation(ItemStack stack, RandomSource random, CallbackInfo ci) {
        if (stack.is(Items.TOTEM_OF_UNDYING)) {
            ci.cancel();
        }
    }
}