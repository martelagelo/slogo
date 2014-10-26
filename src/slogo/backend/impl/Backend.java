package slogo.backend.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.ExecutionException;
import slogo.IModel;
import slogo.backend.evaluation.IExecutionContext;
import slogo.backend.evaluation.MalformedSyntaxException;
import slogo.backend.impl.evaluation.Evaluator;
import slogo.backend.impl.evaluation.ExecutionContext;
import slogo.backend.impl.parsing.GrammarRule;
import slogo.backend.impl.parsing.Parser;
import slogo.backend.impl.tokenization.TokenRule;
import slogo.backend.impl.tokenization.Tokenizer;
import slogo.backend.impl.util.TurtleStatus;
import slogo.backend.parsing.IGrammarRule;
import slogo.backend.parsing.InvalidGrammarRuleException;
import slogo.backend.tokenization.ITokenRule;
import slogo.backend.tokenization.InvalidTokenRulesException;
import slogo.backend.util.ITurtleStatus;

public class Backend implements IModel {

    private IExecutionContext lastContext;
    private Tokenizer tokenizer;
    private Parser parser;
    private Evaluator evaluator;

    public Backend () throws InitializationException {
        Map<String, ITurtleStatus> turtles = new HashMap<>();
        turtles.put(Constants.DEFAULT_TURTLE_NAME, new TurtleStatus.Builder().build());
        this.lastContext = new ExecutionContext(turtles, new HashMap<>(), new HashMap<>());

        try {
            tokenizer = new Tokenizer(tokenRules());
        } catch (InvalidTokenRulesException e) {
            throw new InitializationException(e.getMessage());
        }
        try {
            parser = new Parser(grammarRules());
        } catch (InvalidGrammarRuleException e) {
            throw new InitializationException(e.getMessage());
        }
        evaluator = new Evaluator();
    }

    private List<ITokenRule> tokenRules () {
        List<ITokenRule> rules = new ArrayList<>();
        rules.add(new TokenRule.Builder(Constants.CONSTANT_LABEL, "-?[0-9]+\\.?[0-9]*").build());
        rules.add(new TokenRule.Builder(Constants.COMMAND_LABEL, "[a-zA-Z_]+(\\?)?").build());
        rules.add(new TokenRule.Builder(Constants.VARIABLE_LABEL, ":[a-zA-Z]+").build());
        rules.add(new TokenRule.Builder(Constants.OPENING_LIST_LABEL, "\\[").build());
        rules.add(new TokenRule.Builder(Constants.CLOSING_LIST_LABEL, "\\]").build());
        return rules;
    }

    private List<IGrammarRule> grammarRules () {
        String[][] rules = { { "And", "2" }, { "Equal", "2" }, { "GreaterThan", "2" }, { "LessThan", "2" },
                { "Not", "1" }, { "NotEqual", "2" }, { "Or", "2" }, { "ArcTangent", "1" },
                { "Cosine", "1" }, { "Difference", "2" }, { "NaturalLog", "1" }, { "Minus", "1" },
                { "Power", "2" }, { "Product", "2" }, { "Quotient", "2" }, { "RandomNumber", "1" },
                { "Remainder", "2" }, { "Sine", "1" }, { "Sum", "2" }, { "Tangent", "1" },
                { "Backward", "1" }, { "ClearScreen", "0" }, { "Forward", "1" }, { "Heading", "0" },
                { "HideTurtle", "0" }, { "Home", "0" }, { "IsPenDown", "0" }, { "IsShowing", "0" },
                { "Left", "1" }, { "PenDown", "0" }, { "PenUp", "0" }, { "Right", "1" },
                { "SetHeading", "1" }, { "SetPosition", "2" }, { "ShowTurtle", "0" }, { "SetTowards", "2" },
                { "XCoordinate", "0" }, { "YCoordinate", "0" }, { "SetPenSize", "1" }, { "SetShape", "1" },
                { "SetPenColor", "3" }, { "SetBackground", "3" }, { "ID", "0" }, { "Turtles", "0" } };
        String[][][] controlRules = {
                {
                        { "For" },
                        { Constants.OPENING_LIST_LABEL, Constants.VARIABLE_LABEL,
                                Constants.CONSTANT_LABEL, Constants.CONSTANT_LABEL,
                                Constants.CONSTANT_LABEL, Constants.CLOSING_LIST_LABEL,
                                Constants.OPENING_LIST_LABEL, Constants.CONSTANT_LABEL,
                                Constants.INFINITE_MATCHING_LABEL, Constants.CLOSING_LIST_LABEL } },
                { { "MakeVariable" }, { Constants.VARIABLE_LABEL, Constants.CONSTANT_LABEL } },
                { { "Set" }, { Constants.VARIABLE_LABEL, Constants.CONSTANT_LABEL } },
                {
                        { "Repeat" },
                        { Constants.CONSTANT_LABEL, Constants.OPENING_LIST_LABEL,
                                Constants.CONSTANT_LABEL, Constants.INFINITE_MATCHING_LABEL,
                                Constants.CLOSING_LIST_LABEL } },
                {
                        { "DoTimes" },
                        { Constants.OPENING_LIST_LABEL, Constants.VARIABLE_LABEL,
                                Constants.CONSTANT_LABEL, Constants.CLOSING_LIST_LABEL,
                                Constants.OPENING_LIST_LABEL, Constants.CONSTANT_LABEL,
                                Constants.INFINITE_MATCHING_LABEL, Constants.CLOSING_LIST_LABEL } },
                {
                        { "If" },
                        { Constants.CONSTANT_LABEL, Constants.OPENING_LIST_LABEL,
                                Constants.CONSTANT_LABEL, Constants.INFINITE_MATCHING_LABEL,
                                Constants.CLOSING_LIST_LABEL } },
                {
                        { "IfElse" },
                        { Constants.CONSTANT_LABEL, Constants.OPENING_LIST_LABEL,
                                Constants.CONSTANT_LABEL, Constants.INFINITE_MATCHING_LABEL,
                                Constants.CLOSING_LIST_LABEL, Constants.OPENING_LIST_LABEL,
                                Constants.CONSTANT_LABEL, Constants.INFINITE_MATCHING_LABEL,
                                Constants.CLOSING_LIST_LABEL } },
                {
                        { "MakeUserInstruction" },
                        { Constants.CONSTANT_LABEL, Constants.OPENING_LIST_LABEL,
                                Constants.VARIABLE_LABEL, Constants.INFINITE_MATCHING_LABEL,
                                Constants.CLOSING_LIST_LABEL, Constants.OPENING_LIST_LABEL,
                                Constants.CONSTANT_LABEL, Constants.INFINITE_MATCHING_LABEL,
                                Constants.CLOSING_LIST_LABEL } },
                {
                        { "Tell" },
                        { Constants.OPENING_LIST_LABEL, Constants.CONSTANT_LABEL,
                                Constants.INFINITE_MATCHING_LABEL, Constants.CLOSING_LIST_LABEL } },
                {
                        { "Ask" },
                        { Constants.OPENING_LIST_LABEL, Constants.CONSTANT_LABEL,
                                Constants.INFINITE_MATCHING_LABEL, Constants.CLOSING_LIST_LABEL,
                                Constants.OPENING_LIST_LABEL, Constants.CONSTANT_LABEL,
                                Constants.INFINITE_MATCHING_LABEL, Constants.CLOSING_LIST_LABEL

                        } } };

        List<IGrammarRule> ruleList = new ArrayList<>();
        for (String[] rule : rules) {
            int numRepeats = Integer.parseInt(rule[1]);
            String[][] repeatedConstants = new String[numRepeats][2];
            for (int i = 0; i < numRepeats; i++) {
                repeatedConstants[i][0] = Constants.CONSTANT_LABEL;
                repeatedConstants[i][1] = Constants.VARIABLE_LABEL;
            }
            String[] wrappedCommandName = { rule[0] };
            ruleList.add(new GrammarRule(wrappedCommandName, repeatedConstants));
        }
        for (String[][] rule : controlRules) {
            ruleList.add(new GrammarRule(rule[0][0], Arrays.asList(rule[1])));
        }
        return ruleList;
    }

    @Override
    public IExecutionContext execute (String string) throws ExecutionException {
        StringReader reader = new StringReader(string);
        IExecutionContext result = null;
        try {
            result = evaluator.evaluate(parser.parse(tokenizer.tokenize(reader)), lastContext);
        } catch (MalformedSyntaxException | IOException e) {
            throw new ExecutionException(e.getMessage());
        }
        if (result != null) {
            lastContext = result;
            return result;
        } else {
            throw new ExecutionException("Executing " + string
                    + " resulted in a invalid (null) result being returned");
        }
    }

}
