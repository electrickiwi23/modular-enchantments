package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.HeavyCurse;
import mintnetwork.modularenchantments.Enchantments.RepulsionEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.block.AnvilBlock;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEngine;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.particle.EnchantmentTableParticle;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.EnchantingTableTileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class EntityHurtListener {

    public static Map<Entity,Integer> Countered = new HashMap<>();
    public static Map<Entity,Integer> Countered2 = new HashMap<>();

    @SubscribeEvent
    public static void onUpdate(LivingHurtEvent event) {
        float originalDamage = event.getAmount() ;
        LivingEntity victim = event.getEntityLiving();
        Entity attacker = event.getSource().getImmediateSource();




        if (event.getEntityLiving().canBlockDamageSource(event.getSource())){

            if (victim.isHandActive() && !victim.activeItemStack.isEmpty()) {
                ItemStack item = victim.activeItemStack;
                if (item.getItem().getUseAction(victim.activeItemStack) == UseAction.BLOCK) {
                    int counterLevel = EnchantmentHelper.getEnchantmentLevel(Registration.COUNTER.get(), item);
                    if (counterLevel==2){
                        Countered2.put(attacker,attacker.ticksExisted);
                    } else if (counterLevel==1) {
                        Countered.put(attacker, attacker.ticksExisted);
                    }



                    int repulsionLevel = EnchantmentHelper.getEnchantmentLevel(Registration.REPULSION.get(), item);
                    Vector3d vector3d1 = event.getSource().getDamageLocation().subtractReverse(victim.getPositionVec()).normalize().mul(-.33*repulsionLevel-.5,0,-.33*repulsionLevel-.5).add(0,.3+.1*repulsionLevel,0);
                    attacker.addVelocity(vector3d1.getX(),vector3d1.getY(),vector3d1.getZ());
                }
            }
        }

        if (Countered2.containsKey(victim)) {
            if (victim.ticksExisted - Countered2.get(victim) <= 20) {
                event.setAmount(event.getAmount()+2);
            } else {
                Countered2.remove(victim);
            }
        } else if (Countered.containsKey(victim)) {
            if (victim.ticksExisted - Countered.get(victim) <= 20) {
                event.setAmount(event.getAmount() + 1);
            } else {
                Countered.remove(victim);
            }
        }

        if (event.getSource().damageType.equals("arrow")){
            Entity arrow = event.getSource().getImmediateSource();
            assert arrow != null;
            if (BowShootListener.volleyArrows.contains((AbstractArrowEntity) arrow)){
                event.getEntityLiving().arrowHitTimer = 0;
                event.getEntityLiving().hurtTime = 0;
                event.getEntityLiving().hurtResistantTime = 0;
            }
        } else if(event.getSource().damageType.equals("fall")) {
            for (ItemStack item: event.getEntityLiving().getArmorInventoryList()) {
                Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(item);
                for (Enchantment e : enchants.keySet()) {
                    if (e instanceof HeavyCurse){
                        event.setAmount(event.getAmount() + originalDamage/2);
                    }
                }
            }
        }

        if (victim.isHandActive() && !victim.activeItemStack.isEmpty()) {
            ItemStack item = victim.activeItemStack;
            if (item.getItem().getUseAction(victim.activeItemStack) == UseAction.BLOCK) {
                int perfectLevel = EnchantmentHelper.getEnchantmentLevel(Registration.PERFECT.get(), item);
                if (perfectLevel == 1) {
                    if (((PlayerEntity) victim).getItemInUseCount() >= 71994) {
                        if (attacker instanceof LivingEntity) {
                            victim.getEntityWorld().playSound(null,victim.getPosX(),victim.getPosY(),victim.getPosZ(),SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS,.5F,.8F);
                            ((LivingEntity) attacker).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 30, 4));
                            ((LivingEntity) attacker).addPotionEffect(new EffectInstance(Effects.WEAKNESS, 30, 1));
                            event.setCanceled(true);
                            event.setAmount(0);
                        }
                    }
                }
            }
        }
    }
}
