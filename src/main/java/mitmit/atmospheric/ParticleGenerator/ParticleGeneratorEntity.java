package mitmit.atmospheric.ParticleGenerator;

import mitmit.atmospheric.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class ParticleGeneratorEntity extends BlockEntity implements Container, MenuProvider {
    private final NonNullList<ItemStack> items = NonNullList.withSize(0, ItemStack.EMPTY);

    public ParticleGeneratorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PARTICLE_GENERATOR_ENTITY, pos, state);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, ParticleGeneratorEntity entity) {
        //entity.ticksSinceLast++;
        //CALLED EVERY TICK
        //level.addParticle(, blockPos.getX(), blockPos.getX(), blockPos.getX(), 5, 5, 5 );
    }

    @Override
    @NonNull
    public Component getDisplayName() {
        return Component.translatable("block.atmospheric.particle_generator");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new ParticleGeneratorMenu(containerId, inventory, this);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        //output.putInt("clicks", clicks);
        ContainerHelper.saveAllItems(output, items);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);
        //clicks = input.getIntOr("clicks", 0);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    //IMPLEMENTED FOR CONTAINER
    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int slot) {
        return null;
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        return null;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return null;
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {

    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }

    @Override
    public void clearContent() {

    }
}