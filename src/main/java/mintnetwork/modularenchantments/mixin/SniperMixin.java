package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * A mixin for SomeJavaFile.
 */
@Mixin(BowItem.class)
public class SniperMixin {

    // signal that we want to inject into a method
    @Inject(
            method = "onPlayerStoppedUsing",
            locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(value = "INVOKE",target="Lnet/minecraft/item/BowItem;customArrow(Lnet/minecraft/entity/projectile/AbstractArrowEntity;)Lnet/minecraft/entity/projectile/AbstractArrowEntity;",by = -1)
            )

    private void snipe(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft, CallbackInfo ci, PlayerEntity playerEntity, boolean flag, ItemStack itemStack, int i, float f, boolean flag1, ArrowItem arrowItem, AbstractArrowEntity abstractarrowentity) {
        int sniperLevel = EnchantmentHelper.getMaxEnchantmentLevel(Registration.SNIPER.get(), entityLiving);
        if (sniperLevel > 0) {
            abstractarrowentity.setDamage(abstractarrowentity.getDamage()/1.3);
            if (f == 1.0F||f ==1.3F) {
                abstractarrowentity.setIsCritical(true);
            }
        }
    }

}