package cardejibka.totemclean.client.mixin;

import cardejibka.totemclean.client.TotemCleanClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class UseTotemMixin {

    @Inject(method = "handleEntityEvent", at = @At("HEAD"))
    private void onEntityEventHead(ClientboundEntityEventPacket packet, CallbackInfo ci) {
        if (packet.getEventId() == 35) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && mc.level != null
                    && packet.getEntity(mc.level) == mc.player) {
                TotemCleanClient.activateTotemSuppression();
            }
        }
    }
}