package pattern.state;
/**
 * 电梯门关闭后可以做哪些事情
 * 
 * @author Administrator
 *
 */
public class ClosingState extends LiftState {

	@Override
	public void open() {
		super.context.setLiftState(Context.openningState); //置为门敞状态
		super.context.getLiftState().open();
	}

	@Override
	public void close() {
		System.out.println("电梯门关闭。。。");
	}

	//电梯门关了就跑，这是再正常不过了
	@Override
	public void run() {
		super.context.setLiftState(Context.runningState); //设置为运行状态；
		super.context.getLiftState().run();
	}
	//电梯门关着，我就不按楼层
	@Override
	public void stop() {
		super.context.setLiftState(Context.stoppingState); //设置为停止状态；
		super.context.getLiftState().stop();
	}

}
