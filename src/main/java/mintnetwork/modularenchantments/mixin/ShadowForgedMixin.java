package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.Enchantments.FragilityCurse;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * A mixin for SomeJavaFile.
 */
@Mixin(EnchantmentHelper.class)
public abstract class ShadowForgedMixin {

    @ModifyVariable(
            method = "getModifierForCreature",
            name = "mutablefloat",
            at = @At(value = "INVOKE", target = "Lorg/apache/commons/lang3/mutable/MutableFloat;floatValue()F")
    )

    private static MutableFloat attemptDamageItem(MutableFloat mutableFloat, ItemStack stack, CreatureAttribute creatureAttribute) {
        int curses = 0;
        if (EnchantmentHelper.getEnchantmentLevel(Registration.SHADOWFORGED.get(), stack) > 0) {
            curses++;
            for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) {
                if (enchantment.isCurse()) curses++;
            }

        }
        mutableFloat.add(curses);
        return mutableFloat;
        
    }

}