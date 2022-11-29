package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * A mixin for SomeJavaFile.
 */
@Mixin(EnchantmentHelper.class)
public class ShadowForgedMixin {

    @ModifyVariable(
            method = "getDamageBonus",
            name = "mutablefloat",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;runIterationOnItem(Lnet/minecraft/world/item/enchantment/EnchantmentHelper$EnchantmentVisitor;Lnet/minecraft/world/item/ItemStack;)V")
    )

    private static MutableFloat attemptDamageItem(MutableFloat mutableFloat, ItemStack stack) {
        int curses = 0;
        if (stack.getEnchantmentLevel(Registration.SHADOWFORGED.get()) > 0) {
            curses++;
            for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) {
                if (enchantment.isCurse()) curses++;
            }

        }
        mutableFloat.add(curses);
        return mutableFloat;
        
    }

}