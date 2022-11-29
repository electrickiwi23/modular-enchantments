package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


/**
 * A mixin for SomeJavaFile.
 */
@Mixin(Player.class)
public abstract class ShieldStunMixin {

    @ModifyConstant(
            method = "disableShield",
            constant = @Constant(intValue = 100)
            )

    public int ModifyStunCounter(int constant) {
        int level = ((Player) (Object)this).getUseItem().getEnchantmentLevel(Registration.RECOVERY.get());
            if (level>0){

                constant *= (1 - .1 * level);
            }

        return constant;
    }

    }
