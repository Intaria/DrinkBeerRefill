package lekavar.lma.drinkbeer.compat.jade.provider;

import lekavar.lma.drinkbeer.blockentities.MixedBeerBlockEntity;
import lekavar.lma.drinkbeer.blocks.MixedBeerBlock;
import lekavar.lma.drinkbeer.items.MixedBeerBlockItem;
import lekavar.lma.drinkbeer.registries.ItemRegistry;
import lekavar.lma.drinkbeer.utils.beer.Beers;
import mcp.mobius.waila.addons.core.CorePlugin;
import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import mcp.mobius.waila.api.config.WailaConfig;
import mcp.mobius.waila.api.ui.IElement;
import mcp.mobius.waila.impl.ui.ItemStackElement;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import org.jetbrains.annotations.Nullable;

public class MixedBeerHeadComponentProvider implements IComponentProvider {
    public static MixedBeerHeadComponentProvider INSTANCE = new MixedBeerHeadComponentProvider();

    @Override
    public @Nullable IElement getIcon(BlockAccessor accessor, IPluginConfig config, IElement currentIcon) {
        if (accessor.getBlockEntity() instanceof MixedBeerBlockEntity mixedBeer)
        {
            return ItemStackElement.of(Beers.byId(mixedBeer.getBeerId()).getBeerItem().getDefaultInstance());
        }
        return IComponentProvider.super.getIcon(accessor, config, currentIcon);
    }

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if(!(accessor.getBlock() instanceof MixedBeerBlock &&
                accessor.getBlockEntity() instanceof MixedBeerBlockEntity mixedBeer))
            return;

        MixedBeerBlockItem MIXED_BEER = (MixedBeerBlockItem) ItemRegistry.MIXED_BEER.get();
        Component name = MIXED_BEER.getMixedBeerName(mixedBeer.getPickStack());
        if (name != null) {
            WailaConfig wailaConfig = config.getWailaConfig();
            tooltip.clear();
            tooltip.add((new TextComponent(String.format(wailaConfig.getFormatting().getBlockName(), name.getString()))).withStyle(wailaConfig.getOverlay().getColor().getTitle()), CorePlugin.TAG_OBJECT_NAME);
        }
    }
}