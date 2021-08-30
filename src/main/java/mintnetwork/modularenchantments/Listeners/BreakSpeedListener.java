package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class BreakSpeedListener {

    @SubscribeEvent
    public static void arrowShot(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        int i = EnchantmentHelper.getMaxEnchantmentLevel(Registration.UTILITY.get(), player);

        boolean effective = false;

        for (ToolType tool:player.inventory.mainInventory.get(player.inventory.currentItem).getToolTypes() ) {
            if (event.getState().isToolEffective(tool)) effective = true;
        }

        if (!effective){
            event.setNewSpeed(event.getOriginalSpeed()+ (i*i+1.0f)/2.0f);

        }
    }
}
