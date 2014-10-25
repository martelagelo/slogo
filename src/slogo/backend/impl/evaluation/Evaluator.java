package slogo.backend.impl.evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.evaluation.IEvaluator;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.parsing.ISyntaxNode;

public class Evaluator implements IEvaluator{

	@Override
	public IExecutionContext evaluate(ISyntaxNode current, IExecutionContext previous) throws MalformedSyntaxException {
		if (current.children().size() == 0) {
			List<IExecutionContext> previousList = new ArrayList<>();
			previousList.add(previous);
			IExecutionContext result = current.operation().execute(previousList,previous,current);
			return result;
		}
		List<IExecutionContext> results = new ArrayList<>();
		for (ISyntaxNode child: current.children()){
			IExecutionContext result = evaluate(child, previous);
			results.add(result);
		}
		IExecutionContext result = current.operation().execute(results,previous,current);
		return result;
	}
}
