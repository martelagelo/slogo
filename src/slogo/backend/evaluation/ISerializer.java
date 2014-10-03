package slogo.backend.evaluation;

import java.io.Reader;

import slogo.backend.parsing.IASTNode;

public interface ISerializer {

	public Reader serialize(IASTNode root);
}
