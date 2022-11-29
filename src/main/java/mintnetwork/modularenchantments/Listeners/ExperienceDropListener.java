package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.ExperienceEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class ExperienceDropListener {
    @SubscribeEvent
    public static void onEntityDrop(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() !=null) {
            int experianceEnchantLevel = EnchantmentHelper.getEnchantmentLevel(Registration.EXPERIENCE.get(), event.getAttackingPlayer());
            if (experianceEnchantLevel > 0) {
                event.setDroppedExperience(ExperienceEnchantment.calculateExp(event.getDroppedExperience(), experianceEnchantLevel));
            }
        }


    }
}
