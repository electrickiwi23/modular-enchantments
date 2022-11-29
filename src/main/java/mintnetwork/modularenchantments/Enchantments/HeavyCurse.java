package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class HeavyCurse extends Enchantment {

    public HeavyCurse() {

        super(Rarity.VERY_RARE, EnchantmentCategory.WEARABLE, new EquipmentSlot[] {EquipmentSlot.HEAD,EquipmentSlot.CHEST,EquipmentSlot.LEGS,EquipmentSlot.FEET});

    }

    public int getMinCost(int enchantmentLevel) {
        return 15;
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

    public boolean isTreasureOnly() {
        return true;
    }

    public boolean isCurse() {
        return true;
    }
}

