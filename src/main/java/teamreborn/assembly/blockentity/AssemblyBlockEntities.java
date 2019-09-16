package teamreborn.assembly.blockentity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import teamreborn.assembly.Assembly;
import teamreborn.assembly.block.AssemblyBlocks;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AssemblyBlockEntities {
	private static final Map<Identifier, BlockEntityType<? extends BlockEntity>> BLOCK_ENTITY_TYPES = new HashMap<>();

	public static final BlockEntityType<WoodenBarrelBlockEntity> WOODEN_BARREL = add("wooden_barrel", WoodenBarrelBlockEntity::new, AssemblyBlocks.WOODEN_BARREL);
	public static final BlockEntityType<TreeTapBlockEntity> TREE_TAP = add("tree_tap", TreeTapBlockEntity::new, AssemblyBlocks.TREE_TAP);
//	public static final BlockEntityType<TubeBlockEntity> TUBE = add("tube", TubeBlockEntity::new, AssemblyBlocks.TUBE);

	private static <T extends BlockEntity> BlockEntityType<T> add(String name, Supplier<? extends T> supplier, Block... blocks) {
		return add(name, BlockEntityType.Builder.create(supplier, blocks));
	}

	private static <T extends BlockEntity> BlockEntityType<T> add(String name, BlockEntityType.Builder<T> builder) {
		return add(name, builder.build(null));
	}

	private static <T extends BlockEntity> BlockEntityType<T> add(String name, BlockEntityType<T> blockEntityType) {
		BLOCK_ENTITY_TYPES.put(new Identifier(Assembly.MOD_ID, name), blockEntityType);
		return blockEntityType;
	}

	public static void register() {
		for (Identifier id : BLOCK_ENTITY_TYPES.keySet()) {
			Registry.register(Registry.BLOCK_ENTITY, id, BLOCK_ENTITY_TYPES.get(id));
		}
	}

	public static Map<Identifier, BlockEntityType<? extends BlockEntity>> getBlockEntityTypes() {
		return BLOCK_ENTITY_TYPES;
	}
}
