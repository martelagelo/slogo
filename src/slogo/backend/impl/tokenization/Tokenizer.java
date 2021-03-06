package slogo.backend.impl.tokenization;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import slogo.Constants;
import slogo.backend.tokenization.IToken;
import slogo.backend.tokenization.ITokenRule;
import slogo.backend.tokenization.ITokenizer;
import slogo.backend.tokenization.InvalidTokenRulesException;

public class Tokenizer implements ITokenizer {
    private List<ITokenRule> rules;

    public Tokenizer (List<ITokenRule> rules) throws InvalidTokenRulesException {
        loadTokenRules(rules);
    }

    @Override
    public List<IToken> tokenize (Reader input) throws IOException {
        int current;
        StringBuilder buffer = new StringBuilder();
        List<IToken> tokens = new ArrayList<>();
        // we use while(true) with an explicit break because token matching
        // needs to know when we've reached the end of the string so that it
        // can ignore the closing pattern; otherwise, if the last character of
        // of the last token is the last character of the string, we have no
        // way to recognize that token
        while (true) {
            current = input.read();
            boolean isStart = (tokens.size() == 0) ? true : false;
            boolean isEnd = (current == -1) ? true : false;

            if (!isEnd) {
                buffer.append((char) current);
            }
            String bufferString = buffer.toString();

            for (ITokenRule rule : rules) {
                Map<String, IToken> candidateMatch = rule.match(bufferString, isStart, isEnd);
                if (candidateMatch.size() > 0) {
                    tokens.add(candidateMatch.get(Constants.BODY_TOKEN_STRING));
                    IToken closingToken = candidateMatch.get(Constants.CLOSING_TOKEN_STRING);
                    if (closingToken != null) {
                        buffer = new StringBuilder(closingToken.text());
                    }
                    break;
                }
            }
            if (isEnd) {
                break;
            }
        }
        return tokens;
    }

    @Override
    public void loadTokenRules (List<ITokenRule> rules) throws InvalidTokenRulesException {
        if (rules != null) {
            this.rules = rules;
        } else {
            throw new InvalidTokenRulesException();
        }
    }
}
