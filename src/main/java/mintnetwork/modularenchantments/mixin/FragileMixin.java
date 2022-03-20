package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.Enchantments.FragilityCurse;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * A mixin for SomeJavaFile.
 */
@Mixin(ItemStack.class)
public class FragileMixin {

    // signal that we want to inject into a method
    @ModifyVariable(
            method = "attemptDamageItem",  // the method's signature, or just its name
            name = "amount",
            index = 1,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEnchantmentLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I"),
            argsOnly = true
    )
    // note that this void here can be named whatever you want, as long as it doesn't conflict with other methods
    // for style reasons, most people just keep the name of this the same as the name of the target method.
    private int attemptDamageItem(int amount, int a, Random rand, @Nullable ServerPlayerEntity damager) {


        if (EnchantmentHelper.getEnchantmentLevel(Registration.FRAGILITY.get(), (ItemStack) (Object) this) > 0) {
            for (int k = 0; k < amount; ++k) {
                amount += FragilityCurse.getRandomDamage((ItemStack) (Object) this,rand);
            }
        }
        if (EnchantmentHelper.getEnchantmentLevel(Registration.PAIN.get(), (ItemStack) (Object) this) > 0) {
            if (damager != null && Math.random() < .3) {
                damager.attackEntityFrom(DamageSource.MAGIC, 1);
            }
        }
        return amount;
        
    }

}