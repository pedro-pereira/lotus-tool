package br.uece.lotus.simulator;

import br.uece.lotus.State;
import br.uece.lotus.Transition;

import javax.script.ScriptException;

/**
 * Created by erickbs7 on 02/02/15.
 */
public class SimulatorUtils {

	public static void showChoices(State currentState, SimulatorContext mSimulatorContext) {
		applyEnableStyle(currentState);
		for (Transition t : currentState.getOutgoingTransitions()) {
			applyChoiceStyle(t, mSimulatorContext);
			applyChoiceStyle(t.getDestiny());
		}
	}

	public static void hideChoices(State currentState) {
		applyDisabledStyle(currentState);
		for (Transition t : currentState.getOutgoingTransitionsList()) {
			if (t.getDestiny().getmVisitedStatesCount() > 0) {
				applyEnableStyle(t.getDestiny());
			} else {
				applyDisabledStyle(t.getDestiny());
			}

			if (t.getmTransitionsStatesCount() > 0) {
				applyEnableStyle(t);
			} else {
				applyDisabledStyle(t);
			}
		}
	}

	public static void applyEnableStyle(State s) {
		s.setColor(null);
		s.setTextColor("black");
		s.setTextSyle(State.TEXTSTYLE_NORMAL);
		s.setBorderColor("black");
		s.setBorderWidth(1);
	}

	public static void applyEnableStyle(Transition t) {
		t.setColor("black");
		t.setTextSyle(Transition.TEXTSTYLE_NORMAL);
		t.setTextColor("black");
		t.setWidth(1);
	}

	public static void applyDisabledStyle(State s) {
		s.setColor("#d0d0d0");
		s.setTextColor("#c0c0c0");
		s.setTextSyle(State.TEXTSTYLE_NORMAL);
		s.setBorderColor("gray");
		s.setBorderWidth(1);
	}

	public static void applyDisabledStyle(Transition t) {
		t.setColor("#d0d0d0");
		t.setTextColor("#c0c0c0");
		t.setTextSyle(Transition.TEXTSTYLE_NORMAL);
		t.setWidth(1);
	}

	public static void applyChoiceStyle(Transition t, SimulatorContext mSimulatorContext) {
		boolean guardResult = true;
		try {
			if (t.getGuard() != null && !t.getGuard().isEmpty()) {
				guardResult = (boolean) mSimulatorContext.getmEngine().eval(t.getGuard());
			}
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		t.setColor(guardResult ? "blue" : "red");
		t.setTextSyle(Transition.TEXTSTYLE_BOLD);
		t.setTextColor(guardResult ? "blue" : "red");
		t.setWidth(2);
	}

	public static void applyChoiceStyle(State s) {
		s.setColor(null);
		s.setBorderColor("blue");
		s.setTextSyle(Transition.TEXTSTYLE_BOLD);
		s.setTextColor("blue");
		s.setBorderWidth(2);
	}
}
