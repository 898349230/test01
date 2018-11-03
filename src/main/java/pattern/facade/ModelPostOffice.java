package pattern.facade;

public class ModelPostOffice {
	
	LetterProcess letterProcess = new LetterProcessImpl();
	
	public void sendLetter(String content,String address){
		letterProcess.writeLetter(content);
		letterProcess.fillEnvelope(address);
		letterProcess.letterIntoEnvelop();
		letterProcess.sendLetter();
	}
}
