package tfar.enchantedbookredesign;

import net.minecraft.client.render.VertexConsumer;

public class TintedVertexConsumer implements VertexConsumer {

	public static VertexConsumer withTint(VertexConsumer tint, int color) {
		return new TintedVertexConsumer(tint, color);
	}

	private final VertexConsumer vertexConsumer;
	private final int color;

	public TintedVertexConsumer(VertexConsumer vertexConsumer, int color) {

		this.vertexConsumer = vertexConsumer;
		this.color = color;
	}

	public VertexConsumer vertex(double x, double y, double z) {
		this.vertexConsumer.vertex(x, y, z);
		return this;
	}

	public VertexConsumer color(int red, int green, int blue, int alpha) {
		int r = color >> 16 & 0xff;
		int g = color >> 8 & 0xff;
		int b = color & 0xff;
		this.vertexConsumer.color(r, g, b, alpha);
		return this;
	}

	public VertexConsumer texture(float u, float v) {
		this.vertexConsumer.texture(u, v);
		return this;
	}

	public VertexConsumer overlay(int u, int v) {
		this.vertexConsumer.overlay(u, v);
		return this;
	}

	public VertexConsumer light(int u, int v) {
		this.vertexConsumer.light(u, v);
		return this;
	}

	public VertexConsumer normal(float x, float y, float z) {
		this.vertexConsumer.normal(x, y, z);
		return this;
	}

	public void vertex(float x, float y, float z, float red, float green, float blue, float alpha, float u, float v, int overlay, int light, float normalX, float normalY, float normalZ) {

		float r = (color >> 16 & 0xff) / 255f;
		float g = (color >> 8 & 0xff) / 255f;
		float b = (color & 0xff) / 255f;

		this.vertexConsumer.vertex(x, y, z, r, g, b, alpha, u, v, overlay, light, normalX, normalY, normalZ);
	}

	public void next() {
		this.vertexConsumer.next();
	}
}
