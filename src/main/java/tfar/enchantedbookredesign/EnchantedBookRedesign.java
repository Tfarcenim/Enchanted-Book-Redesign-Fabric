package tfar.enchantedbookredesign;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.Map;

public class EnchantedBookRedesign implements ClientModInitializer {

	public static final String MODID = "enchantedbookredesign";

	@Override
	public void onInitializeClient() {
		FabricModelPredicateProviderRegistry.register(Items.ENCHANTED_BOOK, new Identifier(MODID, "level"),
						(stack, world, entity) -> {
							Map<Enchantment, Integer> enchs = EnchantmentHelper.get(stack);
							if (enchs.isEmpty())
								return 1;

							int level = 1;
							for (Map.Entry<Enchantment, Integer> entry : enchs.entrySet()) {
								if (entry.getKey().isCursed())
									return 0;

								level = Math.max(level, entry.getValue());
							}
							return level;
						});

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex != 1 ? -1 : Hooks.getColor(stack), Items.ENCHANTED_BOOK);
	}
}
