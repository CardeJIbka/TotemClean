package cardejibka.totemclean.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class TotemCleanClient implements ClientModInitializer {

	public static boolean suppressingMyTotem = false;
	private static int ticksRemaining = 0;
	private static final int SUPPRESS_TICKS = 80; // 4 секунды — перекрывает весь lifecycle частиц

	@Override
	public void onInitializeClient() {
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			if (ticksRemaining > 0) {
				ticksRemaining--;
				if (ticksRemaining == 0) {
					suppressingMyTotem = false;
				}
			}
		});
	}

	public static void activateTotemSuppression() {
		suppressingMyTotem = true;
		ticksRemaining = SUPPRESS_TICKS;
	}
}