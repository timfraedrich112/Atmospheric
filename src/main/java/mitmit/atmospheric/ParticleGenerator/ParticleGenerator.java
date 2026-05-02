package mitmit.atmospheric.ParticleGenerator;

import com.mojang.serialization.MapCodec;
import mitmit.atmospheric.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ParticleGenerator extends BaseEntityBlock {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public ParticleGenerator(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ACTIVATED, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(ParticleGenerator::new);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ParticleGeneratorEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.PARTICLE_GENERATOR_ENTITY, ParticleGeneratorEntity::tick);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        }
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof ParticleGeneratorEntity particleGeneratorEntity) {
            player.openMenu(particleGeneratorEntity);
            level.playSound(player, pos, SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            //level.setBlockAndUpdate(pos, state.setValue(ACTIVATED, !state.getValue(ACTIVATED)));
        }
        return InteractionResult.PASS;
    }
}