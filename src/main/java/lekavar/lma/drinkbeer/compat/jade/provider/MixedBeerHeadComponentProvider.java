package lekavar.lma.drinkbeer.compat.jade.provider;

import lekavar.lma.drinkbeer.DrinkBeer;
import lekavar.lma.drinkbeer.blockentities.MixedBeerBlockEntity;
import lekavar.lma.drinkbeer.blocks.MixedBeerBlock;
import lekavar.lma.drinkbeer.items.MixedBeerBlockItem;
import lekavar.lma.drinkbeer.registries.ItemRegistry;
import lekavar.lma.drinkbeer.utils.beer.Beers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.config.IWailaConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.impl.ui.ItemStackElement;

import java.awt.*;

public class MixedBeerHeadComponentProvider implements IBlockComponentProvider {
    public static MixedBeerHeadComponentProvider INSTANCE = new MixedBeerHeadComponentProvider();

    @Override
    public @Nullable IElement getIcon(BlockAccessor accessor, IPluginConfig config, IElement currentIcon) {
        if (accessor.getBlockEntity() instanceof MixedBeerBlockEntity mixedBeer)
        {
            return ItemStackElement.of(Beers.byId(mixedBeer.getBeerId()).getBeerItem().getDefaultInstance());
        }
        return IBlockComponentProvider.super.getIcon(accessor, config, currentIcon);
    }

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if(!(accessor.getBlock() instanceof MixedBeerBlock &&
                accessor.getBlockEntity() instanceof MixedBeerBlockEntity mixedBeer))
            return;
        MixedBeerBlockItem MIXED_BEER = (MixedBeerBlockItem) ItemRegistry.MIXED_BEER.get();
        Component name = MIXED_BEER.getMixedBeerName(mixedBeer.getPickStack());
        if (name != null) {
            @SuppressWarnings("all")
            IWailaConfig wailaConfig = config.getWailaConfig();
            tooltip.clear();
            tooltip.add(Component.literal(name.toString()).withStyle(Style.EMPTY.withColor(TextColor.parseColor(wailaConfig.getOverlay().getTheme().backgroundColor))));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(DrinkBeer.MOD_ID,"mixed_beer_head");
    }
}