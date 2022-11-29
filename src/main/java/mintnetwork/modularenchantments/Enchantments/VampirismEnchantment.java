package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FireAspectEnchantment;

public class VampirismEnchantment extends Enchantment {

    public VampirismEnchantment() {

        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

    }


    public int getMinCost(int enchantmentLevel) { return 10 + 15 * (enchantmentLevel - 1); }

    public int getMaxCost(int enchantmentLevel) {
        return super.getMinCost(enchantmentLevel) + 30;
    }

    public int getMaxLevel()
    {
        return 2;
    }

    public boolean checkCompatibility(Enchantment ench){
        return !(ench instanceof FireAspectEnchantment)& super.checkCompatibility(ench);
    }
}



