package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.PowerEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class RecoveryEnchantment extends Enchantment {
    public RecoveryEnchantment(){
        super(Rarity.UNCOMMON, ModularEnchantments.shield, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 1 + (enchantmentLevel - 1) * 10;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }
    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 5;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof MobilityEnchantment) && super.canApplyTogether(ench) ;
    }

}

