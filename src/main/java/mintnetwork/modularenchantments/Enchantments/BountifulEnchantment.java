package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.LootBonusEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class BountifulEnchantment extends LootBonusEnchantment {
    public BountifulEnchantment(){
        super(Rarity.RARE, ModularEnchantments.hoe , EquipmentSlotType.MAINHAND);
    }
}

