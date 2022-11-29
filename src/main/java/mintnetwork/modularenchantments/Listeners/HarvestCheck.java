package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class HarvestCheck {


    @SubscribeEvent
    public static void onUpdate(PlayerEvent.HarvestCheck event) {

        ItemStack stack = event.getEntity().getMainHandItem();
        if (stack.getEnchantmentLevel(Registration.UTILITY.get())>0) {
            Item item = stack.getItem();
            if (!(item instanceof DiggerItem)) {
                return;
            }
            boolean tier = TierSortingRegistry.isCorrectTierForDrops(((DiggerItem) item).getTier(), event.getTargetBlock());


            if (tier) {
                event.setCanHarvest(true);
            }
        }
    }
}