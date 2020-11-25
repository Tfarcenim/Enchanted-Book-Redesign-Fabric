package tfar.enchantedbookredesign;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormats;
import org.lwjgl.opengl.GL11;


public class ModRenderLayer extends RenderPhase {
  public ModRenderLayer(String name, Runnable beginAction, Runnable endAction) {
    super(name, beginAction, endAction);
  }

  public static RenderLayer TINTED_GLINT_DIRECT = RenderLayer.of("tinted_glint_direct", VertexFormats.POSITION_COLOR_TEXTURE, GL11.GL_QUADS,
          256, RenderLayer.MultiPhaseParameters.builder().texture(new RenderPhase.Texture(Hooks.TINTED_GLINT_RL,
                  true, false)).writeMaskState(COLOR_MASK).cull(DISABLE_CULLING)
                  .depthTest(EQUAL_DEPTH_TEST).transparency(GLINT_TRANSPARENCY).texturing(GLINT_TEXTURING).build(false));

  public static RenderLayer TINTED_ENTITY_GLINT_DIRECT = RenderLayer.of("tinted_entity_glint_direct", VertexFormats.POSITION_COLOR_TEXTURE,
          GL11.GL_QUADS, 256, RenderLayer.MultiPhaseParameters.builder().texture(new RenderPhase.Texture(Hooks.TINTED_GLINT_RL,
                  true, false)).writeMaskState(COLOR_MASK).cull(DISABLE_CULLING).depthTest(EQUAL_DEPTH_TEST)
                  .transparency(GLINT_TRANSPARENCY).texturing(ENTITY_GLINT_TEXTURING).build(false));

  public static RenderLayer TINTED_ARMOR_ENTITY_GLINT = RenderLayer.of("tinted_armor_entity_glint",
          VertexFormats.POSITION_COLOR_TEXTURE, GL11.GL_QUADS,
          256,
          RenderLayer.MultiPhaseParameters.builder().texture(new RenderPhase.Texture(Hooks.TINTED_GLINT_RL,
                  true, false)).
                  writeMaskState(COLOR_MASK)
                  .cull(DISABLE_CULLING).depthTest(EQUAL_DEPTH_TEST).transparency(GLINT_TRANSPARENCY).texturing(ENTITY_GLINT_TEXTURING)
                  .layering(VIEW_OFFSET_Z_LAYERING).build(false));

  public static RenderLayer TINTED_ARMOR_GLINT = RenderLayer.of("tinted_armor_glint", VertexFormats.POSITION_COLOR_TEXTURE, GL11.GL_QUADS,
          256, RenderLayer.MultiPhaseParameters.builder().texture(new RenderPhase.Texture(Hooks.TINTED_GLINT_RL, true, false))
                  .writeMaskState(COLOR_MASK).cull(DISABLE_CULLING).depthTest(EQUAL_DEPTH_TEST).transparency(GLINT_TRANSPARENCY).texturing(GLINT_TEXTURING)
                  .layering(VIEW_OFFSET_Z_LAYERING).build(false));

}
