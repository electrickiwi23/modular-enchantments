package mintnetwork.modularenchantments.Events;

import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID, value = Dist.CLIENT)
public class PlayerMoveListener {

    @SubscribeEvent
    public static void sinkPlayer(MovementInputUpdateEvent event) {
        Player player = event.getEntity();
        if (player.isUsingItem()) {
            int level = player.getUseItem().getEnchantmentLevel(Registration.MOBILITY.get());
            event.getInput().forwardImpulse *= 1 + level * .8;
            event.getInput().leftImpulse *= 1 + level * .8;

        }
    }
}
