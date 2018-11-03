/*package concurr.ch10;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

*//**
 * 代码的执行逻辑是，生产者启动一个线程把所有邮件全部抽取到队列中，消费者启动
 * CPU*2个线程数处理邮件，从之前的单线程处理邮件变成了现在的多线程处理，并且抽取邮件
 * 的线程不需要等处理邮件的线程处理完再抽取新邮件，
 * @author 
 *
 *//*
public class QuickEmailToWikiExtractor extends AbstractExtractor {

	private ThreadPoolExecutor threadsPool;
	private ArticleBlockingQueue<ExchangeEmailShallowDTO> emailQueue;

	public QuickEmailToWikiExtractor() {
		emailQueue = new ArticleBlockingQueue<ExchangeEmailShallowDTO>();
		int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
		threadsPool = new ThreadPoolExecutor(corePoolSize, corePoolSize, 10l, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(2000));
	}

	public void extract() {
		long start = System.currentTimeMillis();
		// 抽取所有邮件放到队列里
		new ExtractEmailTask().start();
		// 把队列里的文章插入到Wiki
		insertToWiki();
		long end = System.currentTimeMillis();
		double cost = (end - start) / 1000;
	}

	*//**
	 * 把队列里的文章插入到Wiki
	 *//*
	private void insertToWiki() {
		// 登录Wiki,每间隔一段时间需要登录一次
		confluenceService.login(RuleFactory.USER_NAME, RuleFactory.PASSWORD);
		while (true) {
			// 2秒内取不到就退出
			ExchangeEmailShallowDTO email = emailQueue.poll(2, TimeUnit.SECONDS);
			if (email == null) {
				break;
			}
			threadsPool.submit(new insertToWikiTask(email));
		}
	}

	
	
	protected List<Article> extractEmail() {
		List<ExchangeEmailShallowDTO> allEmails = getEmailService().queryAllEmails();
		if (allEmails == null) {
			return null;
		}
		for (ExchangeEmailShallowDTO exchangeEmailShallowDTO : allEmails) {
			emailQueue.offer(exchangeEmailShallowDTO);
		}
		return null;
	}

	*//**
	 * 抽取邮件任务
	 **
	 * @author tengfei.fangtf
	 *//*
	public class ExtractEmailTask extends Thread {
		public void run() {
			extractEmail();
		}
	}
}*/