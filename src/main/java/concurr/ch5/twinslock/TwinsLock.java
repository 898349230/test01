package concurr.ch5.twinslock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义工具
 * 该工具在同一时刻，只允许至多两个线程同时访问，超过两个线程的访问将被阻塞
 * @author
 *
 */
public class TwinsLock implements Lock {

	private final Sync sync = new Sync(2);

	private static final class Sync extends AbstractQueuedSynchronizer {
		
		Sync(int count) {
			if (count <= 0) {
				throw new IllegalArgumentException("count must largethan zero.");
			}
			setState(count);
		}

		public int tryAcquireShared(int reduceCount) {
			for (;;) {
				int current = getState();
				int newCount = current - reduceCount;
				if (newCount < 0 || compareAndSetState(current, newCount)) {
					return newCount;
				}
			}
		}

		public boolean tryReleaseShared(int returnCount) {
			for (;;) {
				int current = getState();
				int newCount = current + returnCount;
				if (compareAndSetState(current, newCount)) {
					return true;
				}
			}
		}
	}

	@Override
	public void lock() {
		sync.acquireShared(1);		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(timeout));
	}

	@Override
	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public Condition newCondition() {
		return null;
	}

	@Override
	public boolean tryLock() {
		return false;
	}

}
