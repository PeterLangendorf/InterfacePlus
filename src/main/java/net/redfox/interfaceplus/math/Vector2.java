package net.redfox.interfaceplus.math;

public class Vector2 {
	private double x;
	private double y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void set(Vector2 replacement) {
		this.x = replacement.x;
		this.y = replacement.y;
	}

	public Vector2 addX(double x) {
		this.x += x;
		return this;
	}
	public Vector2 addY(double y) {
		this.y += y;
		return this;
	}
	public Vector2 addXY(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	public Vector2 addVector2(Vector2 vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}
	public Vector2 subtractX(double x) {
		this.x -= x;
		return this;
	}
	public Vector2 subtractY(double y) {
		this.y -= y;
		return this;
	}
	public Vector2 subtractXY(double x, double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	public Vector2 subtractVector2(Vector2 vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}
	public Vector2 multiplyX(double scalar) {
		this.x *= scalar;
		return this;
	}
	public Vector2 multiplyY(double scalar) {
		this.y *= scalar;
		return this;
	}
	public Vector2 multiplyXY(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}
	public Vector2 multiplyVector(Vector2 scalar) {
		this.x *= scalar.x;
		this.y *= scalar.y;
		return this;
	}
	public Vector2 divideX(double scalar) {
		this.x /= scalar;
		return this;
	}
	public Vector2 divideY(double scalar) {
		this.y /= scalar;
		return this;
	}
	public Vector2 divideXY(double scalar) {
		this.x /= scalar;
		this.y /= scalar;
		return this;
	}
	public Vector2 divideVector(Vector2 scalar) {
		this.x /= scalar.x;
		this.y /= scalar.y;
		return this;
	}

	public static Vector2 zero() {
		return new Vector2(0, 0);
	}
}
