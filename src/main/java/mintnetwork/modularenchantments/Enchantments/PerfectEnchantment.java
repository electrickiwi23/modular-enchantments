package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.Effects;

public class PerfectEnchantment extends Enchantment {
    public PerfectEnchantment(){
        super(Rarity.VERY_RARE, ModularEnchantments.shield, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel){ return 20; }

    public int getMaxEnchantability(int enchantmentLevel){ return 50; }  /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }

    public boolean canApplyTogether(Enchantment ench){
        return !ench.type.equals(ModularEnchantments.shield);
    }

}

