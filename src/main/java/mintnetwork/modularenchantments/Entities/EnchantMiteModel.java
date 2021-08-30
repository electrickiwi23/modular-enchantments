package mintnetwork.modularenchantments.Entities;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.vertex.VertexBuilderUtils;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EndermiteModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

public class EnchantMiteModel<T extends Entity> extends EndermiteModel<T> {
    public IRenderTypeBuffer buffer = null;
    public EnchantMiteModel(){
        super();
    }

    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha){

        super.render(matrixStackIn,net.minecraft.client.renderer.ItemRenderer.getEntityGlintVertexBuilder(buffer, this.getRenderType(EnchantMiteRenderer.ENCHANTMITE_TEXTURES), false, true), packedLightIn, packedOverlayIn, red, green, blue, alpha);

    }
}
