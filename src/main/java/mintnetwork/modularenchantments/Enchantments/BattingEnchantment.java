package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.*;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BattingEnchantment extends Enchantment {
    public BattingEnchantment(){
        super(Rarity.RARE, ModularEnchantments.shovel, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel){ return 10 + 20 * (enchantmentLevel - 1); }

    public int getMaxEnchantability(int enchantmentLevel){ return super.getMinEnchantability(enchantmentLevel) + 30; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 2;  }

    @Override
    public float calcDamageByCreature(int level, CreatureAttribute creatureType) {
        return level;
    }


    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof EfficiencyEnchantment) &&  super.canApplyTogether(ench);
    }

}

