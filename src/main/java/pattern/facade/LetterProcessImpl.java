package pattern.facade;

public class LetterProcessImpl implements LetterProcess {

	@Override
	public void writeLetter(String content) {
		System.out.println("信件的正文 ：  "+ content);
	}

	@Override
	public void fillEnvelope(String address) {
		System.out.println("地址是   : "+ address);
	}

	@Override
	public void letterIntoEnvelop() {
		System.out.println("裝信封");
	}

	@Override
	public void sendLetter() {
		System.out.println("開始寄信");
	}

}
