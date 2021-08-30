package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class ArrowUpdateListener {

    public static void onEntityShot(Entity user, ArrowEntity arrow, int level) {
        if (arrow != null) {
            double damage = arrow.getDamage();
            arrow.setDamage(damage/2.5);
            BowShootListener.volleyArrows.add(arrow);

            ItemStack itemStack  = arrow.getArrowStack();
            ArrowItem item = (ArrowItem) itemStack.getItem();

            for (int i = 0; i < level*3; i++) {

                float pitch = (user.rotationPitch + (float) (new Random()).nextGaussian()*(1.5F*level+2F));
                float yaw = (user.rotationYaw + (float) (new Random()).nextGaussian()*(1.5F*level+2F));
                ArrowEntity arrowEntity = (ArrowEntity) item.createArrow(user.world, itemStack, (LivingEntity) user);
                arrowEntity.setDirectionAndMovement( user, pitch, yaw, 0.0F, (float) arrow.getMotion().length(), 1.0F);
                arrowEntity.setDamage(damage/2.5);
                arrowEntity.setIsCritical(arrow.getIsCritical());
                arrowEntity.setFire(arrow.getFireTimer());


                arrowEntity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                user.world.addEntity(arrowEntity);
                BowShootListener.volleyArrows.add(arrowEntity);

            }
        }

    }

    @SubscribeEvent
    public static <arrowStack> void onUpdate(TickEvent.WorldTickEvent event) {
        List<BlockPos> removeExplode = new ArrayList<BlockPos>();
        for (BlockPos pos:BlockDestroyListener.explode) {
            DamageSource.causeExplosionDamage(event.world.createExplosion(null,pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5, (float) (Math.random()*4F), Explosion.Mode.DESTROY));
            removeExplode.add(pos);
        }
        BlockDestroyListener.explode.removeAll(removeExplode);



        for (PlayerEntity p:event.world.getPlayers()) {
            for (ArrowEntity arrow: event.world.getLoadedEntitiesWithinAABB(ArrowEntity.class,new AxisAlignedBB(p.getPosX()+2,p.getPosY()+4,p.getPosZ()+2,p.getPosX()-2,p.getPosY()-1,p.getPosZ()-2))){
                if (!arrow.isOnGround()) {
                    if (arrow.ticksExisted<=1) {
                        if (BowShootListener.volleyLevel.containsKey(arrow.getShooter())) {

                            onEntityShot(arrow.getShooter(), arrow, BowShootListener.volleyLevel.get(arrow.getShooter()));
                            BowShootListener.volleyLevel.remove(arrow.getShooter());
                        }

                        if (CrossbowShootListener.supressesedlevel.containsKey(arrow.getShooter())) {
                            arrow.setIsCritical(false);
                            arrow.setDamage(0);
                        }

                        if (CrossbowShootListener.PotencyLevel.containsKey(arrow.getShooter())){

                            Potion potion = arrow.potion;
                            List<EffectInstance> newPotion = new ArrayList<>();
                            for (EffectInstance effect:potion.getEffects()) {
                                newPotion.add(new EffectInstance(effect.getPotion(),effect.getDuration(),effect.getAmplifier()+1));
                            }
                            arrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                            arrow.potion = new Potion(newPotion.toArray(new EffectInstance[]{}));
                            CrossbowShootListener.PotencyLevel.remove(arrow.getShooter());
                        }
                    }
                }
            }
            CrossbowShootListener.supressesedlevel.remove(p);
        }
    }
}
