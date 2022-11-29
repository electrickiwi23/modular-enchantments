package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.Enchantments.FragilityCurse;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import javax.annotation.Nullable;

/**
 * A mixin for SomeJavaFile.
 */
@Mixin(ItemStack.class)
public class FragileMixin {

    // signal that we want to inject into a method
    @ModifyVariable(
            method = "hurt",  // the method's signature, or just its name
            name = "amount",
            index = 1,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getItemEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/item/ItemStack;)I"),
            argsOnly = true
    )
    // note that this void here can be named whatever you want, as long as it doesn't conflict with other methods
    // for style reasons, most people just keep the name of this the same as the name of the target method.
    private int attemptDamageItem(int amount, int a, RandomSource rand, @Nullable ServerPlayer damager) {


        if (EnchantmentHelper.getItemEnchantmentLevel(Registration.FRAGILITY.get(), (ItemStack) (Object) this) > 0) {
            for (int k = 0; k < amount; ++k) {
                amount += FragilityCurse.getRandomDamage((ItemStack) (Object) this,rand);
            }
        }
        if (EnchantmentHelper.getItemEnchantmentLevel(Registration.PAIN.get(), (ItemStack) (Object) this) > 0) {
            if (damager != null && Math.random() < .3) {
                damager.hurt(DamageSource.MAGIC, 1);
            }
        }
        return amount;
        
    }

}