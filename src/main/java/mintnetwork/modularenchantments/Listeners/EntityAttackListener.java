package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.FrostAspectEnchantment;
import mintnetwork.modularenchantments.Enchantments.PunctureingEnchantment;
import mintnetwork.modularenchantments.Enchantments.VampirismEnchantment;
import mintnetwork.modularenchantments.Enchantments.VenomEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class EntityAttackListener {

    @SubscribeEvent
    public static void livingAttack(LivingAttackEvent event) {
        LivingEntity victim = event.getEntityLiving();
        Entity entity = event.getSource().getImmediateSource();
        if (entity != null) {
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(living.getHeldItem(Hand.MAIN_HAND));
                for (Enchantment e : enchants.keySet()) {
                    if (e instanceof VampirismEnchantment) {
                        System.out.println(event.getEntityLiving().getHealth());
                        living.heal(Math.min(event.getAmount(),event.getEntityLiving().getHealth())  / 10.0F * enchants.get(e));
                    }
                    if (e instanceof PunctureingEnchantment){
                        event.getSource().setDamageBypassesArmor();
                    }
                }
            }
        }
    }
}
