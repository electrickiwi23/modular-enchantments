package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class ExperienceEnchantment extends Enchantment {
    public ExperienceEnchantment(){
        super(Rarity.RARE, EnchantmentType.DIGGER, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel){ return 10 + 8 * (enchantmentLevel - 1); }

    public int getMaxEnchantability(int enchantmentLevel){ return super.getMinEnchantability(enchantmentLevel) + 30; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;  }

    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof SwordItem || super.canApply(stack);
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
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof EfficiencyEnchantment) &&  super.canApplyTogether(ench);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof SwordItem || super.canApplyAtEnchantingTable(stack);
    }
}

