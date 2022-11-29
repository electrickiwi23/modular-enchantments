package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FishingSpeedEnchantment;
import org.jetbrains.annotations.NotNull;

public class BarbedEnchantment extends Enchantment {
    public BarbedEnchantment(){
        super(Rarity.UNCOMMON, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[] {EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND});
    }
    public int getMinCost(int enchantmentLevel){ return 1 + (enchantmentLevel - 1) * 10; }

    public int getMaxCost(int enchantmentLevel){ return super.getMinCost(enchantmentLevel) + 30; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;  }



    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(@NotNull Enchantment ench) {
        return !(ench instanceof FishingSpeedEnchantment) &&  super.checkCompatibility(ench);
    }

}

