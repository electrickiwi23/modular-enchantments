package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
            method = "onPlayerStoppedUsing",  // the method's signature, or just its name
            name = "f",
            at = @At(value = "INVOKE",target="Lnet/minecraft/item/ArrowItem;createArrow(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/entity/projectile/AbstractArrowEntity;")// signal that this void should be run at the method HEAD, meaning the first opcode
            )
    // note that this void here can be named whatever you want, as long as it doesn't conflict with other methods
    // for style reasons, most people just keep the name of this the same as the name of the target method.
    private float snipe(float f,ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        int sniperLevel = EnchantmentHelper.getMaxEnchantmentLevel(Registration.SNIPER.get(), entityLiving);
        if (sniperLevel > 0) {
            f*=1.3;        }
        return f;
    }

}