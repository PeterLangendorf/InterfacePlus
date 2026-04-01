package net.redfox.interfaceplus.math;

@SuppressWarnings("unused")
public class Vector2Math {
    /**
     * Calculates the geometrical distance between two points using the Pythagorean theorem.
     *
     * @param origin The starting point
     * @param goal The ending point
     * @return A double containing the distance from the starting point to the ending point.
     */
    private static double getDistanceBetweenTwoPoints(Vector2 origin, Vector2 goal) {
        return Math.hypot(goal.getX() - origin.getX(), goal.getY() - origin.getY());
    }

    /**
     * Calculates the direction that one would need to travel to get from origin to goal.
     *
     * @param origin The starting location.
     * @param goal The goal location.
     * @return A double containing the direction in radians.
     */
    public static double getDirectionToPoint(Vector2 origin, Vector2 goal) {
        return Math.atan2(goal.getY() - origin.getY(), goal.getX() - origin.getX());
    }

    /**
     * Calculates x and y increments that need to be added to your current x and y position to move speed units in radians direction
     *
     * @param radians The angle in radians that you are traveling
     * @param speed The speed at which you are going
     * @return A Vector2 containing the increments that should be added to your position to move speed units in radians direction
     */
    public static Vector2 getIncrementsBySpeed(double radians, double speed) {
        return new Vector2(speed * Math.cos(radians), speed * Math.sin(radians));
    }

    /**
     * Calculates the increments required to be added to a position in order to move speed units towards goal from origin
     *
     * @param origin The starting position
     * @param goal The ending position
     * @param speed The speed at which the object is moving
     * @return A Vector2 containing the increments that should be added to your position to move speed units in radians direction
     */
    public static Vector2 getIncrementsFromPoint(Vector2 origin, Vector2 goal, double speed) {
        double distance = getDistanceBetweenTwoPoints(origin, goal);
        if (distance > speed) return getIncrementsBySpeed(getDirectionToPoint(origin, goal), speed);
        return getIncrementsBySpeed(getDirectionToPoint(origin, goal), distance);
    }
}
