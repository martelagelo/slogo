package slogo.backend.impl.util;

import java.util.ArrayList;
import java.util.List;

import slogo.backend.util.ICoordinates;
import slogo.backend.util.IDirection;
import slogo.backend.util.ILine;
import slogo.backend.util.ITurtleStatus;
import slogo.backend.util.PenState;
import slogo.backend.util.Visibility;

public class TurtleStatus implements ITurtleStatus {
	private List<ILine> lineSequence;
	private ICoordinates turtlePosition;
	private IDirection turtleDirection;
	private PenState penState;
	private Visibility turtleVisibility;
	private boolean isActive;

	public static class Builder {
		private List<ILine> lineSequence;
		private ICoordinates turtlePosition;
		private IDirection turtleDirection;
		private PenState penState;
		private Visibility turtleVisibility;
		private boolean isActive;
		public Builder() {
			this.lineSequence = new ArrayList<>();
			this.turtlePosition = new Coordinates(0, 0);
			this.turtleDirection = new Direction(0);
			this.penState = PenState.DOWN;
			this.turtleVisibility = Visibility.VISIBLE;
			this.isActive = true;
		}
		public Builder(ITurtleStatus status) {
			this.lineSequence = status.lineSequence();
			this.turtlePosition = status.turtlePosition();
			this.turtleDirection = status.turtleDirection();
			this.penState = status.penState();
			this.turtleVisibility = status.turtleVisibility();
			this.isActive = status.isActive();
		}
		public Builder lineSequence(List<ILine> lines){
			this.lineSequence = lines;
			return this;
		}
		public Builder turtlePosition(ICoordinates position) {
			this.turtlePosition = position;
			return this;
		}
		public Builder turtleDirection(IDirection direction) {
			this.turtleDirection = direction;
			return this;
		}
		public Builder penState(PenState state) {
			this.penState = state;
			return this;
		}
		public Builder turtleVisibility(Visibility visibility) {
			this.turtleVisibility = visibility;
			return this;
		}
		public Builder isActive(boolean isActive){
			this.isActive = isActive;
			return this;
		}
		public TurtleStatus build() {
			return new TurtleStatus(this);
		}
	}
	public TurtleStatus(List<ILine> l, ICoordinates p, IDirection d, PenState s, Visibility v){
		lineSequence = l;
		turtlePosition = p;
		turtleDirection = d;
		penState = s;
		turtleVisibility = v;
		this.isActive = true;
	}
	private TurtleStatus(Builder builder) {
		this.lineSequence = builder.lineSequence;
		this.turtlePosition = builder.turtlePosition;
		this.turtleDirection = builder.turtleDirection;
		this.penState = builder.penState;
		this.turtleVisibility = builder.turtleVisibility;
		this.isActive = builder.isActive;
	}

	@Override
	public List<ILine> lineSequence () {
		return lineSequence;
	}

	@Override
	public ICoordinates turtlePosition () {
		return turtlePosition;
	}

	@Override
	public IDirection turtleDirection () {
		return turtleDirection;
	}

	@Override
	public PenState penState () {
		return penState;
	}

	@Override
	public Visibility turtleVisibility () {
		return turtleVisibility;
	}
	@Override
	public boolean isActive() {
		return isActive;
	}
    @Override
    public void setActive (boolean b) {
        isActive = b;
        
    }
}
