package slogo.backend.impl.evaluation.commands.control;

import java.util.List;

import slogo.Constants;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.commands.Operation;
import slogo.backend.parsing.ISyntaxNode;

public abstract class ConditionalOperation extends Operation {

    public ConditionalOperation (String type, int argMin, int argMax) {
        super(type, argMin, argMax);
    }

    @Override
    protected abstract IExecutionContext executeRaw (List<IExecutionContext> args,
            IExecutionContext previous, ISyntaxNode current) throws MalformedSyntaxException;

    /**
     * If you can keep your head when all about you
     * <P>
     * are losing theirs and blaming it on you
     * <P>
     * If you can trust yourself when all men doubt you
     * <P>
     * but make allowance for their doubting too
     * <P>
     * If you can wait and not be tired by waiting
     * <P>
     * Or being lied about, don't deal in lies
     * <P>
     * Or being hated, don't give way to hating
     * <P>
     * And yet don't look too good, nor talk too wise
     * <P>
     * 
     * @return You'll be a man, my son!
     * @throws MalformedSyntaxException
     */
    public IExecutionContext kipling (List<IExecutionContext> args, IExecutionContext previous,
            ISyntaxNode current, int conditionIndex, int startOfCommands)
            throws MalformedSyntaxException {
        int condition;
        String conditionString = getContextReturnValue(args.get(conditionIndex));
        if (conditionString.length() != 0) {
            condition = Integer.parseInt(conditionString);
        } else {
            throw new MalformedSyntaxException(
                    "Condition expression could not be found at position: " + conditionIndex);
        }
        int trueCommandStart = startOfCommands;
        int trueCommandEnd = getMatchingParentheses(current.children(), trueCommandStart);
        int falseCommandStart;
        int falseCommandEnd;
        if (trueCommandEnd >= current.children().size() - 1) {
            falseCommandStart = 0;
            falseCommandEnd = 0;
        } else {
            falseCommandStart = trueCommandEnd + 1;
            falseCommandEnd = getMatchingParentheses(current.children(), falseCommandStart);
        }

        Evaluator evaluator = new Evaluator();
        IExecutionContext context = previous;
        if (condition == Constants.TRUE_INT) {
            for (int i = trueCommandStart; i < trueCommandEnd; i++) {
                context = evaluator.evaluate(current.children().get(i), context);
            }
        } else {
            for (int i = falseCommandStart; i < falseCommandEnd; i++) {
                context = evaluator.evaluate(current.children().get(i), context);
            }
        }
        return context;
    }

    private int getMatchingParentheses (List<ISyntaxNode> nodes, int openingIndex)
            throws MalformedSyntaxException {
        int matchingIndex = openingIndex;
        while (nodes.get(matchingIndex).type() != Constants.CLOSING_LIST_LABEL
                && matchingIndex < nodes.size()) {
            matchingIndex++;
        }
        return matchingIndex;
    }
}
