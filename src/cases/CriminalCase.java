package cases;

import java.util.HashSet;

import people.*;

public class CriminalCase extends Case {

	public CriminalCase(Judge judge, HashSet<Jury> jury, Defendant defendant, Attorney prosecutor,
			HashSet<Witness> witneses, String caseArchive) {
		super(judge, jury, defendant, prosecutor, witneses, caseArchive);

	}
	
	@Override
	public void askQuestions(){
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < 5; i++) {
			builder.append(((Attorney)super.prosecutor).askQuestion(super.defendant));
			builder.append(System.lineSeparator());
		}
		
		for (Witness witness : super.witneses) {
			for (int i = 0; i < 5; i++) {
				builder.append(((Attorney)super.prosecutor).askQuestion(witness));
				builder.append(System.lineSeparator());
			}
		}

		((Attorney)super.prosecutor).takeNotes(builder.toString(), super.caseArchive);
	}
	
	@Override
	protected void addCases() {
		super.addCases();
		
		((Attorney)super.prosecutor).addCase();
	}
}
