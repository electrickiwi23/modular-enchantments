package mintnetwork.modularenchantments.Events;

import mintnetwork.modularenchantments.Enchantments.HeavyCurse;
import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfig;
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
        LivingEntity victim = event.getEntity();
        Entity attacker = event.getSource().getDirectEntity();


        ItemStack item = victim.getUseItem();
        if (event.getEntity().isDamageSourceBlocked(event.getSource())){

            if ( victim.isBlocking()) {
                    int counterLevel = item.getEnchantmentLevel(Registration.COUNTER.get());
                    if (counterLevel==2){
                        Countered2.put(attacker,attacker.tickCount);
                    } else if (counterLevel==1) {
                        Countered.put(attacker, attacker.tickCount);
                    }



                    int repulsionLevel = item.getEnchantmentLevel(Registration.REPULSION.get());
                    Vec3 vector3d1 = victim.position().subtract(event.getSource().getSourcePosition()).normalize().multiply(-.33*repulsionLevel-.5,0,-.33*repulsionLevel-.5).add(0,.3+.1*repulsionLevel,0);
                    attacker.setDeltaMovement(vector3d1);
                }
            }else if(victim.isUsingItem()&&victim.getUseItem().getItem() instanceof ShieldItem) {
            int perfectLevel = item.getEnchantmentLevel(Registration.PERFECT.get());
            if (perfectLevel == 1) {
                if (attacker instanceof LivingEntity) {
                    victim.getLevel().playSound(null, victim.getX(), victim.getY(), victim.getZ(), SoundEvents.ANVIL_PLACE, SoundSource.PLAYERS, .5F, .8F);
                    ((LivingEntity) attacker).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 4));
                    ((LivingEntity) attacker).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30, 1));
                    event.setCanceled(true);
                    event.setAmount(0);
                }
            }
        }


        if (Countered2.containsKey(victim)) {
            if (victim.tickCount - Countered2.get(victim) <= 20) {
                event.setAmount(event.getAmount()+2);
                Countered2.remove(victim);
            } else {
                Countered2.remove(victim);
            }
        } else if (Countered.containsKey(victim)) {
            if (victim.tickCount - Countered.get(victim) <= 20) {
                event.setAmount(event.getAmount() + 1);
                Countered.remove(victim);
            } else {
                Countered.remove(victim);
            }
        }

        if (event.getSource().getDirectEntity() instanceof AbstractArrow){
            Entity arrow = event.getSource().getDirectEntity();
            assert arrow != null;
            if (arrow.getTags().contains("volley_arrow")){
                event.getEntity().invulnerableTime = 0;

            }
        } else if(event.getSource().getMsgId().equals("fall")) {
            for (ItemStack armoritem: event.getEntity().getArmorSlots()) {
                Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(armoritem);
                for (Enchantment e : enchants.keySet()) {
                    if (e instanceof HeavyCurse){
                        event.setAmount(event.getAmount() + originalDamage/2);
                    }
                }
            }
        }


    }
}
