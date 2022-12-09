package lekavar.lma.drinkbeer.blockentities;

import lekavar.lma.drinkbeer.blocks.BartendingTableBlock;
import lekavar.lma.drinkbeer.items.BeerMugItem;
import lekavar.lma.drinkbeer.items.MixedBeerBlockItem;
import lekavar.lma.drinkbeer.items.SpiceBlockItem;
import lekavar.lma.drinkbeer.managers.MixedBeerManager;
import lekavar.lma.drinkbeer.registries.BlockEntityRegistry;
import lekavar.lma.drinkbeer.utils.beer.Beers;
import lekavar.lma.drinkbeer.utils.mixedbeer.Spices;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BartendingTableBlockEntity extends BlockEntity {
    private final SimpleContainer inv = new OneItemContainer(2);
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> new BartendingTableInvWrapper(inv,this));
    private List<Integer> spiceList = new ArrayList<>();

    public BartendingTableBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.BARTENDING_TABLE_TILEENTITY.get(),pos,state);
    }

    public boolean placeBeer(ItemStack itemStack){
        if(!inv.isEmpty())
            return false;
        Item beerItem = itemStack.getItem();
        if(!(beerItem instanceof MixedBeerBlockItem) && !(beerItem instanceof BeerMugItem))
            return false;
        var spiceList = MixedBeerManager.getSpiceList(itemStack);
        if(spiceList.size()>=3)
            return false;
        inv.setItem(0, itemStack);
        inv.setChanged();
        setChanged();
        return true;
    }

    public boolean putSpice(ItemStack itemStack){
        if(!(itemStack.getItem() instanceof SpiceBlockItem))
            return false;
        if(inv.isEmpty())
            return false;
        if(!inv.getItem(1).isEmpty()){
            var spiceList = MixedBeerManager.getSpiceList(inv.getItem(1));
            if(spiceList.size()>=3)
                return false;
        }
        ItemStack beerItem = inv.getItem(0);
        if(beerItem.isEmpty())
            beerItem = inv.getItem(1);
        var beerId = Beers.byItem(beerItem.getItem()).getId();
        var spiceList = MixedBeerManager.getSpiceList(beerItem);
        spiceList.add(Spices.byItem(itemStack.getItem()).getId());
        ItemStack flavoredBeer = MixedBeerManager.genMixedBeerItemStack(beerId, spiceList);
        inv.setItem(0, ItemStack.EMPTY);
        inv.setItem(1, flavoredBeer);
        inv.setChanged();
        setChanged();
        return true;
    }

    public ItemStack takeBeer(boolean simulate){
        var ret = inv.getItem(0);
        if(ret.isEmpty())
            ret = inv.getItem(1);
        if(!simulate){
            inv.clearContent();
            inv.setChanged();
            setChanged();
        }
        return ret.copy();
    }


    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.put("inv",inv.createTag());
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        inv.fromTag((ListTag) tag.get("inv"));
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inv",inv.createTag());
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);
        inv.fromTag((ListTag) tag.get("inv"));
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        handler.invalidate();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return handler.cast();
        return super.getCapability(cap, side);
    }

    static class OneItemContainer extends SimpleContainer{
        public OneItemContainer(int pSize) {
            super(pSize);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }
    }

    static class BartendingTableInvWrapper extends InvWrapper{
        Container inv;
        BartendingTableBlockEntity be;
        public BartendingTableInvWrapper(Container inv, BartendingTableBlockEntity be) {
            super(inv);
            this.inv = inv;
            this.be = be;
        }

        @Override
        public @NotNull ItemStack getStackInSlot(int slot) {
            // if(slot==0) return ItemStack.EMPTY;
            return super.getStackInSlot(slot);
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            if(slot==1) return stack;
            if(stack.getItem() instanceof MixedBeerBlockItem || stack.getItem()  instanceof BeerMugItem){
                if(!inv.isEmpty())
                    return stack;
                var spiceList = MixedBeerManager.getSpiceList(stack);
                if(spiceList.size()>=3)
                    return stack;
                if(!simulate){
                    var ret = stack.copy();
                    be.placeBeer(stack);
                    ret.shrink(1);
                    return ret;
                }
            } else if(stack.getItem() instanceof SpiceBlockItem) {
                if(inv.isEmpty())
                    return stack;
                if(!inv.getItem(1).isEmpty()){
                    var spiceList = MixedBeerManager.getSpiceList(inv.getItem(1));
                    if(spiceList.size()>=3)
                        return stack;
                }
                if(!simulate){
                    var ret = stack.copy();
                    be.putSpice(stack);
                    ret.shrink(1);
                    return ret;
                }
            }
            return stack;
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            if(slot==0) return ItemStack.EMPTY;
            return super.extractItem(slot, amount, simulate);
        }

        @Override
        public void setStackInSlot(int slot, @NotNull ItemStack stack) {
            super.setStackInSlot(slot, stack);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if(slot==0){
                if(stack.getItem() instanceof MixedBeerBlockItem || stack.getItem()  instanceof BeerMugItem){
                    if(!inv.isEmpty())
                        return false;
                    var spiceList = MixedBeerManager.getSpiceList(stack);
                    if(spiceList.size()>=3)
                        return false;
                } else if(stack.getItem() instanceof SpiceBlockItem) {
                    if(inv.isEmpty())
                        return false;
                    if(!inv.getItem(1).isEmpty()){
                        var spiceList = MixedBeerManager.getSpiceList(inv.getItem(1));
                        if(spiceList.size()>=3)
                            return false;
                    }
                }
                return false;
            }
            return false;
        }
    }

}
