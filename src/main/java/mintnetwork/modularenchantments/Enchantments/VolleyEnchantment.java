package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.Listeners.BowShootListener;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Random;

public class VolleyEnchantment extends Enchantment {

    public VolleyEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.BOW, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});

    }


    public int getMinEnchantability(int enchantmentLevel){return (enchantmentLevel) * 10; }

    public int getMaxEnchantability(int enchantmentLevel){ return this.getMinEnchantability(enchantmentLevel) + 20; }

    public int getMaxLevel()
    {
        return 3;
    }

    public boolean canApplyTogether(Enchantment ench){
        return !(ench instanceof PowerEnchantment) && super.canApplyTogether(ench);
    }
}

