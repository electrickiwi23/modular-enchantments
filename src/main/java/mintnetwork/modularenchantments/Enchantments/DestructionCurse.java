package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class DestructionCurse extends Enchantment {

    public DestructionCurse() {

        super(Rarity.VERY_RARE, EnchantmentType.DIGGER, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});

    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 25;
    }

    public int getMaxEnchantability(int enchantmentLevel){return 50; }

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

