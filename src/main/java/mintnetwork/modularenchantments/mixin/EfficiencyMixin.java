package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * A mixin for SomeJavaFile.
 */
@Mixin(Player.class)
public class EfficiencyMixin {

    // signal that we want to inject into a method
    @ModifyVariable(
            method = "getDigSpeed",  // the method's signature, or just its name
            name = "f",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectUtil;hasDigSpeed(Lnet/minecraft/world/entity/LivingEntity;)Z")
    )
    // note that this void here can be named whatever you want, as long as it doesn't conflict with other methods
    // for style reasons, most people just keep the name of this the same as the name of the target method.
    private float attemptDamageItem(float f) {


        int i = EnchantmentHelper.getEnchantmentLevel(Registration.UTILITY.get(), (Player) (Object) (this));
        ItemStack itemstack = ((Player) (Object) this).getMainHandItem();
        if (i > 0 && !itemstack.isEmpty()) {
            f += (i * i + 1) / 2f;
        }

        return f;
    }

}