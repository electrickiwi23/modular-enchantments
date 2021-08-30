package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.enchantment.BindingCurseEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.PowerEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class HeavyCurse extends Enchantment {

    public HeavyCurse() {

        super(Rarity.VERY_RARE, EnchantmentType.WEARABLE, new EquipmentSlotType[] {EquipmentSlotType.HEAD,EquipmentSlotType.CHEST,EquipmentSlotType.LEGS,EquipmentSlotType.FEET});

    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 25;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }

    public boolean isTreasureEnchantment() {
        return true;
    }

    public boolean isCurse() {
        return true;
    }
}

