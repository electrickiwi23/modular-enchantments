package mintnetwork.modularenchantments.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Arrow.class)
public class ArrowHitMixin {

    // signal that we want to inject into a method
    @Redirect(
            method = "doPostHurtEffects",

            at = @At(value = "INVOKE",target="Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z")
    )
    public boolean addEffect(LivingEntity target, MobEffectInstance mobeffectinstance, Entity entity){
        int amp = mobeffectinstance.getAmplifier();
        if (((Arrow) (Object) this).getTags().contains("Extra_Potent")) amp++;
        return target.addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), Math.max(mobeffectinstance.getDuration() / 8, 1), amp, mobeffectinstance.isAmbient(), mobeffectinstance.isVisible()), entity);
    }
}
