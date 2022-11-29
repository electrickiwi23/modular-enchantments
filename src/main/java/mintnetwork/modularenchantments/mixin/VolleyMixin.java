package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;


/**
 * A mixin for SomeJavaFile.
 */
@Mixin(BowItem.class)
public class VolleyMixin {

    // signal that we want to inject into a method
    @Inject(
            method = "releaseUsing",
            locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(value = "INVOKE",target="Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V")
            )

    private void Volley(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft, CallbackInfo ci, Player player, boolean flag, ItemStack itemStack, int i, float f, boolean flag1, ArrowItem arrowitem, AbstractArrow abstractarrowentity) {
        int VolleyLevel = stack.getEnchantmentLevel(Registration.VOLLEY.get());
        boolean Sniper = stack.getEnchantmentLevel(Registration.SNIPER.get()) > 0 ;
        if (VolleyLevel > 0)  abstractarrowentity.addTag("volley_arrow");
        if (Sniper) {
            abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage()/1.3);
            abstractarrowentity.setCritArrow(f==1.3f);
        }

        for (int v = 0; v < VolleyLevel*3; v++) {
            AbstractArrow arrow = arrowitem.createArrow(worldIn, itemStack, player);
            arrow =  ((BowItem) (Object) this).customArrow(arrow);
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F + 1.5F*VolleyLevel+2F);

            arrow.setCritArrow(abstractarrowentity.isCritArrow());
            arrow.setBaseDamage(abstractarrowentity.getBaseDamage()/2.5);
            arrow.setKnockback(abstractarrowentity.getKnockback());
            arrow.setRemainingFireTicks(arrow.getRemainingFireTicks());
            arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

            arrow.addTag("volley_arrow");
            worldIn.addFreshEntity(arrow);



        }

    }

}