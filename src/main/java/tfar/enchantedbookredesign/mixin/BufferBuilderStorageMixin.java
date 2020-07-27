package tfar.enchantedbookredesign.mixin;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.enchantedbookredesign.ModRenderLayer;

@Mixin(BufferBuilderStorage.class)
public abstract class BufferBuilderStorageMixin {

	@Shadow private static void assignBufferBuilder(Object2ObjectLinkedOpenHashMap<RenderLayer, BufferBuilder> mapBuildersIn, RenderLayer renderLayer) {
		throw new IllegalStateException("mixin broke");
	}

	@Inject(method = "*(Lit/unimi/dsi/fastutil/objects/Object2ObjectLinkedOpenHashMap;)V",
					at = @At(value = "INVOKE",target = "net/minecraft/client/render/RenderLayer.getEntityGlint()Lnet/minecraft/client/render/RenderLayer;"))
	private void rendertype(Object2ObjectLinkedOpenHashMap<RenderLayer, BufferBuilder> map, CallbackInfo ci) {
		assignBufferBuilder(map, ModRenderLayer.TINTED_GLINT_DIRECT);
		assignBufferBuilder(map, ModRenderLayer.TINTED_ENTITY_GLINT_DIRECT);

		assignBufferBuilder(map, ModRenderLayer.TINTED_ARMOR_GLINT);
		assignBufferBuilder(map, ModRenderLayer.TINTED_ARMOR_ENTITY_GLINT);
	}
}
