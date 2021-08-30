package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.enchantment.*;
import net.minecraft.inventory.EquipmentSlotType;

public class VampirismEnchantment extends Enchantment {

    public VampirismEnchantment() {

        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});

    }


    public int getMinEnchantability(int enchantmentLevel) { return 10 + 15 * (enchantmentLevel - 1); }

    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 30;
    }

    public int getMaxLevel()
    {
        return 2;
    }

    public boolean canApplyTogether(Enchantment ench){
        return !(ench instanceof FireAspectEnchantment)& super.canApplyTogether(ench);
    }
}


