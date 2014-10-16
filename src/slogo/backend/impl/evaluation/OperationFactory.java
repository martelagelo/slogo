package slogo.backend.impl.evaluation;

import slogo.backend.evaluation.IOperation;
import slogo.backend.evaluation.IOperationFactory;

public class OperationFactory implements IOperationFactory {
	private String[] searchPath;
	private String root;
	public OperationFactory(){
		this.root = "slogo.backend.evaluation.commands";
		String[] searchPath = { "booleans", "control", "math", "turtle" };
		this.searchPath = searchPath;
	}
	@Override
	public IOperation makeElement(String element) throws ClassNotFoundException {
		IOperation newOperation = null;
		for (String path: searchPath){
			try {
				Class operation = Class.forName(root + "." + path + "." + element);
				newOperation = (IOperation) operation.newInstance();
				break;
			}
			catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
				continue;
			}
		}
		if (newOperation != null){
			return newOperation;
		}
		else{
			throw new ClassNotFoundException(element + " was not found");
		}
	}

}