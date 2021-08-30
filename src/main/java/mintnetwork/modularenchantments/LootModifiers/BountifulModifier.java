package mintnetwork.modularenchantments.LootModifiers;

import com.google.gson.JsonObject;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.block.CropsBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.BlastingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BountifulModifier extends LootModifier {
    public BountifulModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
       ItemStack tool = context.get(LootParameters.TOOL);
       if (tool!=null) {
           if (context.get(LootParameters.BLOCK_STATE)!=null && context.get(LootParameters.BLOCK_STATE).getBlock() instanceof CropsBlock) {
               if (((CropsBlock) context.get(LootParameters.BLOCK_STATE).getBlock()).isMaxAge(context.get(LootParameters.BLOCK_STATE))) {
                   List<ItemStack> newLoot = new ArrayList<>();
                   for (ItemStack item : generatedLoot) {
                       if (Math.random() < EnchantmentHelper.getEnchantmentLevel(Registration.BOUNTIFUL.get(), tool) * .2) {
                           newLoot.add(item.copy());
                       }
                       newLoot.add(item);
                   }
                   return newLoot;
               }
           }
       }
       return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<BountifulModifier> {

        @Override
        public BountifulModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            return new BountifulModifier(conditionsIn);
        }

        @Override
        public JsonObject write(BountifulModifier instance) {
            return makeConditions(instance.conditions);

        }


    }

}


