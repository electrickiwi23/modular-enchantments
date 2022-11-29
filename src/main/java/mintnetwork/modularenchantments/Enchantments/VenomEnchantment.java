package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class VenomEnchantment extends Enchantment {

    public VenomEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});


    }


    public int getMinCost(int enchantmentLevel) { return 10 + 15 * (enchantmentLevel - 1); }

    public int getMaxCost(int enchantmentLevel) {
        return super.getMinCost(enchantmentLevel) + 30;
    }

    public int getMaxLevel()
    {
        return 2;
    }

    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.POISON, 120, level - 1));

        }
    }

    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof AxeItem || super.canEnchant(stack);
    }

    public boolean checkCompatibility(Enchantment ench){
        return !(ench instanceof DamageEnchantment)& super.checkCompatibility(ench);
    }
}

