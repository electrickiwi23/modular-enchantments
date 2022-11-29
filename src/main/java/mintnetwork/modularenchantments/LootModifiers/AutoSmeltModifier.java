package mintnetwork.modularenchantments.LootModifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Supplier;

public class AutoSmeltModifier extends LootModifier {
    public AutoSmeltModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    public static final Supplier<Codec<AutoSmeltModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create
            (inst -> codecStart(inst).apply(inst, AutoSmeltModifier::new)));
    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
       ItemStack tool = context.getParam(LootContextParams.TOOL);
        if (tool.getEnchantmentLevel(Registration.SMELTING.get()) > 0) {
            ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<>();
            for (ItemStack item:generatedLoot) {
                Optional<BlastingRecipe> recipe = context.getLevel().getRecipeManager().getRecipeFor(RecipeType.BLASTING,new SimpleContainer(item),context.getLevel());
                if (recipe.isPresent()){
                    ItemStack newItem = recipe.get().getResultItem();
                    newItem.setCount(item.getCount());
                    newLoot.add(newItem);
                } else{
                    newLoot.add(item);

                }
            }
            return newLoot;
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }


}


