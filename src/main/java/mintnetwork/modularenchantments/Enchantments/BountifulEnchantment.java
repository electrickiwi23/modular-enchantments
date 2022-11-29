package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.LootBonusEnchantment;

public class BountifulEnchantment extends LootBonusEnchantment {
    public BountifulEnchantment(){
        super(Rarity.RARE, ModularEnchantments.hoe , EquipmentSlot.MAINHAND);
    }
}

