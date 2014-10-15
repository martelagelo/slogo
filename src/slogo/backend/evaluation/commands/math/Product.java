package slogo.backend.evaluation.commands.math;

import java.util.List;

import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.commands.MathOperation;

public class Product extends MathOperation{

    public Product() {
		super("Product", 2, -1);
	}

	@Override
	protected Number executeMath(List<Number> args) {
		double result = 1;
		for (Number number: args){
			result *= number.doubleValue();
		}
		return result;
	}
}
