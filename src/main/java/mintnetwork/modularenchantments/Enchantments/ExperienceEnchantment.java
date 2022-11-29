package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.LootBonusEnchantment;

public class ExperienceEnchantment extends Enchantment {
    public ExperienceEnchantment(){
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel){ return 10 + 8 * (enchantmentLevel - 1); }

    public int getMaxCost(int enchantmentLevel){ return super.getMinCost(enchantmentLevel) + 30; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;  }

    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof SwordItem || super.canEnchant(stack);
    }

    public static int calculateExp(int startingExp,int level){
        double amount =  startingExp * (1 + level * .1);
        if (Math.random() < amount%1) {
            amount = Math.ceil(amount);
        }
        return (int) amount;

    }


    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return !(ench instanceof LootBonusEnchantment) &&  super.checkCompatibility(ench);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof SwordItem || super.canApplyAtEnchantingTable(stack);
    }
}

