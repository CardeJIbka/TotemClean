package cardejibka.totemclean.client;

import cardejibka.totemclean.TotemClean;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TotemClean.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TotemCleanClient {

    public static boolean suppressingMyTotem = false;
    private static int ticksRemaining = 0;
    private static final int SUPPRESS_TICKS = 80;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        if (ticksRemaining > 0) {
            ticksRemaining--;
            if (ticksRemaining == 0) {
                suppressingMyTotem = false;
            }
        }
    }

    public static void activateTotemSuppression() {
        suppressingMyTotem = true;
        ticksRemaining = SUPPRESS_TICKS;
    }
}