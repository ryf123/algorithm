import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

class Task{
	long time;
	String name;
	public Task(String name,long time) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.time = time;
	}
}
public class ToDoList implements Runnable{
	String name;
	PriorityQueue<Task> taskqueue = new PriorityQueue<Task>(new Comparator<Task>() {
		@Override
		public int compare(Task o1, Task o2) {
			return (int)(o1.time - o2.time);
		}
	});
	ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss:SSS");
	public ToDoList(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	@Override
	public void run() {
		while(true){
			lock.lock();
			try {
				while(taskqueue.size() == 0)
					condition.await();
				// multiple tasks may end at the same time
				while(taskqueue.size() > 0 && taskqueue.peek().time <= System.currentTimeMillis()){
					Task current = taskqueue.poll();
					Date date = new Date(current.time);
					System.out.println(current.name + ":"+format.format(date));
				}
				if(taskqueue.size() > 0 && taskqueue.peek().time > System.currentTimeMillis()){
					Task task = taskqueue.peek();
					condition.await(task.time-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				lock.unlock();
			}
		}
	}
	public void addTask(Task t) {
		lock.lock();
		taskqueue.offer(t);
		condition.signal();
		lock.unlock();
	}
	public static void main(String[] args) {
		ToDoList list = new ToDoList("To-Do List");
		Thread thread = new Thread(list);
		thread.start();
		Scanner scanner = new Scanner(System.in);
		while(true){
			String str = scanner.nextLine();
			String[] splits = str.split(":");
			String name = splits[0];
			int time = Integer.parseInt(splits[1]) * 1000;
			Task task = new Task(name, System.currentTimeMillis() + (long)time);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.addTask(task);
		}
		
	}
}
