package teamreborn.assembly.blockentity;

import io.github.prospector.silk.fluid.FluidContainer;
import io.github.prospector.silk.util.ContainerInteraction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;
import teamreborn.assembly.registry.AssemblyBlockEntities;
import teamreborn.assembly.registry.AssemblyFluids;
import teamreborn.assembly.util.block.AssemblyProperties;

public class TreeTapBlockEntity extends BlockEntity implements Tickable {
	public TreeTapBlockEntity() {
		super(AssemblyBlockEntities.TREE_TAP);
	}

	@Override
	public void tick() {
		if (!world.isClient) {
			if (world.getTime() % (25 + world.getRandom().nextInt(15)) == 0) {
				BlockEntity downEntity = world.getBlockEntity(pos.offset(Direction.DOWN));
				boolean pouring = downEntity instanceof FluidContainer;
				if (pouring) {
					pouring = ((FluidContainer) downEntity).tryInsertFluid(Direction.UP, AssemblyFluids.LATEX, 1, ContainerInteraction.EXECUTE);
				}
				BlockState state = world.getBlockState(pos);
				if (state.get(AssemblyProperties.POURING) != pouring) {
					world.setBlockState(pos, state.with(AssemblyProperties.POURING, pouring));
				}
			}
		}
	}
}
