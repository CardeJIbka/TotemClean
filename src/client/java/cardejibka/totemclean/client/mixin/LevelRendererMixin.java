package cardejibka.totemclean.client.mixin;

import cardejibka.totemclean.client.TotemCleanClient;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @Inject(
            method = "addParticle(Lnet/minecraft/core/particles/ParticleOptions;ZZDDDDDD)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void suppressTotemParticles(ParticleOptions particle,
                                        boolean overrideLimiter,
                                        boolean alwaysRender,
                                        double x, double y, double z,
                                        double dx, double dy, double dz,
                                        CallbackInfo ci) {
        if (particle.getType() == ParticleTypes.TOTEM_OF_UNDYING
                && TotemCleanClient.suppressingMyTotem) {
            ci.cancel();
        }
    }
}