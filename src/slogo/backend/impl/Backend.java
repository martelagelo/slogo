package slogo.backend.impl;

import java.util.Collection;
import java.util.Map;

import slogo.IModel;
import slogo.backend.evaluation.ElementUnsupportedException;

public class Backend implements IModel{

	@Override
	public void execute(String string) {
		
	}

	@Override
	public Map<String, String> getData(Collection<String> elements)
			throws ElementUnsupportedException {
		return null;
	}

}
