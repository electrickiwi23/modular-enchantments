package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.GloryEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class EquipmentChangeEvent {

    @SubscribeEvent
    public static void arrowShot(LivingEquipmentChangeEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            if (event.getSlot().getSlotType()== EquipmentSlotType.Group.ARMOR) {
                if (EnchantmentHelper.getEnchantmentLevel(Registration.GLORY.get(), player.getHeldItemMainhand()) > 0) {
                    GloryEnchantment.SetGloryModifiers(player);
                }
            } else if (event.getSlot()==EquipmentSlotType.MAINHAND){
                if (EnchantmentHelper.getEnchantmentLevel(Registration.GLORY.get(), player.getHeldItemMainhand()) > 0) {
                    GloryEnchantment.SetGloryModifiers(player);
                } else  {
                    GloryEnchantment.RemoveGloryModifiers(player);
                }
            }
        }
    }
}
