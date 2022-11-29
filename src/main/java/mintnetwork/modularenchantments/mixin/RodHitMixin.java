package mintnetwork.modularenchantments.mixin;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHook.class)
public abstract class RodHitMixin {

    // signal that we want to inject into a method
    @Inject(
            method = "onHitEntity",
            at = @At(value = "INVOKE",target="Lnet/minecraft/world/entity/projectile/Projectile;onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V")
    )
    public void ChangeVelocity(EntityHitResult p_37144_, CallbackInfo ci){

        int level = EnchantmentHelper.getEnchantmentLevel(Registration.BARBED.get(),((FishingHook)(Object)this).getPlayerOwner());

        if (level != 0) p_37144_.getEntity().hurt(DamageSource.playerAttack(((FishingHook)(Object)this).getPlayerOwner()),level * 2);
    }
}
