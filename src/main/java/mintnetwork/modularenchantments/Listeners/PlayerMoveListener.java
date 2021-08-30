package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID, value = Dist.CLIENT)
public class PlayerMoveListener {

    @SubscribeEvent
    public static void sinkPlayer(InputUpdateEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player.isHandActive()) {
            int level = EnchantmentHelper.getEnchantmentLevel(Registration.MOBILITY.get(), player.activeItemStack);
            event.getMovementInput().moveForward *= 1 + level;
            event.getMovementInput().moveStrafe *= 1 + level;
        }
    }
}
