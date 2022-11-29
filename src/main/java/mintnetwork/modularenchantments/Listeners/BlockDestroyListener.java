package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.DestructionCurse;
import mintnetwork.modularenchantments.Enchantments.ExperienceEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class BlockDestroyListener {

    public static List<BlockPos> explode = new ArrayList<>();



    @SubscribeEvent
    public static void arrowShot(final BlockEvent.BreakEvent event) {
        LivingEntity entity = event.getPlayer();
        Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(entity.getMainHandItem());
        for (Enchantment e : enchants.keySet()) {
            if (e instanceof DestructionCurse) {
                BlockPos pos = event.getPos();
                explode.add(pos);
            }
            if (e instanceof ExperienceEnchantment){
                event.setExpToDrop(ExperienceEnchantment.calculateExp(event.getExpToDrop(), enchants.get(e)));
            }
        }
    }

}
