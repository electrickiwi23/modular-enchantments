package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * A mixin for SomeJavaFile.
 */
@Mixin(EnchantmentHelper.class)
public class KnockBackMixin {

    // signal that we want to inject into a method
    @Inject(
            method = "getKnockbackBonus",  // the method's signature, or just its name
            at = @At("HEAD"),  // signal that this void should be run at the method HEAD, meaning the first opcode
            cancellable = true
    )
    // note that this void here can be named whatever you want, as long as it doesn't conflict with other methods
    // for style reasons, most people just keep the name of this the same as the name of the target method.
    private static void bat(LivingEntity player, CallbackInfoReturnable<Integer> cir) {


        if (EnchantmentHelper.getEnchantmentLevel(Registration.BATTING.get(), player) > 0) {
            cir.setReturnValue(EnchantmentHelper.getEnchantmentLevel(Registration.BATTING.get(), player)*2);
            cir.cancel();
        }
        
    }

}