package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.Enchantments.MagmaWalker;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * A mixin for SomeJavaFile.
 */
@Mixin(LivingEntity.class)
public class MagmaMixin {

    // signal that we want to inject into a method
    @Inject(
            method = "frostWalk",  // the method's signature, or just its name
            at = @At("HEAD")  // signal that this void should be run at the method HEAD, meaning the first opcode
    )
    // note that this void here can be named whatever you want, as long as it doesn't conflict with other methods
    // for style reasons, most people just keep the name of this the same as the name of the target method.
    private void magmaWalk(BlockPos pos,CallbackInfo info) {

        int j = EnchantmentHelper.getMaxEnchantmentLevel(Registration.MAGMAWALKING.get(), ((LivingEntity) (Object) this));
        if (j > 0) {
            MagmaWalker.freezeNearby(((LivingEntity) (Object) this), ((LivingEntity) (Object) this).world, pos, j);
        }
    }

}