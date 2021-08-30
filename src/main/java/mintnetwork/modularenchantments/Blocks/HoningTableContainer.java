package mintnetwork.modularenchantments.Blocks;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class HoningTableContainer extends Container {
    public static HoningTableContainer getClientContainer(int id, PlayerInventory playerInventory){
        return new HoningTableContainer(id,playerInventory);
    }

    IInventory input = new Inventory(1);

    public HoningTableContainer(int id, PlayerInventory playerInventory){
        super(Registration.HONINGTABLECONTAINER.get(),id);

        this.addSlot(new Slot(input,0,20,20){

            public boolean isItemValid(ItemStack stack) {
                return stack.isDamageable() || stack.getItem() == Items.ENCHANTED_BOOK || stack.isEnchanted();
            }
        }
        );

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }




    }

//    public void onCraftMatrixChanged(IInventory inventoryIn) {
//        super.onCraftMatrixChanged(inventoryIn);
//        if (inventoryIn == this.input) {
//            this.updateRecipeOutput();
//        }




//    }

    private void updateRecipeOutput() {

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
