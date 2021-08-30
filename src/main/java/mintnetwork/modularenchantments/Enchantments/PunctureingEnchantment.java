package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class PunctureingEnchantment extends Enchantment {
    public PunctureingEnchantment(){
        super(Rarity.RARE, ModularEnchantments.pickaxe, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel){ return 15; }

    public int getMaxEnchantability(int enchantmentLevel){ return 50; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1; }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof EfficiencyEnchantment) &&  super.canApplyTogether(ench);
    }

}

