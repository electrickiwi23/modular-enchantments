package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * A mixin for SomeJavaFile.
 */
@Mixin(BowItem.class)
public class SniperVelocityMixin {

    // signal that we want to inject into a method
    @ModifyVariable(
            method = "releaseUsing",  // the method's signature, or just its name
            name = "f",
            at = @At(value = "INVOKE",target="Lnet/minecraft/world/item/ArrowItem;createArrow(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/entity/projectile/AbstractArrow;")// signal that this void should be run at the method HEAD, meaning the first opcode
            )
    // note that this void here can be named whatever you want, as long as it doesn't conflict with other methods
    // for style reasons, most people just keep the name of this the same as the name of the target method.
    private float snipe(float f, ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        int sniperLevel = EnchantmentHelper.getEnchantmentLevel(Registration.SNIPER.get(), entityLiving);
        if (sniperLevel > 0) {
            f*=1.3;        }
        return f;
    }

}