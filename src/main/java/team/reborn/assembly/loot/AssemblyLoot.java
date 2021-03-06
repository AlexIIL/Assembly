package team.reborn.assembly.loot;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import team.reborn.assembly.item.AssemblyItems;

public class AssemblyLoot {
	private static final Identifier CAVE_SPIDER = new Identifier("minecraft", "entities/cave_spider");

	public static void register() {
		LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
			if (CAVE_SPIDER.equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
					.withCondition(KilledByPlayerLootCondition.builder())
					.withCondition(RandomChanceWithLootingLootCondition.builder(0.02F, (float) 1 / 3))
					.withEntry(ItemEntry.builder(AssemblyItems.VENOMOUS_FANG));
				supplier.withPool(poolBuilder);
			}
		});
	}
}
