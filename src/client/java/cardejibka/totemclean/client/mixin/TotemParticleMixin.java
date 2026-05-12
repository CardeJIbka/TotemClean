package cardejibka.totemclean.client.mixin;

import cardejibka.totemclean.client.TotemCleanClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.TotemParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Particle.class)
public abstract class TotemParticleMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void killIfTotemSuppressed(CallbackInfo ci) {
        if (TotemCleanClient.suppressingMyTotem && (Object) this instanceof TotemParticle) {
            ((Particle) (Object) this).remove();
            ci.cancel();
        }
    }
}