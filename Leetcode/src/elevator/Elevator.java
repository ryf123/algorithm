package elevator;
enum elevatorStatus{
	move,
	stop
};
public class Elevator {
	int currentFloor;
	elevatorStatus status;
	int capacity;
	int targetFloor;
	int bottomFloor;
	int topFloor;
	// move up by one level
	void moveUp(){}
	// move down by one level
	void moveDown(){}
	void closeDoor(){}
	void openDoor(){}
}
