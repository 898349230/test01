package pattern.facade;

public interface LetterProcess {
	public void writeLetter(String content);
	public void fillEnvelope(String address);
	public void letterIntoEnvelop();
	public void sendLetter();
}
