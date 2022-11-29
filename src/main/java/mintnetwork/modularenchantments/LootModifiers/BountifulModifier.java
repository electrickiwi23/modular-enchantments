package mintnetwork.modularenchantments.LootModifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class BountifulModifier extends LootModifier {

    public BountifulModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    public static final Supplier<Codec<BountifulModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create
            (inst -> codecStart(inst).apply(inst, BountifulModifier::new)));


    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ItemStack tool = context.getParam(LootContextParams.TOOL);
        if (tool!=null) {
           if (context.getParam(LootContextParams.BLOCK_STATE).getBlock() instanceof CropBlock) {
               if (((CropBlock) context.getParam(LootContextParams.BLOCK_STATE).getBlock()).isMaxAge(context.getParam(LootContextParams.BLOCK_STATE))) {
                   ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<>();
                   for (ItemStack item : generatedLoot) {
                       if (Math.random() <  tool.getEnchantmentLevel(Registration.BOUNTIFUL.get()) * .2) {
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
    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}


