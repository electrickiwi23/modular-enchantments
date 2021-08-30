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

public class RicochetEnchantment extends Enchantment {

    public RicochetEnchantment() {

        super(Rarity.RARE, EnchantmentType.BOW, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});

    }


    public int getMinEnchantability(int enchantmentLevel){return 1 + (enchantmentLevel) * 10; }

    public int getMaxEnchantability(int enchantmentLevel){ return this.getMinEnchantability(enchantmentLevel) + 15; }

    public int getMaxLevel()
    {
        return 3;
    }

    public boolean canApplyTogether(Enchantment ench){
        return !(ench instanceof VolleyEnchantment) && super.canApplyTogether(ench);
    }
}

