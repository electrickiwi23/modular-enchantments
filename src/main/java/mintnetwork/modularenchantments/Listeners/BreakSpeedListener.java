package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.client.gui.screens.inventory.EnchantmentNames;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class BreakSpeedListener {

    @SubscribeEvent
    public static void BlockBroken(PlayerEvent.BreakSpeed event) {
        float speed = event.getOriginalSpeed();
        Player player = event.getEntity();

        if (!player.isOnGround() && EnchantmentHelper.getEnchantmentLevel(Registration.AIRAFFINITY.get(),player)>0){
            speed *= 5f;
        }

        event.setNewSpeed(speed);

    }
}
