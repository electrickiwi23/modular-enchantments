package mintnetwork.modularenchantments.LootModifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Supplier;

public class SiftingModifier extends LootModifier {

    public SiftingModifier(LootItemCondition[] conditionsIn,Map<Block, Item> siftables) {
        super(conditionsIn);
        this.siftables = siftables;
    }
    public static final Supplier<Codec<SiftingModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create
            (inst -> codecStart(inst).and(
                    Codec.unboundedMap(ForgeRegistries.BLOCKS.getCodec(),ForgeRegistries.ITEMS.getCodec()).fieldOf("siftblocks").forGetter(m -> m.siftables)
            ).apply(inst, SiftingModifier::new)));

    private final Map<Block, Item> siftables;
    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (siftables.containsKey(context.getParam(LootContextParams.BLOCK_STATE).getBlock())) {
            int level = context.getParam(LootContextParams.TOOL).getEnchantmentLevel(Registration.SIFTING.get());
           if (Math.random()<.04 + level * .02) {
               generatedLoot = ObjectArrayList.of(new ItemStack(siftables.get(context.getParam(LootContextParams.BLOCK_STATE).getBlock())));
           }
        }
       return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

}


