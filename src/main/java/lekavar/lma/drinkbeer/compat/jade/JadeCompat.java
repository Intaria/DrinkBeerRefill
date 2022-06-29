package lekavar.lma.drinkbeer.compat.jade;

import lekavar.lma.drinkbeer.blockentities.BeerBarrelBlockEntity;
import lekavar.lma.drinkbeer.blockentities.TradeBoxBlockEntity;
import lekavar.lma.drinkbeer.blocks.BeerBarrelBlock;
import lekavar.lma.drinkbeer.blocks.MixedBeerBlock;
import lekavar.lma.drinkbeer.blocks.TradeboxBlock;
import lekavar.lma.drinkbeer.compat.jade.provider.BeerBarrelComponentProvider;
import lekavar.lma.drinkbeer.compat.jade.provider.MixedBeerComponentProvider;
import lekavar.lma.drinkbeer.compat.jade.provider.MixedBeerHeadComponentProvider;
import lekavar.lma.drinkbeer.compat.jade.provider.TradeBoxComponentProvider;
import mcp.mobius.waila.api.*;

@WailaPlugin
public class JadeCompat implements IWailaPlugin {
	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.registerComponentProvider(BeerBarrelComponentProvider.INSTANCE, TooltipPosition.BODY, BeerBarrelBlock.class);
		registration.registerComponentProvider(TradeBoxComponentProvider.INSTANCE, TooltipPosition.BODY, TradeboxBlock.class);
		registration.registerComponentProvider(MixedBeerComponentProvider.INSTANCE, TooltipPosition.BODY, MixedBeerBlock.class);
		registration.registerComponentProvider(MixedBeerHeadComponentProvider.INSTANCE, TooltipPosition.HEAD, MixedBeerBlock.class);

		registration.registerIconProvider(MixedBeerHeadComponentProvider.INSTANCE, MixedBeerBlock.class);
	}

	@Override
	public void register(IWailaCommonRegistration registration) {
		registration.registerBlockDataProvider(BeerBarrelComponentProvider.INSTANCE, BeerBarrelBlockEntity.class);
		registration.registerBlockDataProvider(TradeBoxComponentProvider.INSTANCE, TradeBoxBlockEntity.class);
	}
}
