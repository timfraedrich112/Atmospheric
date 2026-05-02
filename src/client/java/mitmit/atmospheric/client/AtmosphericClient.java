package mitmit.atmospheric.client;

import mitmit.atmospheric.ModMenuType;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class AtmosphericClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		MenuScreens.register(ModMenuType.PARTICLE_GENERATOR_MENU_TYPE, ParticleGeneratorScreen::new);
	}
}