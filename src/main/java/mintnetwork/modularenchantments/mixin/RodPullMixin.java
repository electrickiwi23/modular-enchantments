package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FishingHook.class)
public abstract class RodPullMixin {

    // signal that we want to inject into a method
    @ModifyVariable(
            method = "pullEntity",
            name = "vec3",
            at = @At(value = "INVOKE",target="Lnet/minecraft/world/entity/Entity;getDeltaMovement()Lnet/minecraft/world/phys/Vec3;")
    )
    public Vec3 ChangeVelocity(Vec3 value){

        int level = EnchantmentHelper.getEnchantmentLevel(Registration.YANKING.get(),((FishingHook)(Object)this).getPlayerOwner());
        System.out.println(level);
        return value.scale(level * .66 + 1);

    }

}