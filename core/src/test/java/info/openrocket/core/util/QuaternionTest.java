package info.openrocket.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuaternionTest {

	private final double testW = 0.237188;
	private final double testX = 0.570190;
	private final double testY = -0.514542;
	private final double testZ = 0.594872;
	private final double TEST_COORDINATE_X = 148578428.914;
	private final double TEST_COORDINATE_Y = 8126778.954;
	private final double TEST_COORDINATE_Z = -607.741;
	private final double DELTA_2SF = 1e-2;
	private final double DELTA_3SF = 1e-3;
	private final double DELTA_5SF = 1e-5;
	private final double DELTA_12SF = 1e-12;
	private final double EXPECTED_ROTATED_X = -42312599.537;
	private final double EXPECTED_ROTATED_Y = -48162747.551;
	private final double EXPECTED_ROTATED_Z = 134281904.197;
	private final CoordinateIF FORTY_FIVE_DEGREE_ROTATION_COORDINATE = new Coordinate(Math.PI / 4, 0, 0);
	private final CoordinateIF Y_UNIT_COORDINATE = new Coordinate(0, 1, 0);


	private Quaternion testQuat;
	private CoordinateIF testCoordinate;
	private CoordinateIF testRotatedCoordinate;

	@BeforeEach
	public void setup(){
		this.testQuat = new Quaternion(testW, testX, testY, testZ);
		this.testCoordinate = new Coordinate(TEST_COORDINATE_X, TEST_COORDINATE_Y, TEST_COORDINATE_Z);
		this.testRotatedCoordinate = this.testQuat.rotate(testCoordinate);
	}

	@Test
	public void testQuatIsNormalised(){
		assertEquals(1.0, this.testQuat.norm(), DELTA_2SF);

	}

	@Test
	public void isQuaternionNormalised(){
		assertEquals(1.0, this.testQuat.norm(), DELTA_2SF);
	}

	@Test
	public void isNormalizedQuaternionWCorrect(){
		this.testQuat.normalize();
		assertEquals(testW, this.testQuat.getW(), DELTA_5SF);
	}

	@Test
	public void isNormalizedQuaternionXCorrect(){
		this.testQuat.normalize();
		assertEquals(testX, this.testQuat.getX(), DELTA_5SF);
	}

	@Test
	public void isNormalizedQuaternionYCorrect(){
		this.testQuat.normalize();
		assertEquals(testY, this.testQuat.getY(), DELTA_5SF);
	}

	@Test
	public void isNormalizedQuaternionZCorrect(){
		this.testQuat.normalize();
		assertEquals(testZ, this.testQuat.getZ(), DELTA_5SF);
	}

	@Test
	public void correctXCooordinateAfterRotation(){
		assertEquals(EXPECTED_ROTATED_X, testRotatedCoordinate.getX(), DELTA_3SF);
	}

	@Test
	public void correctYCoordinateAfterRotation(){
		assertEquals(EXPECTED_ROTATED_Y, testRotatedCoordinate.getY(), DELTA_3SF);
	}

	@Test
	public void correctZCoordinateAfterRotation(){
		assertEquals(EXPECTED_ROTATED_Z, testRotatedCoordinate.getZ(), DELTA_3SF);
	}

	@Test
	public void fortyFiveDegreeInvRotationCorrectX(){
		CoordinateIF rotatedCoordinate = Quaternion.rotation(FORTY_FIVE_DEGREE_ROTATION_COORDINATE).invRotate(Y_UNIT_COORDINATE);
		assertEquals(0.0, rotatedCoordinate.getX(), DELTA_3SF);
		assertEquals(0.707, rotatedCoordinate.getY(), DELTA_3SF);
		assertEquals(-0.707, rotatedCoordinate.getZ(), DELTA_3SF);
	}

	@Test
	public void rotationAboutAxisMatchesRotationVector() {
		// A quarter turn about z takes the x axis onto the y axis
		Quaternion q = Quaternion.rotation(Coordinate.Z_UNIT, Math.PI / 2);
		CoordinateIF c = q.rotate(Coordinate.X_UNIT);

		assertEquals(0.0, c.getX(), DELTA_12SF);
		assertEquals(1.0, c.getY(), DELTA_12SF);
		assertEquals(0.0, c.getZ(), DELTA_12SF);

		// and must agree with the rotation vector form of the same rotation
		Quaternion v = Quaternion.rotation(new Coordinate(0, 0, Math.PI / 2));
		assertEquals(v.getW(), q.getW(), DELTA_12SF);
		assertEquals(v.getX(), q.getX(), DELTA_12SF);
		assertEquals(v.getY(), q.getY(), DELTA_12SF);
		assertEquals(v.getZ(), q.getZ(), DELTA_12SF);
	}

	@Test
	public void rotationVectorZeroLengthReturnsIdentityQuaternion() {
		Quaternion q = Quaternion.rotation(new Coordinate(0, 0, 0));
		assertEquals(1.0, q.getW(), DELTA_12SF);
		assertEquals(0.0, q.getX(), DELTA_12SF);
		assertEquals(0.0, q.getY(), DELTA_12SF);
		assertEquals(0.0, q.getZ(), DELTA_12SF);
	}

	@Test
	public void multiplyWithIdentityReturnsOriginalQuaternion() {
		Quaternion q = new Quaternion(0.5, -0.5, 0.25, 0.75);
		Quaternion identity = new Quaternion();

		Quaternion right = q.multiplyRight(identity);
		Quaternion left = q.multiplyLeft(identity);

		assertEquals(q.getW(), right.getW(), DELTA_12SF);
		assertEquals(q.getX(), right.getX(), DELTA_12SF);
		assertEquals(q.getY(), right.getY(), DELTA_12SF);
		assertEquals(q.getZ(), right.getZ(), DELTA_12SF);

		assertEquals(q.getW(), left.getW(), DELTA_12SF);
		assertEquals(q.getX(), left.getX(), DELTA_12SF);
		assertEquals(q.getY(), left.getY(), DELTA_12SF);
		assertEquals(q.getZ(), left.getZ(), DELTA_12SF);
	}

	@Test
	public void rotateAndInverseRotateReturnOriginalCoordinate() {
		Quaternion rotation = Quaternion.rotation(new Coordinate(0, 0, Math.PI / 2));
		CoordinateIF original = new Coordinate(1, 0, 0);

		CoordinateIF rotated = rotation.rotate(original);
		CoordinateIF restored = rotation.invRotate(rotated);

		assertEquals(original.getX(), restored.getX(), DELTA_12SF);
		assertEquals(original.getY(), restored.getY(), DELTA_12SF);
		assertEquals(original.getZ(), restored.getZ(), DELTA_12SF);
	}

	@Test
	public void rotateInPlaceMatchesImmutableRotation() {
		Quaternion rotation = Quaternion.rotation(new Coordinate(Math.PI / 3, 0, 0));
		MutableCoordinate mutable = new MutableCoordinate(0, 1, 0);

		CoordinateIF rotated = rotation.rotate(mutable.toImmutable());
		rotation.rotateInPlace(mutable);

		assertEquals(rotated.getX(), mutable.getX(), DELTA_12SF);
		assertEquals(rotated.getY(), mutable.getY(), DELTA_12SF);
		assertEquals(rotated.getZ(), mutable.getZ(), DELTA_12SF);
	}

	@Test
	public void inverseRotateInPlaceRestoresCoordinate() {
		Quaternion rotation = Quaternion.rotation(new Coordinate(0, Math.PI / 6, 0));
		MutableCoordinate mutable = new MutableCoordinate(0, 0, 1);

		rotation.rotateInPlace(mutable);
		rotation.invRotateInPlace(mutable);

		assertEquals(0.0, mutable.getX(), DELTA_12SF);
		assertEquals(0.0, mutable.getY(), DELTA_12SF);
		assertEquals(1.0, mutable.getZ(), DELTA_12SF);
	}

	@Test
	public void normalizeThrowsForZeroQuaternion() {
		Quaternion zero = new Quaternion(0, 0, 0, 0);
		assertThrows(IllegalStateException.class, zero::normalize);
	}

	@Test
	public void normalizeIfNecessaryReturnsSameInstanceWhenAlreadyUnit() {
		double component = 1.0 / Math.sqrt(2);
		Quaternion unit = new Quaternion(component, component, 0, 0);

		Quaternion normalized = unit.normalizeIfNecessary();
		assertSame(unit, normalized);
	}

	@Test
	public void normalizeIfNecessaryNormalizesWhenLengthDeviates() {
		Quaternion scaled = new Quaternion(2, 0, 0, 0);
		Quaternion normalized = scaled.normalizeIfNecessary();

		assertNotSame(scaled, normalized);
		assertEquals(1.0, normalized.norm(), 1.0e-12);
		assertEquals(1.0, normalized.getW(), 1.0e-12);
	}

	@Test
	public void cloneReturnsIndependentInstance() {
		Quaternion q = new Quaternion(0.1, 0.2, 0.3, 0.4);
		Quaternion copy = q.clone();

		assertNotSame(q, copy);
		assertEquals(q.getW(), copy.getW(), 0.0);
		assertEquals(q.getX(), copy.getX(), 0.0);
		assertEquals(q.getY(), copy.getY(), 0.0);
		assertEquals(q.getZ(), copy.getZ(), 0.0);
	}

	@Test
	public void isNaNDetectsAnyNaNComponent() {
		assertTrue(new Quaternion(Double.NaN, 0, 0, 0).isNaN());
		assertTrue(new Quaternion(0, Double.NaN, 0, 0).isNaN());
		assertTrue(new Quaternion(0, 0, Double.NaN, 0).isNaN());
		assertTrue(new Quaternion(0, 0, 0, Double.NaN).isNaN());
		assertFalse(new Quaternion(1, 0, 0, 0).isNaN());
	}
}
