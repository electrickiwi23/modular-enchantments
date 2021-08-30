package mintnetwork.modularenchantments.Listeners;


import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class EntityDamageListener {

    @SubscribeEvent
    public static void onUpdate(LivingDamageEvent event) {


    }
}
