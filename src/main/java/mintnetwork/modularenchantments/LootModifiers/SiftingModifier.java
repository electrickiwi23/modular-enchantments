package mintnetwork.modularenchantments.LootModifiers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SiftingModifier extends LootModifier {
    private final HashMap<Block, Item> siftables;
    public SiftingModifier(ILootCondition[] conditionsIn,HashMap<Block, Item> siftables) {
        super(conditionsIn);
        this.siftables = siftables;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if (context.get(LootParameters.BLOCK_STATE) != null && siftables.containsKey(context.get(LootParameters.BLOCK_STATE).getBlock())) {
            int level = EnchantmentHelper.getEnchantmentLevel(Registration.SIFTING.get(),context.get(LootParameters.TOOL));
           if (Math.random()<.04 + level * .02) {
               generatedLoot = Collections.singletonList(new ItemStack(siftables.get(context.get(LootParameters.BLOCK_STATE).getBlock())));
           }
        }
       return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SiftingModifier> {

        @Override
        public SiftingModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            HashMap<Block, Item> siftables = new HashMap<>();
            for (JsonElement element:JSONUtils.getJsonArray(object,"siftblocks")) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(JSONUtils.getString((JsonObject) element,"block")));
                Item nugget = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getString((JsonObject) element,"nugget")));

                siftables.put(block,nugget);
            }


            return new SiftingModifier(conditionsIn,siftables);
        }

        @Override
        public JsonObject write (SiftingModifier instance) {
            return makeConditions(instance.conditions);

        }


    }

}


