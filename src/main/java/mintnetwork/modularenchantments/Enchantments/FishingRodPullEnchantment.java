package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FishingSpeedEnchantment;
import net.minecraft.world.item.enchantment.LootBonusEnchantment;
import org.jetbrains.annotations.NotNull;

public class FishingRodPullEnchantment extends Enchantment {
    public FishingRodPullEnchantment(){
        super(Rarity.RARE, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[] {EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND});
    }

    public int getMinCost(int enchantmentLevel){ return 10 + 15 * (enchantmentLevel - 1); }

    public int getMaxCost(int enchantmentLevel){ return super.getMinCost(enchantmentLevel) + 20; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 2;  }

    @Override
    public float getDamageBonus(int level, MobType creatureType) {
        return level;
    }


    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(@NotNull Enchantment ench) {
        return !(ench instanceof LootBonusEnchantment) &&  super.checkCompatibility(ench);
    }

}

