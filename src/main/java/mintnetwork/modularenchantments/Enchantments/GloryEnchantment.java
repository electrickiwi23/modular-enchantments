package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.Arrays;

public class GloryEnchantment extends Enchantment {
    public GloryEnchantment(){
        super(Rarity.VERY_RARE, ModularEnchantments.axe, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel){ return 20; }

    public int getMaxCost(int enchantmentLevel){ return 50; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;  }

    public static void RemoveGloryModifiers(Player player){
        for (AttributeModifier modifier:player.getAttribute(Attributes.ATTACK_DAMAGE).getModifiers()) {
            if (modifier.getName().equals("GloryDamageModifier")){
                player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(modifier);
            }
        }

        for (AttributeModifier modifier:player.getAttribute(Attributes.ATTACK_SPEED).getModifiers()) {
            if (modifier.getName().equals("GlorySpeedModifier")){
                player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(modifier);
            }
        }
    }

    public static void SetGloryModifiers(Player player){

        RemoveGloryModifiers(player);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(new AttributeModifier("GloryDamageModifier",CalcGloryDamage(player)*1.25, AttributeModifier.Operation.ADDITION));
        player.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(new AttributeModifier("GlorySpeedModifier",CalcGloryDamage(player)*.1, AttributeModifier.Operation.ADDITION));

    }

    public static float CalcGloryDamage(Player player){
        int validSlots = 0;

        for (int i = 0; i < 4; i++) {
            ItemStack armor = player.getInventory().armor.get(i);
            ArrayList<AttributeModifier> modifiers = new ArrayList<>(armor.getAttributeModifiers((EquipmentSlot) Arrays.stream(EquipmentSlot.values()).toArray()[i+2]).get(Attributes.ARMOR));
            int maxArmor = 0;
            switch (i) {
                case 0:
                case 3:
                    maxArmor = 1;
                    break;
                case 1:
                    maxArmor = 2;
                    break;
                case 2:
                    maxArmor = 3;
                    break;
            }
            if ((armor== ItemStack.EMPTY)||(!modifiers.isEmpty()&&modifiers.get(0).getAmount()<=maxArmor)) validSlots++;
        }
        return validSlots;
    }


    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return !(ench instanceof DamageEnchantment) &&
         super.checkCompatibility(ench);
    }

}

