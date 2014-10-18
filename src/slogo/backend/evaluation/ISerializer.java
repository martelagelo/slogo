package slogo.backend.evaluation;

import java.io.Reader;

import slogo.backend.parsing.ISyntaxNode;

public interface ISerializer {

	public Reader serialize(ISyntaxNode root);
}
