package elevator;

import java.util.ArrayList;
import java.util.List;

public abstract class ElevatorController implements Runnable{
	Elevator elevator;
	public ElevatorController(Elevator elevator) {
		this.elevator = elevator;
	}
	@Override
	public void run() {
		while(true){
			Request request = getRequest();
			// moving up
			if(elevator.currentFloor < elevator.targetFloor){
				// move to lower floor first
				if(request.targetFloor < elevator.targetFloor){
					// queue the previous request
					saveRequest(new Request(elevator.targetFloor,true));
					elevator.targetFloor = request.targetFloor;
				}
				elevator.moveUp();
			}
			// moving down
			else if (elevator.currentFloor > elevator.targetFloor){
				// move to upper floor first
				if(request.targetFloor > elevator.targetFloor){
					// queue the previous request
					saveRequest(new Request(elevator.targetFloor,false));
					elevator.targetFloor = request.targetFloor;
				}
				elevator.moveDown();
			}
			else{
				elevator.openDoor();
				elevator.closeDoor();
			}
		}
	}
	// get request
	abstract Request getRequest();
	void saveRequest(Request r){
		
	}
    public static void main(String[] args) {
    	System.out.println("controller started");
	}
}
