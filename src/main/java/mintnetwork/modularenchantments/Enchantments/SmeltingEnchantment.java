package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class SmeltingEnchantment extends Enchantment {
    public SmeltingEnchantment(){
        super(Rarity.VERY_RARE, ModularEnchantments.pickaxe, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel){ return 25; }

    public int getMaxCost(int enchantmentLevel){ return 50; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1; }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench) && ench != Enchantments.BLOCK_FORTUNE && ench != Enchantments.SILK_TOUCH;
    }

}

