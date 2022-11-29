package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public class CrossbowShootMixin {


        // signal that we want to inject into a method
        @Inject(
                method = "shootProjectile",  // the method's signature, or just its name
                locals = LocalCapture.CAPTURE_FAILSOFT,
                at =  @At(value = "INVOKE", target =("Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"))
        )

   private static void Arrow(Level p_40895_, LivingEntity p_40896_, InteractionHand p_40897_, ItemStack p_40898_, ItemStack p_40899_, float p_40900_, boolean p_40901_, float p_40902_, float p_40903_, float p_40904_, CallbackInfo ci, boolean flag, Projectile projectile) {
        if (projectile instanceof AbstractArrow) {
            if (p_40898_.getEnchantmentLevel(Registration.DAMPENED.get()) > 0) {
                ((AbstractArrow) projectile).setCritArrow(false);
                ((AbstractArrow) projectile).setBaseDamage(.5);
                ((AbstractArrow) projectile).setKnockback(0);
            }
            if (p_40898_.getEnchantmentLevel(Registration.POTENCY.get()) > 0) {
                projectile.addTag("Extra_Potent");
            }
        }
   }
}
