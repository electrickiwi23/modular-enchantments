package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.enchantment.DiggingEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class BattingEnchantment extends Enchantment {
    public BattingEnchantment(){
        super(Rarity.RARE, ModularEnchantments.shovel, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel){ return 10 + 20 * (enchantmentLevel - 1); }

    public int getMaxCost(int enchantmentLevel){ return super.getMinCost(enchantmentLevel) + 30; }

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
        return !(ench instanceof DiggingEnchantment) &&  super.checkCompatibility(ench);
    }

}

