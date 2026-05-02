package mitmit.atmospheric.ParticleGenerator;

import mitmit.atmospheric.ModMenuType;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ParticleGeneratorMenu extends AbstractContainerMenu {
    private final Container container;

    // Client-side constructor
    public ParticleGeneratorMenu(final int containerId, final Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(0));
    }

    // Server-side constructor
    public ParticleGeneratorMenu(final int containerId, final Inventory inventory, final Container container) {
        super(ModMenuType.PARTICLE_GENERATOR_MENU_TYPE, containerId);
        checkContainerSize(container, 0);
        this.container = container;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}