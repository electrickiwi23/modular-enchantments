package mintnetwork.modularenchantments.Events;

import mintnetwork.modularenchantments.Enchantments.PunctureingEnchantment;
import mintnetwork.modularenchantments.Enchantments.VampirismEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class EntityAttackListener {

    @SubscribeEvent
    public static void livingAttack(LivingAttackEvent event) {
        Entity entity = event.getSource().getDirectEntity();
        if (entity != null) {
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(living.getMainHandItem());
                for (Enchantment e : enchants.keySet()) {
                    if (e instanceof VampirismEnchantment) {
                        living.heal(Math.min(event.getAmount(),event.getEntity().getHealth())  / 10.0F * enchants.get(e));
                    }
                    if (e instanceof PunctureingEnchantment){
                        event.getSource().bypassArmor();
                    }
                }
            }
        }
    }
}
