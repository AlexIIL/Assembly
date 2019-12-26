package team.reborn.assembly.attributes;

import alexiil.mc.lib.attributes.Simulation;
import alexiil.mc.lib.attributes.fluid.FixedFluidInv;
import alexiil.mc.lib.attributes.fluid.FluidExtractable;
import alexiil.mc.lib.attributes.fluid.amount.FluidAmount;
import alexiil.mc.lib.attributes.fluid.filter.FluidFilter;
import alexiil.mc.lib.attributes.fluid.volume.FluidVolume;

public interface OutputFluidContainer extends AssemblyFluidContainer, FluidExtractable {

	// FluidExtractable
	@Override
	default FluidVolume attemptExtraction(FluidFilter filter, FluidAmount maxAmount, Simulation simulation) {
		return getGroupedInv().attemptExtraction(filter, maxAmount, simulation);
	}

	@Override
	default FluidVolume attemptAnyExtraction(FluidAmount maxAmount, Simulation simulation) {
		return getGroupedInv().attemptAnyExtraction(maxAmount, simulation);
	}
}