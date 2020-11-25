package tfar.enchantedbookredesign.mixin;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexConsumers;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.enchantedbookredesign.Captures;
import tfar.enchantedbookredesign.Hooks;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.enchantedbookredesign.ModRenderLayer;
import tfar.enchantedbookredesign.TintedVertexConsumer;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

	//capture the itemstack
	@Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At("HEAD"))
	private void capturestack(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices,
			VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
		Captures.stack = stack;
	}

	//items
	//swap the vanilla vertexconsumer with our own
	@Inject(method = "getDirectItemGlintConsumer", at = @At("HEAD"), cancellable = true)
	private static void tinteditemglint(VertexConsumerProvider provider, RenderLayer layer, boolean solid, boolean glint, CallbackInfoReturnable<VertexConsumer> cir) {
		if (glint) {
			VertexConsumer builder2 = VertexConsumers.dual(
				TintedVertexConsumer.withTint(
					provider.getBuffer(solid ? ModRenderLayer.TINTED_GLINT_DIRECT : ModRenderLayer.TINTED_ENTITY_GLINT_DIRECT),
					Hooks.getColor(Captures.stack)),
				provider.getBuffer(layer));
			cir.setReturnValue(builder2);
		}
	}

	//armor
	//swap the vanilla vertexconsumer with our own
	@Inject(method = "getItemGlintConsumer", at = @At("HEAD"), cancellable = true)
	private static void alsotintedglint(VertexConsumerProvider provider, RenderLayer layer, boolean solid, boolean glint, CallbackInfoReturnable<VertexConsumer> cir) {
		if (glint) {
			VertexConsumer builder2 = VertexConsumers.dual(
				TintedVertexConsumer.withTint(
					provider.getBuffer(solid ? ModRenderLayer.TINTED_ARMOR_GLINT : ModRenderLayer.TINTED_ARMOR_ENTITY_GLINT),
					Hooks.getColor(Captures.stack)),
				provider.getBuffer(layer));
			cir.setReturnValue(builder2);
		}
	}
}
