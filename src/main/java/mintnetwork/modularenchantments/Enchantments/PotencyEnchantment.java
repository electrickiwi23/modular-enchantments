package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.ArrowPiercingEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.MultiShotEnchantment;

public class PotencyEnchantment extends Enchantment {
    public PotencyEnchantment(){
        super(Rarity.VERY_RARE, EnchantmentCategory.CROSSBOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});    }

    public int getMinCost(int enchantmentLevel) {
        return 20;
    }

    public int getMaxCost(int enchantmentLevel) {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return !(ench instanceof ArrowPiercingEnchantment) && !(ench instanceof MultiShotEnchantment) && super.checkCompatibility(ench);
    }

}
