package mitmit.atmospheric;

import mitmit.atmospheric.ParticleGenerator.ParticleGeneratorMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class ModMenuType {
    public static final MenuType<ParticleGeneratorMenu> PARTICLE_GENERATOR_MENU_TYPE = register("particle_generator_menu", ParticleGeneratorMenu::new);

    public static <T extends AbstractContainerMenu> MenuType<T> register(
            String name,
            MenuType.MenuSupplier<T> constructor
    ) {
        return Registry.register(BuiltInRegistries.MENU, name, new MenuType<>(constructor, FeatureFlagSet.of()));
    }

    public static void initialize() {
    }
}