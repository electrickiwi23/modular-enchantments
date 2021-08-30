package mintnetwork.modularenchantments.LootModifiers;

import com.google.gson.JsonObject;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.block.CropsBlock;
import net.minecraft.enchantment.EnchantmentHelper;
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

public class AutoSmeltModifier extends LootModifier {
    public AutoSmeltModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
       ItemStack tool = context.get(LootParameters.TOOL);
       if (tool!=null) {
           if (EnchantmentHelper.getEnchantmentLevel(Registration.SMELTING.get(), tool) > 0) {
               List<ItemStack> newLoot = new ArrayList<>();
               for (ItemStack item:generatedLoot) {
                   Optional<BlastingRecipe> recipe = context.getWorld().getRecipeManager().getRecipe(IRecipeType.BLASTING,new Inventory(item),context.getWorld());
                   if (recipe.isPresent()){
                       newLoot.add(recipe.get().getRecipeOutput());
                   } else{
                       newLoot.add(item);

                   }
               }
               return newLoot;
           }
       }
       return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<AutoSmeltModifier> {

        @Override
        public AutoSmeltModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            return new AutoSmeltModifier(conditionsIn);
        }

        @Override
        public JsonObject write(AutoSmeltModifier instance) {
            return makeConditions(instance.conditions);

        }


    }

}


