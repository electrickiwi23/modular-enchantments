package mintnetwork.modularenchantments.Entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SilverfishModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.loot.functions.Smelt;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

import javax.annotation.Nullable;

public class EnchantMiteRenderer extends MobRenderer<EnchantMiteEntity, EnchantMiteModel<EnchantMiteEntity>> {
   public static final ResourceLocation ENCHANTMITE_TEXTURES = new ResourceLocation(ModularEnchantments.MOD_ID, "textures/entity/enchantmite.png");

   public EnchantMiteRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new EnchantMiteModel<>(), 0.3F);
      new RenderState.TextureState(ItemRenderer.RES_ITEM_GLINT,true,false);
   }

   @Override
   public void render(EnchantMiteEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
      this.getEntityModel().buffer = bufferIn;
      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
   }

   protected float getDeathMaxRotation(EnchantMiteEntity entityLivingBaseIn) {
      return 180.0F;
   }

   /**
    * Returns the location of an entity's texture.
    */

   public ResourceLocation getEntityTexture(EnchantMiteEntity entity) {

      return ENCHANTMITE_TEXTURES;
   }
}