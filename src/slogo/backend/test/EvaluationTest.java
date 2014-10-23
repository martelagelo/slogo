package slogo.backend.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import slogo.Constants;
import slogo.backend.evaluation.IEvaluator;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.evaluation.commands.Constant;
import slogo.backend.impl.evaluation.commands.Result;
import slogo.backend.impl.evaluation.commands.math.Difference;
import slogo.backend.impl.evaluation.commands.math.Minus;
import slogo.backend.impl.evaluation.commands.math.Sum;
import slogo.backend.impl.parsing.SyntaxNode;
import slogo.backend.parsing.ISyntaxNode;

public class EvaluationTest {
	
	private ISyntaxNode setupTree(){
		ISyntaxNode rootResult = new SyntaxNode("constant", new Result(), new ArrayList<>());
		ISyntaxNode rootOperation = new SyntaxNode("Minus", new Minus(), new ArrayList<>());
		ISyntaxNode middleResult = new SyntaxNode("constant", new Result(), new ArrayList<>());
		ISyntaxNode middleOperation = new SyntaxNode("Difference", new Difference(), new ArrayList<>());
		ISyntaxNode leafConstant1 = new SyntaxNode("constant", new Constant("80"), new ArrayList<>());
		ISyntaxNode leafConstant2 = new SyntaxNode("constant", new Constant("50"), new ArrayList<>());
		rootResult.children().add(rootOperation);
		rootOperation.children().add(middleResult);
		middleResult.children().add(middleOperation);
		middleOperation.children().add(leafConstant1);
		middleOperation.children().add(leafConstant2);
		return rootResult;
	}
	@Test
	public void testEvaluate(){
		IEvaluator evaluator = new Evaluator();
		IExecutionContext previous = new ExecutionContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
		IExecutionContext context = null;
		try {
			context = evaluator.evaluate(setupTree(), previous);
		} catch (MalformedSyntaxException e) {
			fail("Bad syntax");
		}
		assertNotNull(context);
		assertEquals("-30.0", context.environment().get(Constants.RETURN_VALUE_ENVIRONMENT));
	}
}
