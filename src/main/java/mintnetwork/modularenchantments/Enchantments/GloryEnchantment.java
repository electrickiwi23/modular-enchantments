package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.world.NoteBlockEvent;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class GloryEnchantment extends Enchantment {
    public GloryEnchantment(){
        super(Rarity.VERY_RARE, ModularEnchantments.axe, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel){ return 20; }

    public int getMaxEnchantability(int enchantmentLevel){ return 50; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;  }

    public static void RemoveGloryModifiers(PlayerEntity player){
        for (AttributeModifier modifier:player.getAttribute(Attributes.ATTACK_DAMAGE).getModifierListCopy()) {
            if (modifier.getName().equals("GloryDamageModifier")){
                player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(modifier);
            }
        }

        for (AttributeModifier modifier:player.getAttribute(Attributes.ATTACK_SPEED).getModifierListCopy()) {
            if (modifier.getName().equals("GlorySpeedModifier")){
                player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(modifier);
            }
        }
    }

    public static void SetGloryModifiers(PlayerEntity player){

        RemoveGloryModifiers(player);
        player.getAttribute(Attributes.ATTACK_DAMAGE).applyPersistentModifier(new AttributeModifier("GloryDamageModifier",CalcGloryDamage(player)*1.25, AttributeModifier.Operation.ADDITION));
        player.getAttribute(Attributes.ATTACK_SPEED).applyPersistentModifier(new AttributeModifier("GlorySpeedModifier",CalcGloryDamage(player)*.1, AttributeModifier.Operation.ADDITION));

    }

    public static float CalcGloryDamage(PlayerEntity player){
        int validSlots = 0;

        for (int i = 0; i < 4; i++) {
            ItemStack armor = player.inventory.armorInventory.get(0);
            ArrayList<AttributeModifier> modifiers = new ArrayList<>(armor.getAttributeModifiers((EquipmentSlotType) Arrays.stream(EquipmentSlotType.values()).toArray()[i+2]).get(Attributes.ARMOR));
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
            if ((armor==ItemStack.EMPTY)||(!modifiers.isEmpty()&&modifiers.get(0).getAmount()<=maxArmor)) validSlots++;
        }
        return validSlots;
    }


    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof DamageEnchantment) &&
         super.canApplyTogether(ench);
    }

}

