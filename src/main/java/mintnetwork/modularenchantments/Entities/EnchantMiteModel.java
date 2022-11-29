package mintnetwork.modularenchantments.Entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EndermiteModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.Entity;

public class EnchantMiteModel<T extends Entity> extends EndermiteModel<T> {
    public MultiBufferSource buffer = null;
    public EnchantMiteModel(ModelPart part){
        super(part);
    }

    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha){

        super.renderToBuffer(matrixStackIn, ItemRenderer.getFoilBuffer(buffer, this.renderType(EnchantMiteRenderer.ENCHANTMITE_TEXTURES), false, true), packedLightIn, packedOverlayIn, red, green, blue, alpha);

    }
}
