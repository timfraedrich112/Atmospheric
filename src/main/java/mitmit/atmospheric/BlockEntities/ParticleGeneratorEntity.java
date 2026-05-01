package mitmit.atmospheric.BlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ParticleGeneratorEntity extends BlockEntity {
    public ParticleGeneratorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PARTICLE_GENERATOR_ENTITY, pos, state);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, ParticleGeneratorEntity entity) {
        //entity.ticksSinceLast++;
        //CALLED EVERY TICK
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        //output.putInt("clicks", clicks);

        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        //clicks = input.getIntOr("clicks", 0);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }
}