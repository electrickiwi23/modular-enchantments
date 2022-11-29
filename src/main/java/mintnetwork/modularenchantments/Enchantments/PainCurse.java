package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.Random;

public class PainCurse extends Enchantment {
    public PainCurse() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

    }

    public int getMinCost(int enchantmentLevel) {
        return 25;
    }

    public int getMaxCost(int enchantmentLevel){return 50; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }

    public boolean isTreasureOnly() {
        return true;
    }

    public boolean isCurse() {
        return true;
    }

    public boolean canEnchant(ItemStack stack) {
        return !(stack.getItem() instanceof ArmorItem) && super.canEnchant(stack);
    }

    public static int getRandomDamage(ItemStack item, Random rand){
        if (item.getItem() instanceof ArmorItem&& rand.nextFloat() < 0.5F){
            return 1;

        } else if (rand.nextFloat() < 0.5F){
            return 1;
        }
        return 0;
    }
}
