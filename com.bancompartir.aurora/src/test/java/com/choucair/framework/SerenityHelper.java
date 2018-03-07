package com.choucair.framework;

import net.thucydides.core.steps.StepEventBus;

public class SerenityHelper {

	public static void agregarDescripcionAStep(String strTexto) {
		StepEventBus.getEventBus().updateCurrentStepTitle(strTexto);
	}

}
