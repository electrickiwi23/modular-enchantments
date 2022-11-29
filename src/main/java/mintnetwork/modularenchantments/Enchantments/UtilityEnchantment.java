package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DiggingEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class UtilityEnchantment extends Enchantment {


    public UtilityEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel) {
        return 1 + 10 * (enchantmentLevel - 1);
    }

    public int getMaxCost(int enchantmentLevel) {
        return super.getMinCost(enchantmentLevel) + 50;
    }

    public int getMaxLevel() {
        return 5;
    }

    public boolean checkCompatibility(Enchantment ench) {
        return !(ench instanceof DiggingEnchantment) &&  super.checkCompatibility(ench);
    }

}
