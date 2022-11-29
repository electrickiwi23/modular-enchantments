package mintnetwork.modularenchantments.Entities;

import com.mojang.blaze3d.vertex.PoseStack;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EnchantMiteRenderer extends MobRenderer<EnchantMiteEntity, EnchantMiteModel<EnchantMiteEntity>> {
   public static final ResourceLocation ENCHANTMITE_TEXTURES = new ResourceLocation(ModularEnchantments.MOD_ID, "textures/entity/enchantmite.png");

   public EnchantMiteRenderer(EntityRendererProvider.Context renderManagerIn) {
      super(renderManagerIn, new EnchantMiteModel<>(renderManagerIn.bakeLayer(ModelLayers.ENDERMITE)), 0.3F);
   }

   public void render(EnchantMiteEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
      this.getModel().buffer = bufferIn;
      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
   }

   protected float getFlipDegrees(EnchantMiteEntity entityLivingBaseIn) {
      return 180.0F;
   }

   /**
    * Returns the location of an entity's texture.
    */

   public ResourceLocation getTextureLocation(EnchantMiteEntity entity) {

      return ENCHANTMITE_TEXTURES;
   }
}