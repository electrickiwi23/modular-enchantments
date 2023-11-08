package mintnetwork.modularenchantments.Events;

import mintnetwork.modularenchantments.Enchantments.GloryEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class EquipmentChangeEvent {

    @SubscribeEvent
    public static void arrowShot(LivingEquipmentChangeEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player){
            Player player = (Player) entity;
            if (event.getSlot().getType()== EquipmentSlot.Type.ARMOR) {
                if (player.getMainHandItem().getEnchantmentLevel(Registration.GLORY.get()) > 0) {
                    GloryEnchantment.SetGloryModifiers(player);
                }
            } else if (event.getSlot()==EquipmentSlot.MAINHAND){
                if (player.getMainHandItem().getEnchantmentLevel(Registration.GLORY.get()) > 0) {
                    GloryEnchantment.SetGloryModifiers(player);
                } else  {
                    GloryEnchantment.RemoveGloryModifiers(player);
                }
            }
        }
    }
}
