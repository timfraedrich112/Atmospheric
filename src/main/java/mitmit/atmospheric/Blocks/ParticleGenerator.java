package mitmit.atmospheric.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ParticleGenerator extends Block {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public ParticleGenerator(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ACTIVATED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (!player.getAbilities().mayBuild) {
            // Skip if the player isn't allowed to modify the level.
            return InteractionResult.PASS;
        } else {
            // Flip the value of activated and save the new blockstate.
            level.setBlockAndUpdate(pos, state.setValue(ACTIVATED, !state.getValue(ACTIVATED)));

            // Play a click sound.
            level.playSound(player, pos, SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);

            return InteractionResult.SUCCESS;
        }
    }
}